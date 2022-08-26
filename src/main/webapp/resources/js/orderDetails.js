const parameters = getUrlParameters();
const clientAjaxUrl = "api/clients/" + parameters[1] + "/orders/" + parameters[2] + "/details";
const productAjaxUrl = "api/products/";

const ctx = {
    ajaxUrl: clientAjaxUrl,
    updateTable: function () {
        $.get(clientAjaxUrl, updateTableByData);
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
                "data": "quanity"
            },
            {
                "data": "amount"
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
    });
});