jQuery(function($){
    if(!placeholderSupport()){   // 判断浏览器是否支持 placeholder
        $('[placeholder]').each(function(){
            var input = $(this);
            if(input.attr("type")=="password"){
                input.attr("dbtype","password");
                input.attr("type","text");
            }
        });

        $('[placeholder]').focus(function() {
            var input = $(this);
            var spanplaceholder=$("#spanplaceholder");
            if(input.attr("dbtype")=="password"){
                input.attr("type","password");
            }

            if (spanplaceholder) {
                $("#spanplaceholder").remove();
            }
        }).blur(function() {
                var input = $(this);
                if (input.val() == '' || input.val() == input.attr('placeholder')) {
                    if(input.attr("type")=="password"){
                        input.attr("type","text");
                    }
                    input.after($("<span style='color: #858585;position: absolute;left: 10px;margin:7px 0 0 20px;' id='spanplaceholder'>"+input.attr('placeholder')+"</span>"));
                }
            }).blur();
    }
})
function placeholderSupport() {
    return 'placeholder' in document.createElement('input');
}