const parameters = getUrlParameters();
const orderDetailsAjaxUrl = "api/clients/" + parameters[1] + "/orders/" + parameters[2] + "/details/";

const ctx = {
    ajaxUrl: orderDetailsAjaxUrl,
    updateTable: function () {
        $.get(orderDetailsAjaxUrl, updateTableByData);
    }
}

$(function () {
    makeEditable({
        "columns": [
            {
                "data": "product.name",
            },
            {
                "data": "price"
            },

            {
                "data": "quantity"
            },
            {
                "data": "amount"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditRowOrderBtn
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
    });
});

function renderEditRowOrderBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.id + ");'><span class='fa fa-pencil'></span></a>";
    }
}

function saveOrderDetails() {
    let idProduct;

    let datatable = $('#datatable').DataTable();
    let data = datatable.rows().data();
    let idRow = $('#detailsForm')[0][0].value;
    data.each(function (value, index) {
        let idRowActual = value['id'];
        if (idRowActual.toString()===idRow.toString()) {
            idProduct = value['product']['id'];
        }
    });

    $.ajax({
        type: "POST",
        url: ctx.ajaxUrl + idProduct,
        data: form.serialize()
    }).done(function () {
        $("#editRow").modal("hide");
        ctx.updateTable();
        successNoty("common.saved");
    });
}
