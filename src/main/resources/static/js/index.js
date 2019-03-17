// $(function () {
//
// });


function getBooksByCategoryType(cateType) {
    $.ajax({
        type: "GET",
        url: "/get-book-by-category-type",
        data: {cateType: cateType},
        dataType: "json",
        success: function (books) {
            $('#bookProduct').empty();
            console.log(books);
            books.forEach(function (book) {
                $('#bookProduct').empty();
                $('#bookProduct').append('<div class="single__product" >\n' +
                    '                            <!-- Start Single Product -->\n' +
                    '                            <div class="col-lg-3 col-md-4 col-sm-6 col-12">\n' +
                    '                                <div class="product product__style--3">\n' +
                    '                                    <div class="product__thumb">\n' +
                    '                                        <a class="first__img" href="single-product.html"><img\n' +
                    '                                                src="/images/product/sm-3/1.jpg" alt="product image"></a>\n' +
                    '                                        <a class="second__img animation1" href="single-product.html"><img\n' +
                    '                                                src="/images/product/sm-3/2.jpg" alt="product image"></a>\n' +
                    '                                        <div class="hot__box">\n' +
                    '                                            <span class="hot-label">BEST SALER</span>\n' +
                    '                                        </div>\n' +
                    '                                    </div>\n' +
                    '                                    <div class="product__content content--center content--center">\n' +
                    '                                        <h4><a href="single-product.html">'+ book.author.fullName +'</a></h4>\n' +
                    '                                        <ul class="prize d-flex">\n' +
                    '                                            <li>$ '+ book.stock.price +'</li>\n' +
                    '                                            <li class="old_prize">$ '+ book.stock.price +'</li>\n' +
                    '                                        </ul>\n' +
                    '                                        <div class="action">\n' +
                    '                                            <div class="actions_inner">\n' +
                    '                                                <ul class="add_to_links">\n' +
                    '                                                    <li><a class="cart" href="cart.html"><i\n' +
                    '                                                            class="bi bi-shopping-bag4"></i></a></li>\n' +
                    '                                                    <li><a class="wishlist" href="wishlist.html"><i\n' +
                    '                                                            class="bi bi-shopping-cart-full"></i></a></li>\n' +
                    '                                                    <li><a class="compare" href="#"><i class="bi bi-heart-beat"></i></a>\n' +
                    '                                                    </li>\n' +
                    '                                                    <li><a data-toggle="modal" title="Quick View"\n' +
                    '                                                           class="quickview modal-view detail-link"\n' +
                    '                                                           href="#productmodal"><i class="bi bi-search"></i></a></li>\n' +
                    '                                                </ul>\n' +
                    '                                            </div>\n' +
                    '                                        </div>\n' +
                    '                                        <div class="product__hover--content">\n' +
                    '                                            <ul class="rating d-flex">\n' +
                    '                                                <li class="on"><i class="fa fa-star-o"></i></li>\n' +
                    '                                                <li class="on"><i class="fa fa-star-o"></i></li>\n' +
                    '                                                <li class="on"><i class="fa fa-star-o"></i></li>\n' +
                    '                                                <li><i class="fa fa-star-o"></i></li>\n' +
                    '                                                <li><i class="fa fa-star-o"></i></li>\n' +
                    '                                            </ul>\n' +
                    '                                        </div>\n' +
                    '                                    </div>\n' +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                            <!-- Start Single Product -->\n' +
                    '                        </div>');
            })

        }
    });
}

$(function() {
    console.log( "Success!" );
   // getAllBook();
});

function getAllBook(){
    $.ajax({
        type:"GET",
        url: "/get-all-books",
        dataType: "HTML",
        success: function (books) {
            // console.log(books);
            $('#nav-all').html(books);
            console.log('done');

        }
    })
}