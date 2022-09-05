const orderAjaxUrl = "api/clients/";

const ctx = {
    ajaxUrl: orderAjaxUrl + "orders/",
    updateTable: function () {
        $.get(orderAjaxUrl, updateTableByData);
    }
}

function ship(checkb, id) {
    if (confirm(i18n['common.confirm'])) {
        let shipped = checkb.is(":checked");
        $.ajax({
            url: "api/clients/" + getIdClient(id) + "/orders/" + id,
            type: "POST",
        }).done(function () {
            checkb.closest("tr").attr("data-order-shipped", shipped);
            successNoty(shipped ? "order.shipped" : "order.notShipped");
            let dataTable = $('#datatable').DataTable();
            dataTable.ajax.reload();
        }).fail(function () {
            $(checkb).prop("checked", !shipped);
        });
    }
}

$(function () {
    makeEditable({
        "columns": [
            {
                "data": "client",
                "render": function (data, type) {
                    if (type === "display") {
                        return "<a href='client_orders?" + getParamClient(data) +
                            "'>" + data.surname + " " + data.name.substr(0, 1) + ". " +
                            data.middleName.substr(0, 1) + "." + "</a>";
                    }
                    return data;
                }
            },
            {
                "data": "registered",
                "render": function (date, type) {
                    if (type === "display") {
                        return date.substr(0, 16).replace('T', ' ');
                    }
                    return date;
                }
            },
            {
                "data": "deliveryAddress"
            },
            {
                "data": "totalCost"
            },
            {
                "data": "shipped",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='ship($(this)," + row.id + ");'/>";
                    }
                    return data;
                }
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtnWithCheckShipped
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtnWithCheckShipped
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data) {
            if (data.shipped) {
                $(row).attr("data-order-shipped", true);
            }
        }
    });
});

function renderEditBtnWithCheckShipped(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRowWithCheckShipped(" + row.id + ");'><span class='fa fa-pencil'></span></a>";
    }
}

function renderDeleteBtnWithCheckShipped(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRowWithCheckShipped(" + row.id + ");'><span class='fa fa-remove'></span></a>";
    }
}

function getIdClient(idRow) {
    let IdClient;
    let datatable = $('#datatable').DataTable();
    let data = datatable.rows().data();
    data.each(function (value) {
        let idRowActual = value['id'];
        if (idRowActual.toString() === idRow.toString()) {
            IdClient = value['client']['id'];
        }
    });
    return IdClient;
}

function getShipped(idRow) {
    let shipped;
    let datatable = $('#datatable').DataTable();
    let data = datatable.rows().data();
    data.each(function (value) {
        let idRowActual = value['id'];
        if (idRowActual.toString() === idRow.toString()) {
            shipped = value['shipped'];
        }
    });
    return shipped;
}

function updateRowWithCheckShipped(id) {
    if (checkShipped(id)) {
        return;
    }
    updateRow(id);
}

function deleteRowWithCheckShipped(id) {
    if (checkShipped(id)) {
        return;
    }
    deleteRow(id);
}

function checkShipped(id) {
    let shipped = getShipped(id)
    if (shipped === true) {
        expectedFailNoty("exception.order.shipmentStatus");
    }
    return shipped;
}

function updateRow(id) {
    let editRow = new bootstrap.Modal(document.getElementById('editRow'));
    editRow.show();
    form.find(":input").val("");
    $("#modalTitle").html(i18n["editTitle"]);
    $.get(orderAjaxUrl + getIdClient(id) + "/orders/" + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#editRow').modal();
    });
}

function deleteRow(id) {
    if (confirm(i18n['common.confirm'])) {
        $.ajax({
            url: orderAjaxUrl + getIdClient(id) + "/orders/" + id,
            type: "DELETE"
        }).done(function () {
            let dataTable = $('#datatable').DataTable();
            dataTable.ajax.reload();
            successNoty("common.deleted");
        });
    }
}

function save() {
    let idRow = $('#editRow').find("input[name='id']").val();
    $.ajax({
        type: "POST",
        url: orderAjaxUrl + getIdClient(idRow) + "/orders/",
        data: form.serialize()
    }).done(function () {
        $("#editRow").modal("hide");
        let dataTable = $('#datatable').DataTable();
        dataTable.ajax.reload();
        successNoty("common.saved");
    });
}
