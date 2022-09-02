function getParamClient (row) {
    return $.param({
        clientId: row.id,
        residentialAddress: row.residentialAddress,
        clientName: row.surname + " " + row.name + " " + row.middleName,
    });
}