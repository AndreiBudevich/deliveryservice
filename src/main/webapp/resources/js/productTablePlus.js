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
                "data": "price"
            },
            {
                "data": "discount"
            },
            {
                "data": "nameShop"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderAddBtn
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

function renderAddBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='plus(" + row.id + ");'><span class='fa fa-plus'></span></a>";
    }
}