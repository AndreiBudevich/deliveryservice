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

function categoriesSerialize(categories) {
    let res = "&categories=";
    for (let key in categories) {
        res = res + String(categories[key]) + "_";
    }
    return res;
}

function getCategories () {
    let categories = [];
    let datatable = document.getElementById('datatableCategory')
    let rows = datatable.getElementsByTagName('tbody')[0].rows

    $(rows).each(function (i, el) {
        let tdNameCategory = $(el)[0].getElementsByTagName("td")[0];
        let input = $(el)[0].getElementsByTagName("td")[1];
        let checkbox = input.getElementsByTagName("input")[0];
        let checked = checkbox.checked;
        if (checked) {
            let valueNameCategory = tdNameCategory.innerText;
            categories.push(i + 1);
        }
    });
    return categories;
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(productAjaxUrl, makeEditable);
}

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
