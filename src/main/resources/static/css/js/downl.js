function downloadExcel() {
    let table = document.getElementById("myTable");
    let html = table.outerHTML.replace(/ /g, '%20');

    let fileName = "table_data.xls";

    let downloadLink = document.createElement("a");
    document.body.appendChild(downloadLink);

    downloadLink.href = 'data:application/vnd.ms-excel,' + html;
    downloadLink.download = fileName;
    downloadLink.click();
    document.body.removeChild(downloadLink);
}
