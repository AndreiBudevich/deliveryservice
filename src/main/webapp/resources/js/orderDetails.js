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
                "render": renderMinusBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderPlusBtn
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

function renderPlusBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='plus(" + row.id + ");'><span class='fa fa-plus'></span></a>";
    }
}

function renderMinusBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='minus(" + row.id + ");'><span class='fa fa-minus'></span></a>";
    }
}
