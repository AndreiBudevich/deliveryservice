const shopAjaxUrl = "api/shops/";

const ctx = {
    ajaxUrl: shopAjaxUrl,
    updateTable: function () {
        $.get(shopAjaxUrl, updateTableByData);
    }
}

$(function () {
    makeEditable({
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "description"
            },
            {
                "data": "address"
            },
            {
                "data": "contact"
            },
            {
                "data": "id",
                "render": function (data, type) {
                    if (type === "display") {
                        return "<a href='storage?shopId=" + data+ "'>" + "Товары" + "</a>";
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
