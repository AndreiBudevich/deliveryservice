function updateDataTableForShipped(shipped) {
    echeckb.closest("tr").attr("data-order-shipped", shipped);
    successNoty(shipped ? "order.shipped" : "order.notShipped");
    let dataTable = $('#datatable').DataTable();
    dataTable.ajax.reload();
}

function getShipped(idRow) {
    let shipped;
    let datatable = $('#datatable').DataTable();
    let data = datatable.rows().data();
    data.each(function (value) {
        let idRowActual = value['id'];
        if (idRowActual.toString() === idRow.toString()) {
            shipped = value['shipped'];
        }
    });
    return shipped;
}

function renderEditBtnWithCheckShipped(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRowWithCheckShipped(" + row.id + ");'><span class='fa fa-pencil'></span></a>";
    }
}

function renderDeleteBtnWithCheckShipped(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRowWithCheckShipped(" + row.id + ");'><span class='fa fa-remove'></span></a>";
    }
}

function updateRowWithCheckShipped(id) {
    if (checkShipped(id)) {
        return;
    }
    updateRow(id);
}

function deleteRowWithCheckShipped(id) {
    if (checkShipped(id)) {
        return;
    }
    deleteRow(id);
}

function checkShipped(id) {
    let shipped = getShipped(id)
    if (shipped === true) {
        expectedFailNoty("exception.order.shipmentStatus");
    }
    return shipped;
}


