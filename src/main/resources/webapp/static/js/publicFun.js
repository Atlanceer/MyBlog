$(document).ready(function () {
    $.ajax({
        url:"../adminUser/userInf",
        type:"GET",
        dataType:"json",
        success:function (data) {
            $("#userHead").append(data.content.username);
            $("#avatarUrl").attr("src",data.content.avatar);
        }
    });
});
$.extend({
    demo:function () {
        alert("test jquery function");
    }
});
