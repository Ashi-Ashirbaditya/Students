function downloadExcel() {
    let table = document.getElementById("myTable");
    let html = table.outerHTML.replace(/ /g, '%20');

    let fileName = "student_data.xls";

    let downloadLink = document.createElement("a");
    document.body.appendChild(downloadLink);

    downloadLink.href = 'data:application/vnd.ms-excel,' + html;
    downloadLink.download = fileName;
    downloadLink.click();
    document.body.removeChild(downloadLink);
}
function downloadExcel2() {
    let table = document.getElementById("myTable");
    let html = table.outerHTML.replace(/ /g, '%20');

    let fileName = "course_data.xls";

    let downloadLink = document.createElement("a");
    document.body.appendChild(downloadLink);

    downloadLink.href = 'data:application/vnd.ms-excel,' + html;
    downloadLink.download = fileName;
    downloadLink.click();
    document.body.removeChild(downloadLink);
}