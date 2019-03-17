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
                    '\t\t\t\t\t\t\t\t\t\t\t<p>' + review.desc +'</p>\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t<div class="review__ratings__type d-flex">\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t\t<div class="review-ratings">\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t\t\t<div class="rating-summary d-flex">\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span>' + review.rating +'</span>\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t\t\t\t<ul class="rating d-flex">\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<li><i class="zmdi zmdi-star"></i></li>\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<li><i class="zmdi zmdi-star"></i></li>\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<li><i class="zmdi zmdi-star"></i></li>\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<li class="off"><i class="zmdi zmdi-star"></i></li>\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<li class="off"><i class="zmdi zmdi-star"></i></li>\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t\t\t\t</ul>\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t\t</div>\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t\t<div class="review-content">\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t\t\t<p>Review by ' + review.user.name + ' ' + review.user.surname + '</p>\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t\t\t<p>Posted on ' + review.writeDate + '</p>\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t\t</div>\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t</div>\n' +
                    '\t\t\t\t\t\t\t\t\t\t</div>\n' +
                    '<br>\n' +
                    '<hr/>\n' +
                    '<br/>');
            });
        }
    });
}