$(function () {
    var pageName = $('#pageName').val();
    if (pageName === 'wishlistPage') {
        getWishlists();
    }
});


function getWishlists() {
    $.ajax({
        type: "GET",
        url: "/cart/get-wishlists",
        dataType: "JSON",
        success: function (wishlists) {
            $('#idWishlists').empty();
            wishlists.forEach(function (wishlist) {
                $('#idWishlists').append('<tr>');
                $('#idWishlists').append('<td class="product-remove"><button type="button" onclick="deleteWishlistById(' + wishlist.book.idBook + ')"> X</button></td>');
                $('#idWishlists').append('<td class="product-thumbnail"><a href="' + "/single-product?idBook=" + wishlist.book.idBook + '"><img src="' + "/images/product/sm-3/" + wishlist.book.firstImagePath + '" alt="product image"></a></td>');
                $('#idWishlists').append('<td class="product-name"><a href="' + "/single-product?idBook=" + wishlist.book.idBook + '">' + wishlist.book.title + '</a></td>');
                $('#idWishlists').append('<td class="product-price"><span class="amount">' + "$" + wishlist.book.stock.discountedPrice + '</span></td>');
                $('#idWishlists').append('<td class="product-stock-status"><span class="wishlist-in-stock">' + (wishlist.book.stock.quantity !== 0 ? "In Stock" : "Non Stock") + '</span></td>');
                $('#idWishlists').append('<td class="product-add-to-cart"><button onclick="addToCart(' + wishlist.book.idBook + ',' + wishlist.idWishlist + ')" type="button">Add to Cart</button></td>');
                $('#idWishlists').append('</tr>');
            });
        }
    });
}

function deleteWishlistById(idBook) {
    $.ajax({
        type: "DELETE",
        url: "/cart/delete-wishlist",
        data: {idBook: idBook},
        success: function () {
            location.reload(true);
        }
    });
}


function addToWishlist(idBook) {
    $.ajax({
        type: "POST",
        url: "/cart/add-to-wishlist",
        data: {idBook: idBook},
        success: function () {
            alert('Book successfully added to wishlist!');
            location.reload(true);
        }, error: function () {
            alert('Internal error!');
        }
    });
}