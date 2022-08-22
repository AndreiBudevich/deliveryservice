let event_status = false;

window.addEventListener("load", function () {

    ["mouseover", "click", "scroll"].forEach(function (event) {

        window.addEventListener(event, function () {
            if (!event_status) {

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

                document.getElementById('priceFrom').value = Math.min.apply(null, valuesPrice)
                document.getElementById('priceUpTo').value = Math.max.apply(null, valuesPrice);

                document.getElementById('discountFrom').value = Math.min.apply(null, valuesDiscount);
                document.getElementById('discountUpTo').value = Math.max.apply(null, valuesDiscount);

                event_status = true;
            }
        }, {
            once: true
        });
    });
});
