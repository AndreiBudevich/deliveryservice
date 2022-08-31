function makeEditableCategory(datatableOpts) {
    $("#datatableCategory").DataTable(
        $.extend(true, datatableOpts,
            {
                "ajax": {
                    "url": "api/categories",
                    "dataSrc": ""
                },
                "bFilter": false,
                "bSort": false,
                "bInfo": false,
                "paging": false,
            }
        ));
    form = $('#detailsForm');
    ajaxError();
    ajaxSetup()
}

window.addEventListener("load", function () {
    makeEditableCategory({
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "enabled",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<input type='checkbox' " + ("checked") + " onclick='enable($(this));'/>";
                    }
                    return data;
                }
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ],
        "createdRow": function (row, data) {
            if (!data.enabled) {
                $(row).attr("data-category-enabled", true);
            }
        }
    });
});


