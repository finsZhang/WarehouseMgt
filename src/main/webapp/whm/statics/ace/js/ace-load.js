
if (!("load" in window)) {
    window.load = {}
}
load.load = function(a) {
    a("<div class=\"load-mask\"></div>").css({ display: "block", width: "100%", height: a(window).height() }).appendTo("body");
    a("<div class=\"load-mask-msg\"></div>").html('<i class="icon-spinner icon-spin icon-2x red"></i>').appendTo("body").css({ display: "block", left: (a(document.body).outerWidth(true) - 20) / 2, top: (a(window).height() - 45) / 2 });
}
load.loadP = function(a,p) {
    a("<div class=\"load-mask\"></div>").css({ display: "block", width: "100%", height: a(p).height() }).appendTo(a(p));
    a("<div class=\"load-mask-msg\"></div>").html('<i class="icon-spinner icon-spin bigger-160 red"></i>').appendTo(a(p)).css({ display: "block", left: (a(p).width()) / 2, top: (a(p).height() - 45) / 2 });
}

load.removeLoad = function(a) {
    a(".load-mask").remove();
    a(".load-mask-msg").remove();
}
load.removeLoadP = function(a,p) {
    a(p).find(".load-mask").remove();
    a(p).find(".load-mask-msg").remove();
}


var FloatFrame = (function() {
    "use strict";

    var elem,
        hideHandler,
        that = {};

    that.init = function(options) {
        elem = $(options.selector);
    };

    that.show = function(text) {
        clearTimeout(hideHandler);

        elem.find("span").html(text);
        elem.delay(200).fadeIn().delay(4000).fadeOut();
    };

    return that;
}());