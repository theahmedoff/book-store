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

                $('#idWishlists').append('<td class="product-remove"><button onclick="deleteWishlistById(' + wishlist.book.idBook + ')" type="button">X</button></td>');

                $('#idWishlists').append('<td class="product-thumbnail"><a href="' + "/single-product?idBook=" + wishlist.book.idBook + '"><img src="/images/product/sm-3/1.jpg" alt=""></a></td>');

                $('#idWishlists').append('<td class="product-name"><a href="'+ "/single-product?idBook=" + wishlist.book.idBook + '">' + wishlist.book.title + '</a></td>');

                $('#idWishlists').append('<td class="product-price"><span class="amount">' + "$" + wishlist.book.stock.price + '</span></td>');

                $('#idWishlists').append('<td class="product-stock-status"><span class="wishlist-in-stock">' + (wishlist.book.stock.quantity !== 0 ? "In Stock" : "Non Stock") + '</span></td>');

                $('#idWishlists').append('<td class="product-add-to-cart"><button onclick="addToCart(' + wishlist.book.idBook + ',' + wishlist.idWishlist + ')" type="button">Add to Cart</button></td>');

                $('#idWishlists').append('</tr>');

            });

        }

    });

}

function setCookie(cname, cvalue, exdays) {

    var d = new Date();

    d.setTime(d.getTime() + (exdays*24*60*60*1000));

    var expires = "expires="+ d.toUTCString();

    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";

}

function getCookie(cname) {

    var name = cname + "=";

    var decodedCookie = decodeURIComponent(document.cookie);

    var ca = decodedCookie.split(';');

    for(var i = 0; i <ca.length; i++) {

        var c = ca[i];

        while (c.charAt(0) == ' ') {

            c = c.substring(1);

        }

        if (c.indexOf(name) == 0) {

            return c.substring(name.length, c.length);

        }

    }

    return "";

}



function deleteWishlistById(idBook) {

    var list = getCookie("list");

    var index = list.indexOf(idBook);

    if (index !== -1) list.splice(index, 1);

    setCookie("list", list, 1);

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

    var list = getCookie("list");

    if(typeof(list)!=='undefined' && list.length!=0){

        if(list.indexOf(idBook)) return false;

        else {

            list.push(idBook);

            setCookie("list", list, 1);

            $.ajax({

                type: "POST",

                url: "/cart/add-to-wishlist",

                data: {idBook: idBook},

                success: function () {

                    alert('Book successfully added to wishlist!');

                    location.reload(true);

                },error: function () {

                    alert('Internal error!');

                }

            });

        }

    } else alert("Error");



}