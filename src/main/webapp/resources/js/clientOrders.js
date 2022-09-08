const parameters = getUrlParameters();
const orderAjaxUrl = "api/clients/" + parameters[1] + "/orders/";

const ctx = {
    ajaxUrl: orderAjaxUrl,
    updateTable: function () {
        $.get(orderAjaxUrl, updateTableByData);
    }
}

$(function () {
    makeEditable({
        "columns": [
            {
                "data": "registered",
                "render": function (date, type, row) {
                    if (type === "display") {
                        return date.substr(0, 16).replace('T', ' ');
                    }
                    return date;
                }
            },
            {
                "data": "deliveryAddress"
            },
            {
                "data": "totalCost"
            },
            {
                "data": "shipped",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='ship($(this)," + row.id + ");'/>";
                    }
                    return data;
                }
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": getOrderProductsBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtnWithCheckShipped
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtnWithCheckShipped
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data) {
            if (data.shipped) {
                $(row).attr("data-order-shipped", true);
            }
        }
    });
});

function ship(checkb, id) {
    if (confirm(i18n['common.confirm'])) {
        let shipped = checkb.is(":checked");
        $.ajax({
            url: orderAjaxUrl + id,
            type: "POST",
        }).done(function () {
            updateDataTableForShipped(shipped, checkb);
        }).fail(function () {
            $(checkb).prop("checked", !shipped);
        });
    }
}

function addWithSetAddress() {
    form.find(":input").val("");
    $("#modalTitle").html(i18n["addTitle"]);
    $("#totalCost").val(0);
    $("#deliveryAddress").val(decodeURIComponent(parameters[2]));
}

function getOrderProductsBtn(data, type, row) {
    if (type === "display") {
        return "<a href='order_details?" + $.param({
            clientId: parameters[1],
            orderId: row.id,
            shipped: row.shipped,
        }) + "'>" + "<span class='fa fa-shopping-basket'></span></a>";
    }
}