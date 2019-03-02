$(function () {
    getCarts();
});


function getCarts(){
    $.ajax({
        type:"GET",
        url: "/cart/get-carts",
        data: "JSON",
        success: function (carts) {
            $('#idCarts').empty();
            carts.forEach(function (cart) {
                $('#idCarts').append('<tr>')
                    $('#idCarts').append('<td class="product-thumbnail"><a href="' + "/single-product?idBook=" + cart.book.idBook + '"><img src="/images/product/sm-3/1.jpg" alt=""></a></td>');
                    $('#idCarts').append('<td class="product-name"><a href="' + "/single-product?idBook=" + cart.book.idBook + '">' + cart.book.title + '</a></td>')
                    $('#idCarts').append('<td class="product-price"><span class="amount">' + "$" + cart.book.stock.price + '</span></td>');
                    $('#idCarts').append('<td class="product-stock-status"><input type="number" value="'+ cart.quantity + '">'+'</input></td>');
                    $('#idCarts').append('<td class="product-subtotal">' + "$" + cart.subTotal + '</td>');
                    $('#idCarts').append('<td class="product-remove"> <button onclick="deleteCartById('+ cart.idCart +')" >X</button></td>')
                $('#idCarts').append('</tr>')
            })
        }
    })
}

function deleteCartById(id) {
    console.log("asdas");
    $.ajax({
        type: "DELETE",
        url: "/cart/delete-cart",
        data: {id: id},
        success: function () {
            getCarts();
        }

    })
}

