$(document).ready(function () {
    let from = $("#from").datepicker({
        minDate: 0,
        changeMonth: true,
        numberOfMonths: 2,
    }).on("change", function() {
        to.datepicker("option", "minDate", from.datepicker('getDate'));
    });
    let to = $("#to").datepicker({
        minDate: +1,
        changeMonth: true,
        numberOfMonths: 2,
    }).on("change", function() {
        from.datepicker("option", "maxDate", to.datepicker('getDate'));
    });

    $('#fromBlock').on('click', function () {
        $("#from").datepicker('show');
    });
    $('#toBlock').on('click', function () {
        $("#to").datepicker('show');
    });
});