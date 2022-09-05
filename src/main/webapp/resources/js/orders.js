const orderAjaxUrl = "api/clients/orders/";

const ctx = {
    ajaxUrl: orderAjaxUrl,
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
                        return "<a href='client_orders?" + getParamClient (data) +
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
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
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

function getIdClient (idRow) {
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

