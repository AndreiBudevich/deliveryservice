const parameters = getUrlParameters();
const orderAjaxUrl = "api/clients/" + parameters[1] + "/orders/" + parameters[2];
const orderAddProduct = orderAjaxUrl + "/add-product/";
const linkOrderDetails = "order_details?clientId=" + parameters[1] + "&orderId=" + parameters[2];
const linkOrderDetailsText = "Заказ общая стоимость: ";

$("#orderTotalCost").html('<a href="' + linkOrderDetails + '"><h4 class="text-center"><p class="totalCost" id = "totalCost" ></p></h4></a>')
updateOrderTotalCost();

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
        url: orderAddProduct + id,
        type: "POST",
    }).done(function () {
        successNoty("product.order.added");
        updateOrderTotalCost();
    }).fail(function () {
        successNoty("product.order.notAdded");
    });
}

function updateOrderTotalCost() {
    $.get(orderAjaxUrl, function (data) {
        let totalCost = document.getElementById("totalCost");
        totalCost.innerHTML = linkOrderDetailsText + data.totalCost;
    });
}

