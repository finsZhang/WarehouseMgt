$(function(){

    var currentMenu = $("#currentMenuId").val();
    if(com.ai.bdx.util.isNotEmpty(currentMenu)){
        $(".menu > ul > li > a").each(function(){
            if(currentMenu == $(this).attr("menuId")){
                $(this).addClass("current");
            }else {
                $(this).removeClass("current");
            }
        })
    }

    $(".menu > ul > li > a").each(function(){
        $(this).click(function(){
            var menuId = $(this).attr("menuId");
            var url = $(this).attr("url");
            if(com.ai.bdx.util.isNotEmpty(menuId)){
                com.ai.bdx.util.hrefPost(url,["menuId",menuId]);
            }
        })
    })

});