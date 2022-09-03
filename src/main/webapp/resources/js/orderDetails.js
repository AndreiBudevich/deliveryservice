
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
                "render": renderEditBtn
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

function plusWithUpdateRow(idRow) {
    plusProduct(getIdProduct(idRow));
}

function minusWithUpdateRow(idRow) {
    minusProduct(getIdProduct(idRow));
}

function deleteRowWithUpdateTotalCost(id) {
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

function saveWithIdProduct() {
    let idRow = $('#detailsForm')[0][0].value;

    $.ajax({
        type: "POST",
        url: ctx.ajaxUrl + getIdProduct(idRow),
        data: form.serialize()
    }).done(function () {
        $("#editRow").modal("hide");
        ctx.updateTable();
        updateOrderTotalCost();
        successNoty("common.saved");
    });
}

function getIdProduct (idRow) {
    let idProduct;
    let datatable = $('#datatable').DataTable();
    let data = datatable.rows().data();
    data.each(function (value) {
        let idRowActual = value['id'];
        if (idRowActual.toString()===idRow.toString()) {
            idProduct = value['product']['id'];
        }
    });
    return idProduct;
}
