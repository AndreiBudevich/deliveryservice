const parameters = getUrlParameters();
const productAddCategory = "/api/shops/products/";

function plus(id) {
    $.ajax({
        url: productAddCategory + id  + "/add-category/" + parameters[1],
        type: "POST",
    }).done(function () {
        successNoty("category.added");
    }).fail(function () {
        successNoty("category.notAdded");
    });
}