const parameters = getUrlParameters()
const shopStorageAjaxUrl = "api/shops/" + parameters[1] + "/storage/";
const productAjaxUrl = "/api/shops/" + parameters[1] + "/products/";

const ctx = {
    ajaxUrl: shopStorageAjaxUrl,
    updateTable: function () {
        $.get(shopStorageAjaxUrl, updateTableByData);
    }
}

$(function () {
    makeEditable({
        "columns": [
            {
                "data": "product",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<a class='productName' onclick='getModalFade(" + data.id + ");'>" + data.name + " " + "<span class='fa fa-paperclip'></span></a>";
                    }
                    return data;
                }
            },
            {
                "data": "product.description"
            },
            {
                "data": "product.price"
            },
            {
                "data": "product.discount"
            },
            {
                "data": "quantity"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
    });
});

formProduct = $('#detailsFormProduct');

function getModalFade(id) {
    $("#editProduct").modal("show");
    formProduct.find(":input").val("");
    $("#modalTitleProduct").html(i18n["editTitle"]);
    $.get(productAjaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            formProduct.find("input[name='" + key + "']").val(value);
        });
        $('#editProduct').modal();
    });
}

function saveProduct() {
    $.ajax({
        type: "POST",
        url: productAjaxUrl,
        data: formProduct.serialize()
    }).done(function () {
        $("#editProduct").modal("hide");
        ctx.updateTable();
        successNoty("common.saved");
    });
}

function add() {
    $("#modalTitleProduct").html(i18n["addTitle"]);
    $("#detailsFormProduct").find(":input").val("");
}