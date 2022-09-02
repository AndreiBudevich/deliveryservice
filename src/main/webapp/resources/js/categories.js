const categoryAjaxUrl = "api/categories/";

const ctx = {
    ajaxUrl: categoryAjaxUrl,
    updateTable: function () {
        $.get(categoryAjaxUrl, updateTableByData);
    }
}

$(function () {
    makeEditable({
        "columns": [
            {
                "data": "name",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<a href='categoryProducts?categoryId=" + row.id + "&categoryName=" + data +"'>" + data + "</a>";
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