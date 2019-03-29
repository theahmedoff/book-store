$(function () {
    $('#idBlogComments').click(function () {
        var idBlog = $('#idBlog').val();
        getCommentsByIdBlog(idBlog);
    });
});

function getCommentsByIdBlog(idBlog) {
    $.ajax({
        type: "GET",
        url: "/get-reviews-by-idBlog",
        data: {idBlog: idBlog},
        dataType: "JSON",
        success: function (reviews) {
            $('#idDivBlogComments').empty();
            reviews.forEach(function (review) {
                $('#idDivBlogComments').append('<li>\n' +
                    '                      <div class="wn__comment">\n' +
                    '                        <div class="content">\n' +
                    '                          <div class="comnt__author d-block d-sm-flex">\n' +
                    '                            <span>Review by ' + review.user.name + ' ' + review.user.surname + '</span>\n' +
                    '                            <span>' + review.shareDate + '</span>\n' +
                    '                          </div>\n' +
                    '                          <p>' + review.desc + '</p>\n' +
                    '                        </div>\n' +
                    '                      </div>\n' +
                    '                    </li>');
            });
        }
    });
}

function addBlogReview() {
    var idBlog = $('#idBlog').val();
    var desc = $('#idDescReview').val();

    $.ajax({
        type: "POST",
        url: "/add-blog-review",
        data: {idBlog: idBlog, desc: desc},
        success: function () {
            $('#idDescReview').val('');
            $('#idBlogComments').trigger('click');
        }, error: function () {
            alert('Internal error!');
        }
    });
}