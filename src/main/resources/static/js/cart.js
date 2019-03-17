var id;
var quantityArr = [];

$(function () {
    var pageName = $('#pageName').val();
    if (pageName === 'cartPage') {
        getCarts();
    }
});


function getCarts(){
    $.ajax({
        type:"GET",
        url: "/cart/get-carts",
        data: "JSON",
        success: function (carts) {
            $('#idCarts').empty();
            carts.forEach(function (cart) {
                $('#count').clear;
                $('#idCarts').append('<tr>')
                    $('#idCarts').append('<td class="product-thumbnail"><a href="' + "/single-product?idBook=" + cart.book.idBook + '"><img src="/images/product/sm-3/1.jpg" alt=""></a></td>');
                    $('#idCarts').append('<td class="product-name"><a href="' + "/single-product?idBook=" + cart.book.idBook + '">' + cart.book.title + '</a></td>')
                    $('#idCarts').append('<td class="product-price"><span class="amount">' + "$" + cart.book.stock.price + '</span></td>');
                    $('#idCarts').append('<td class="product-stock-status"><input type="number" id="count" value="' + cart.quantity + '" class="classCartQuantity"/></td>');
                    $('#idCarts').append('<td class="product-subtotal">' + "$" + cart.subTotals + '</td>');
                $('#idCarts').append('<td class="product-remove"> <button class="zmdi zmdi-delete" type="button" onclick="deleteCartById('+ cart.idCart +')" ></button></td>');
                $('#idCarts').append('<input type="hidden" value="'+cart.idCart+'" class="classIdCart"/>');
                $('#idCarts').append('</tr>')



            })
        }
    })
}

function getQuant() {
    console.log(quantityArr);
}

function getMinicarts() {
    $.ajax({
        type: "GET",
        url: "/cart/get-carts",
        data: "JSON",
        success: function (carts) {
            $('#startShoping').empty();
            $('#startShoping').append('<div class="minicart-content-wrapper">\n' +
                '                                    <div class="micart__close">\n' +
                '                                        <span>close</span>\n' +
                '                                    </div>\n' +
                '                                    <div class="items-total d-flex justify-content-between">\n' +
                '                                        <span>'+ carts.length +' items</span>\n' +
                '                                        <span>Cart Subtotal</span>\n' +
                '                                    </div>\n' +
                '                                    <div class="total_amount text-right">\n' +
                '                                        <span id="idCardSubtotal">0</span>\n' +
                '                                    </div>\n' +
                '                                    <div class="mini_action checkout">\n' +
                '                                        <a class="checkout__btn" href="/cart/checkout">Go to Checkout</a>\n' +
                '                                    </div>\n' +
                '                                    <div class="single__items">\n' +
                '                                        <div class="miniproduct" id="minicart">\n' +
                '\n' +
                '                                        </div>\n' +
                '                                    </div>\n' +
                '                                    <div class="mini_action cart">\n' +
                '                                        <a class="cart__btn" href="/cart">View and edit cart</a>\n' +
                '                                    </div>\n' +
                '                                </div>');
            $('#minicart').empty();
            carts.forEach(function (cart) {
                $('#minicart').append(' <div class="item01 d-flex" >\n' +
                    '                                                <div class="thumb">\n' +
                    '                                                    <a href="product-details.html"><img\n' +
                    '                                                            src="/images/product/sm-img/1.jpg"\n' +
                    '                                                            alt="product images"></a>\n' +
                    '                                                </div>\n' +
                    '                                                <div class="content">\n' +
                    '                                                    <h6><a href="product-details.html">' + cart.book.title + '</a></h6>\n' +
                    '                                                    <span class="prize">$'+ cart.book.stock.price +'</span>\n' +
                    '                                                    <div class="product_prize d-flex justify-content-between">\n' +
                    '                                                        <span class="qun">Qty: '+ cart.quantity +'</span>\n' +
                    '                                                        <ul class="d-flex justify-content-end">\n' +
                    '                                                            <li><a href="#"><i class="zmdi zmdi-settings"></i></a></li>\n' +
                    '                                                            <li><a href="#"><i type="button" onclick="deleteCartById('+ cart.idCart +')" class="zmdi zmdi-delete"></i></a></li>\n' +
                    '                                                        </ul>\n' +
                    '                                                    </div>\n' +
                    '                                                </div>\n' +
                    '                                                \n' +
                    '                                            </div>');
                $('#idCardSubtotal').text(parseInt($('#idCardSubtotal').text())+cart.subTotals);
            });


            $('#startShping').append('</div>');
        },
        error: function () {
            alert("Error mini carts")
        }
    })
}

function deleteCartById(id) {
    $.ajax({
        type: "DELETE",
        url: "/cart/delete-cart",
        data: {id: id},
        success: function () {
            getCarts();
            getMinicarts();
        }

    })
}

function addToCart(idBook, idWishlist) {
    $.ajax({
        type: "POST",
        url: "/cart/add-to-cart",
        data: {idBook: idBook, idWishlist: idWishlist},
        success: function () {
            alert('Book successfully added to cart!');
            getWishlists();
        },error: function () {
            alert('Internal error!');
        }
    });
}

function updateAllCarts() {

    var cartIds = [];
    $('.classIdCart').each(function (i) {
        cartIds.push($(this).val());
    });
    var cartQuantities = [];
    $('.classCartQuantity').each(function (i) {
        cartQuantities.push($(this).val());
    });
    //var cartThisId = [];

    console.log(cartIds);
    console.log(cartQuantities);

    for(var i=0; i<cartIds.length; i++){
        console.log("ID: " + cartIds[i]);
        console.log("Quantity: " + cartQuantities[i]);
        updateCart(cartIds[i], cartQuantities[i]);
    }
}

function updateCart(idCart, quantity) {
    console.log("Gelen ID: " + idCart);
    console.log("Gelen Quantity: " + quantity);
    $.ajax({
        type: "POST",
        url: "/cart/update-cart",
        data: {idCart: idCart, quantity: quantity},
        success: function () {
            getCarts();
        },error: function () {
            //alert('Internal error!');
        }
    });
}

function showContent(tableID) {
    var tbl = document.getElementById(tableID);
    var rCount = document.getElementById(table).rows.length;
    try {
        alert(tbl.rows[rCount-1].cells[0].children[0].value);

    } catch (e) {
        alert(e);
    }

}


function myFunction(table) {
    var x = document.getElementById(table).rows.length;
    console.log("Found " + x + " tr elements in the table.");
}
