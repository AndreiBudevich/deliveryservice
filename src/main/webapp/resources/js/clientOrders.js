const parameters = getUrlParameters();
const orderOrderAjaxUrl = "api/clients/" + parameters[1] + "/orders/";

const ctx = {
    ajaxUrl: orderOrderAjaxUrl,
    updateTable: function () {
        $.get(orderOrderAjaxUrl, updateTableByData);
    }
}

$(function () {
    $('#clientName').val(parameters[2]);
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
                        return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='enable($(this)," + row.id + ");'/>";
                    }
                    return data;
                }
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
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

function addWithSetAddress() {
    form.find(":input").val("");
    $("#modalTitle").html(i18n["addTitle"]);
    $("#deliveryAddress").val(decodeURIComponent(parameters[2]));
}