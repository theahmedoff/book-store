$(function () {
    $('#idBtnAddToCart').click(function () {
        var idBook = $('#idBook').val();
        var quantity = $('#qty').val();
        addToCart(idBook, null, quantity);
    });
});

function getReviewsByIdBook(idBook) {
    $.ajax({
        type: "GET",
        url: "/reviews",
        data: {idBook: idBook},
        dataType: 'html',
        success: function (html) {
            $('#idDivReviews').html(html);
        }
    });
}

function addReview(idBook) {
    var desc = $('#idReviewDesc').val();
    var rating = $('#idReviewRating').val();

    $.ajax({
        type: "POST",
        url: "/add-review",
        data: {idBook: idBook, desc: desc, rating: rating},
        success: function (reviews) {
            getReviewsByIdBook(idBook);
            $('#idReviewDesc').val('');

        }, error: function () {
            alert('Internal error!');
        }
    });
}
