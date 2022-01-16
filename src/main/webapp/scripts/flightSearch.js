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

    /* todo перенести в отдельный файл. рефакторинг */
    let dest = $('#dest');
    let destCities = $("#dest option");
    $('#dept').change(function () {
        if($('#dept option:checked').val() === "1") {
            destCities.each(function () {
                $(this).removeClass('d-none');
            })
            $('#dest option[value = "1"]').addClass('d-none');
            dest.val($('#dest option[disabled]').val());
            dest.css('color', '#6c757d');
        } else {
            destCities.each(function () {
                if ($(this).val() !== "1") {
                    $(this).addClass('d-none');
                } else {
                    $(this).removeClass('d-none');
                    dest.css('color', 'black');
                    dest.val($(this).val());
                }
            })
        }
    });
});