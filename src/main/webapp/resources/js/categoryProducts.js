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

function minus(id) {
    $.ajax({
        url: productAddCategory + id  + "/delete-category/" + parameters[1],
        type: "POST",
    }).done(function () {
        successNoty("category.deleted");
    }).fail(function () {
        successNoty("category.notDeleted");
    });
}