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
                "render": renderMinusBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderPlusBtn
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

function renderPlusBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='plus(" + row.id + ");'><span class='fa fa-plus'></span></a>";
    }
}

function  renderMinusBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='minus(" + row.id + ");'><span class='fa fa-minus'></span></a>";
    }
}