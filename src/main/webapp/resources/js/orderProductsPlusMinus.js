function plusProduct(id) {
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

function minusProduct(id) {
    $.ajax({
        url: orderDeleteProduct + id,
        type: "POST",
    }).done(function () {
        successNoty("product.order.deleted");
        updateOrderTotalCost();
    }).fail(function () {
        expectedFailNoty("product.order.notDeleted");
    });
}