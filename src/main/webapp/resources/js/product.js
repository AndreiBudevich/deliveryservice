const productAjaxUrl = "api/products";

const ctx = {
    ajaxUrl: productAjaxUrl,
    updateTable: function () {
        let categories = getCategories ();
        $.ajax({
            type: "GET",
            url: productAjaxUrl + "/filter",
            data: $("#filter").serialize() + categoriesSerialize (categories)
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
                "data": "shop.name"
            },
            {
                "render": renderAddShoppingBasket,
                "defaultContent": "",
                "orderable": false
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

function renderAddShoppingBasket(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.id + ");'><span class='fa fa-shopping-basket'></span></a>";
    }
}
