const ctx = {
    ajaxUrl: orderDetailsAjaxUrl,
    updateTable: function () {
        $.get(orderDetailsAjaxUrl, updateTableByData);
    }
}

$(function () {
    if (parameters[3] === "true") {
        $('#select').addClass('shipped');
    }
});

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
                "render": renderEditBtnWithCheckShipped
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteRowOrderBtn
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row) {
            if (parameters[3] === "true") {
                let tds = $(row).children('td');
                tds.each(function (key) {
                    if (key > 3) {
                        $(this).attr("data-order-shipped", true);
                    }
                });
            }
        }
    });
});

function renderPlusBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='plusWithUpdateRow(" + row.id + ");'><span class='fa fa-plus'></span></a>";
    }
}

function renderMinusBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='minusWithUpdateRow(" + row.id + ");'><span class='fa fa-minus'></span></a>";
    }
}

function renderDeleteRowOrderBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRowWithUpdateTotalCost(" + row.id + ");'><span class='fa fa-remove'></span></a>";
    }
}

function renderEditBtnWithCheckShipped(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRowWithCheckShipped(" + row.id + ");'><span class='fa fa-pencil'></span></a>";
    }
}

function updateRowWithCheckShipped(id) {
    if (checkShipped() === "true") {
        return;
    }
    updateRow(id);
}

function plusWithUpdateRow(idRow) {
    if (checkShipped() === "true") {
        return;
    }
    plusProduct(getIdProduct(idRow));
}

function minusWithUpdateRow(idRow) {
    if (checkShipped() === "true") {
        return;
    }
    minusProduct(getIdProduct(idRow));
}

function deleteRowWithUpdateTotalCost(id) {
    if (checkShipped() === "true") {
        return;
    }
    if (confirm(i18n['common.confirm'])) {
        $.ajax({
            url: ctx.ajaxUrl + id,
            type: "DELETE"
        }).done(function () {
            ctx.updateTable();
            updateOrderTotalCost();
            successNoty("common.deleted");
        });
    }
}

function plusProduct(id) {
    $.ajax({
        url: orderAddProduct + id,
        type: "POST",
    }).done(function () {
        successNoty("product.order.added");
        ctx.updateTable();
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
        ctx.updateTable();
        updateOrderTotalCost();
    }).fail(function () {
        successNoty("product.order.notDeleted");
    });
}

function checkShipped() {
    let shipped = parameters[3];
    if (shipped === "true") {
        expectedFailNoty("exception.order.shipmentStatus");
    }
    return shipped;
}

function relocateSelectProduct() {

    if (checkShipped() === "true") {
        return;
    }
    location.href = "/order_products?clientId=" + parameters[1] + "&orderId=" + parameters[2];
}

