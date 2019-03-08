$(function () {
    // getBooksByIdCategory();
});


function getBooksByIdCategory() {
    $.ajax({
        type: "GET",
        url: "/get-books",
        dataType: "json",
        success: function (books) {
            // $('#idWishlists').empty();
            wishlists.forEach(function (book) {

            });
        }
    });
}