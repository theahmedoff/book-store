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
        dataType: "json",
        success: function (reviews) {
            $('#idDivReviews').empty();
            reviews.forEach(function (review) {
            	$('#idDivReviews').append('<div class="review__attribute">\n' +
                    '                      <p>' + review.desc +'</p>\n' +
                    '                      <div class="review__ratings__type d-flex">\n' +
                    '                        <div class="review-ratings">\n' +
                    '                          <div class="rating-summary d-flex">\n' +
                    '                            <ul class="rating d-flex">\n' +
                    '                              <li><i class="zmdi zmdi-star"></i></li>\n' +
                    '                              <li><i class="zmdi zmdi-star"></i></li>\n' +
                    '                              <li class="off"><i class="zmdi zmdi-star"></i></li>\n' +
                    '                              <li class="off"><i class="zmdi zmdi-star"></i></li>\n' +
                    '                              <li class="off"><i class="zmdi zmdi-star"></i></li>\n' +
                    '                            </ul>\n' +
                    '                          </div>\n' +
                    '                        </div>\n' +
                    '                        <div class="review-content">\n' +
                    '                          <p>Review by ' + review.user.name + ' ' + review.user.surname + '</p>\n' +
                    '                          <p>Posted on ' + review.writeDate + '</p>\n' +
                    '                        </div>\n' +
                    '                      </div>\n' +
                    '                    </div>\n' +
                    '<br>\n' +
                    '<hr/>\n' +
                    '<br/>');
            });
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
            desc.val('');

        }, error: function () {
            alert('Internal error!');
        }
    });
}