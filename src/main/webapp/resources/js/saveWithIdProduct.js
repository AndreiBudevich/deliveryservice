function saveWithIdProduct() {
    let idProduct;

    let datatable = $('#datatable').DataTable();
    let data = datatable.rows().data();

    let idRow = $('#detailsForm')[0][0].value;
    data.each(function (value, index) {
        let idRowActual = value['id'];
        if (idRowActual.toString()===idRow.toString()) {
            idProduct = value['product']['id'];
        }
    });

    $.ajax({
        type: "POST",
        url: ctx.ajaxUrl + idProduct,
        data: form.serialize()
    }).done(function () {
        $("#editRow").modal("hide");
        ctx.updateTable();
        successNoty("common.saved");
    });
}