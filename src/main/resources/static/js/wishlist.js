$(function () {
    getWishlists();
});


function getWishlists() {
    $.ajax({
        type: "GET",
        url: "/cart/get-wishlists",
        dataType: "json",
        success: function (wishlists) {
            $('#idWishlists').empty();
            wishlists.forEach(function (wishlist) {
                $('#idWishlists').append('<tr>');
                $('#idWishlists').append('<td class="product-remove"><a href="#" onclick="deleteWishlistById(' + wishlist.book.idBook + ')">×</a></td>');
                $('#idWishlists').append('<td class="product-thumbnail"><a href="' + "/single-product?idBook=" + wishlist.book.idBook + '"><img src="/images/product/sm-3/1.jpg" alt=""></a></td>');
                $('#idWishlists').append('<td class="product-name"><a href="'+ "/single-product?idBook=" + wishlist.book.idBook + '">' + wishlist.book.title + '</a></td>');
                $('#idWishlists').append('<td class="product-price"><span class="amount">' + "$" + wishlist.book.stock.price + '</span></td>');
                $('#idWishlists').append('<td class="product-stock-status"><span class="wishlist-in-stock">' + (wishlist.book.stock.quantity !== 0 ? "In Stock" : "Non Stock") + '</span></td>');
                $('#idWishlists').append('<td class="product-add-to-cart"><a href="#" onclick="addToCart(' + wishlist.user.idUser + ', ' + wishlist.book.idBook + ')">Add to Cart</a></td>');
                $('#idWishlists').append('</tr>');
            });
        }
    });
}

function deleteWishlistById(idWishlist) {
    $.ajax({
        type: "DELETE",
        url: "/cart/delete-wishlist",
        data: {idWishlist: idWishlist},
        statusCode: {
            200: function () {
                getWishlists();
            }
        }
    });
}

function addToCart(idUser, idBook) {
    $.ajax({
        type: "POST",
        url: "/cart/add-wishlist-to-cart",
        data: {idUser: idUser, idBook: idBook},
        statusCode: {
            200: function () {
                alert('Book successfully added to cart!');
            }
        }
    });
}