function sucscribe() {
    var email = $('#idScbEmail').val();

    $.ajax({
        type: "POST",
        url: "/sucscribe",
        data: {email : email},
        success: function () {
            $('#idScbEmail').val('');
            alert('You will get the lastest news!');

        }, error: function () {
            alert('Internal error!');
        }
    });
}
