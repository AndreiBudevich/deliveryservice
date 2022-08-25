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
                "data": "surname"
            },
            {
                "data": "name"
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
                "render": getClientOrdersBtn
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

function getClientOrdersBtn(data, type, row) {
    if (type === "display") {
        return "<a href='client_orders?" + $.param({
            clientId: row.id,
            residentialAddress: row.residentialAddress,
            clientName: row.surname + " " + row.name + " " + row.middleName,
        }) + "'>" + "<span class='fa fa-shopping-bag'></span></a>";
    }
}
