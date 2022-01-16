function setMutedColor(object) {
    object.each(function () {
        if ($(this).children("option:selected").val() === '') {
            $(this).css('color','#6c757d');
        }
        $(this).change(function() {
            let current = $(this).val();
            if (current !== '') {
                $(this).css('color','black');
            } else {
                $(this).css('color','#6c757d');
            }
        });
    })
}