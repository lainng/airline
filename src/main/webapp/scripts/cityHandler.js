$(document).ready(function () {
    let dept = $('#dept');
    let dest = $('#dest');
    let destCities = $("#dest option");
    const MINSK = "1";

    dept.change(function () {
        if($('#dept option:checked').val() === MINSK) {
            destCities.each(function () {
                $(this).removeClass('d-none');
            })
            $('#dest option[value = ' + MINSK + ']').addClass('d-none');
            dest.val($('#dest option[disabled]').val());
            dest.css('color', '#6c757d');
        } else {
            destCities.each(function () {
                if ($(this).val() !== MINSK) {
                    $(this).addClass('d-none');
                } else {
                    $(this).removeClass('d-none');
                    dest.css('color', 'black');
                    dest.val($(this).val());
                }
            })
        }
    });

    dest.change(function () {
        if($('#dest option:checked').val() === MINSK
            && $('#dept option:checked').val() === MINSK) {
            dept.val($('#dept option[disabled]').val());
            dept.css('color', '#6c757d');
        }
    });
});