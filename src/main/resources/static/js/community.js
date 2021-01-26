function post(){
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    if(!content){
        alert("内容不能为空");
        return ;
    }
    console.log(questionId);
    console.log(content);
    var data= {
        "parentId": questionId,
        "content": content,
        "type": 1
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
                         window.open("https://github.com/login/oauth/authorize?client_id=7491061646038a245120&scope=user&state=1");
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
        console.log(222);
    }else{
        //展开二级评论
        comments.addClass("in");
        //标记评论展开状态
        e.setAttribute("data-collapse","in");
        e.classList.add("active")
        console.log(e.getAttribute("data-collapse")+'11111222');
    }

}
