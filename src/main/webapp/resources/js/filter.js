let event_status = false;
let parameter_update_status = false;

let defaultPriceFrom;
let defaultPriceUpTo;
let defaultDiscountFrom;
let defaultDiscountUpTo;

let priceFrom;
let priceUpTo;
let discountFrom;
let discountUpTo;

window.addEventListener("load", function () {
    parameter_update_status = true;

    ["mouseover", "click", "scroll"].forEach(function (event) {

        window.addEventListener(event, function () {
            if (!event_status) {
                filterGetParameters();
            }
        }, {
            once: true
        });
    });
});

function runFilter () {
    ctx.updateTable();
    parameter_update_status = false
    event_status=false
    setTimeout(filterGetParameters, 200);
}

function filterGetParameters() {

    let valuesPrice = [];
    let valuesDiscount = [];

    let datatable = document.getElementById('datatable')
    let rows = datatable.getElementsByTagName('tbody')[0].rows

    $(rows).each(function (i, el) {
        let tdPrice = $(el)[0].getElementsByTagName("td")[2];
        let tdDiscount = $(el)[0].getElementsByTagName("td")[3];

        let valuePrice = parseInt(tdPrice.innerText);
        let valueDiscount = parseInt(tdDiscount.innerText);

        if (!isNaN(valuePrice))
            valuesPrice.push(valuePrice);
        if (!isNaN(valueDiscount))
            valuesDiscount.push(valueDiscount);
    });

    priceFrom = Math.min.apply(null, valuesPrice);
    priceUpTo = Math.max.apply(null, valuesPrice);
    discountFrom = Math.min.apply(null, valuesDiscount);
    discountUpTo = Math.max.apply(null, valuesDiscount);

    setParameters(priceFrom, priceUpTo, discountFrom, discountUpTo)

    if (parameter_update_status) {
        defaultPriceFrom = priceFrom;
        defaultPriceUpTo = priceUpTo;
        defaultDiscountFrom = discountFrom;
        defaultDiscountUpTo = discountUpTo;
    }
    event_status = true;
}

function setParameters(pf, pU, dF, dU) {
    document.getElementById('priceFrom').value = pf;
    document.getElementById('priceUpTo').value = pU;
    document.getElementById('discountFrom').value = dF;
    document.getElementById('discountUpTo').value = dU;
}

function categoriesSerialize(categories) {
    let res = "&categories=";
    for (let key in categories) {
        res = res + String(categories[key]) + "_";
    }
    return res;
}

function getCategories() {
    let categories = [];
    let rows = getCategoriesTableRows();

    $(rows).each(function (i, el) {
        let tdNameCategory = $(el)[0].getElementsByTagName("td")[0];
        let input = $(el)[0].getElementsByTagName("td")[1];
        let checkbox = input.getElementsByTagName("input")[0];
        let checked = checkbox.checked;
        if (checked) {
            let valueNameCategory = tdNameCategory.innerText;
            categories.push(i + 1);
        }
    });
    return categories;
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(productAjaxUrl, updateTableByData);
    resetCheckbox();
    setParameters(defaultPriceFrom, defaultPriceUpTo, defaultDiscountFrom, defaultDiscountUpTo)
}

function resetCheckbox() {
    let rows = getCategoriesTableRows();
    $(rows).each(function () {
        $(this).closest("tr").attr("data-category-enabled", true);
    });
}

function enable(checkbox) {
    let enabled = checkbox.is(":checked");
    checkbox.closest("tr").attr("data-category-enabled", !!enabled);
    successNoty(enabled ? "common.enabled" : "common.disabled");
}

function getCategoriesTableRows() {
    let datatable = document.getElementById('datatableCategory')
    return datatable.getElementsByTagName('tbody')[0].rows;
}