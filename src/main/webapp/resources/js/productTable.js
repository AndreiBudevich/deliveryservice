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

function renderAddBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='plus(" + row.id + ");'><span class='fa fa-plus'></span></a>";
    }
}

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='minus(" + row.id + ");'><span class='fa fa-minus'></span></a>";
    }
}