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

function getIdProduct(idRow) {
    let idProduct;
    let datatable = $('#datatable').DataTable();
    let data = datatable.rows().data();
    data.each(function (value) {
        let idRowActual = value['id'];
        if (idRowActual.toString() === idRow.toString()) {
            idProduct = value['product']['id'];
        }
    });
    return idProduct;
}