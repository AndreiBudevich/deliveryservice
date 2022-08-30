const parameters = getUrlParameters();
const productAjaxUrl = "api/products";
const orderProductAjaxUrl = "api/clients/" + parameters[1] + "/orders/" + parameters[2] + "/details/";

const ctx = {
    ajaxUrl: productAjaxUrl,
    updateTable: function () {
        let categories = getCategories();
        $.ajax({
            type: "GET",
            url: productAjaxUrl + "/filter",
            data: $("#filter").serialize() + categoriesSerialize(categories)
        }).done(updateTableByData);
    }
};

$(function () {
    makeEditable({
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "description"
            },
            {
                "data": "price"
            },
            {
                "data": "discount"
            },
            {
                "data": "nameShop"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderAddBtn
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

function renderAddBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='addProductInOrder(" + row.id + ");'><span class='fa fa-plus'></span></a>";
    }
}

function addProductInOrder(id) {
    $.ajax({
        url: orderProductAjaxUrl + id,
        type: "POST",
    }).done(function () {
        successNoty("product.order.added");
    }).fail(function () {
        successNoty("product.order.notAdded");
    });
}
