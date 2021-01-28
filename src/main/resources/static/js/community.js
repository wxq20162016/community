function post(){
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    comment2target(questionId,1,content);
}

function comment2target(targetId,type,content){

    if(!content){
        alert("内容不能为空");
        return ;
    }
    var data= {
        "parentId": targetId,
        "content": content,
        "type": type
    };

    $.ajax({
        url:"/comment",
        method:"post",
        //把请求数据格式化成json格式，可以用浏览器调试模式查看Request Payload是否为json格式
        data: JSON.stringify(data),
        contentType:"application/json",
        success:function (res){
            if(res.code==200){
                window.location.reload();
                //   $("#comment_section").hide();
            }else {
                if(res.code==2003){
                    var isAccepted = confirm(res.message);
                    if(isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=c1613313d2e5b5169a96&scope=user&state=1");
                        window.localStorage.setItem("closable",true);
                    }
                }else{
                    alert(res.message);
                }

            }
            console.log(res);
        }
    });

}
function comment(e){
    var commentId=e.getAttribute("data-id");
    var content=$("#input-"+commentId).val();
    comment2target(commentId,2,content);
}

/*
展开二级评论
 */
function collapseComments(e){
    var id=e.getAttribute("data-id");
    var comments = $("#comment-"+id);
    console.log(comments);
    //获取二级 评论状态的展开状态
    var collapse = e.getAttribute("data-collapse");
    if(collapse){
        //折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    }else{
        var subCommentContainer=$("#comment-"+id);
        if(subCommentContainer.children().length != 1){
            //展开二级评论
            comments.addClass("in");
            //标记评论展开状态
            e.setAttribute("data-collapse","in");
            e.classList.add("active")
            // console.log(e.getAttribute("data-collapse")+'11111222');
        }else{
            $.getJSON( "/comment/"+id, function( data ) {
                console.log(data);

                $.each( data.data.reverse(), function(index,comment) {
                    console.log(comment);
                    var avatarElement=$("<img/>",{
                        "class":"media-object img-thumbnail",
                        "src":"/img/tx.jpg",
                        "style":"width: 100px;height: 100px;"
                    });
                    var mediaLeftElement=$("<div/>",{
                        "class":"media-left"
                    }).append(avatarElement);
                    var mediaBodyElement=$("<div/>",{
                        "class":"media-body"
                    }).append($("<h3/>",{
                        "class":"media-heading",
                        "style":"margin-top: 19px;",
                        "html":comment.user.name
                    })).append($("<div/>",{
                        "html":comment.content,
                    })).append($("<div/>",{
                        "class":"menu"
                    }).append($("<span/>",{
                        "class":"comment-span",
                        "html":moment(comment.gmtCreate).format('YYYY-MM-DD HH:mm')
                    })));
                    var mediaElement=$("<div/>",{
                        "class":"media",
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement= $("<div/>",{
                        "class":"col-lg-12 col-md-12 col-sm-12 col-xs-12 comments",
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
                //展开二级评论
                comments.addClass("in");
                //标记评论展开状态
                e.setAttribute("data-collapse","in");
                e.classList.add("active")

            });
        }

    }
}
