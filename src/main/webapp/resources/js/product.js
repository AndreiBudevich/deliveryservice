const productAjaxUrl = "api/products";

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