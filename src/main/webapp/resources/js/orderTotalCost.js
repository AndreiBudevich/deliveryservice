$("#orderTotalCost").html('<a href="' + linkOrderDetails + '"><h4 class="text-center"><p class="totalCost" id = "totalCost" ></p></h4></a>')
updateOrderTotalCost();

function updateOrderTotalCost() {
    $.get(orderAjaxUrl, function (data) {
        let totalCost = document.getElementById("totalCost");
        totalCost.innerHTML = linkOrderDetailsText + data.totalCost;
    });
}