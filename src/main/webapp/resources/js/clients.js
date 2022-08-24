const clientAjaxUrl = "api/clients/";

const ctx = {
    ajaxUrl: clientAjaxUrl,
    updateTable: function () {
        $.get(clientAjaxUrl, updateTableByData);
    }
}

$(function () {
    makeEditable({
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "surname"
            },
            {
                "data": "middleName"
            },
            {
                "data": "residentialAddress"
            },
            {
                "data": "birthday"
            },
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
                "orderable": false,
                "defaultContent": "",
                "render": seeOrderBtn
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

function seeOrderBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='getClientOrders();'><span class='fa fa-shopping-bag'></span></a>";
    }
}

function getClientOrders () {
}