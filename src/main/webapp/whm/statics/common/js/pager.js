(function(window,undefined){
	var $ = window.$;
	var _Pager = window.Pager;
	var document = window.document;
	
    /**
     * page 工具方法
     */
    var tool = {
    	/**
    	 * 根据setting计算总分页数量
    	 * @param setting {Object}
    	 */
    	totalPage : function(setting) {
    		return Math.ceil(setting.base.totalRecord/setting.base.pageSize) || 1;
    	},
        
        /**
         * ajax方式请求数据
         * @param pager
         * @param begin
         * @param end
         */
        ajaxAction :  function(pager,begin,end) {
        	var setting = pager.setting;
        	var pagerInfo = "startNum=" + begin + "&endNum=" + end;
            var url = (setting.async.url.indexOf("?") >0) ? 
            		(setting.async.url + "&" + pagerInfo) : (setting.async.url + "?" + pagerInfo);
           	url = url+"&_t="+(new Date()).getTime();
            $.ajax({
            	"type": "GET",
            	"url": url,
            	"dataType": "html",
            	"async" : "false",
            	success : function(data) {
            		$(setting.base.divShow).find("table").replaceWith(data);
            		var totalRecord = $("#resultTotal").val();
                    setting.base.totalRecord = totalRecord;
                    pager.updateShow();
                    if(typeof setting.async.callback == 'function') {
                    	setting.async.callback(pager,begin,end);
                    }
            	}
            });
        },
        
        /**
         * 一次展示所有数据方式显示数据
         * @param pager
         * @param begin
         * @param end
         */
        defaultAction : function(pager,begin,end) {
        	var setting = pager.setting;
        	$(setting.base.divShow).find("tr:gt(0)").hide();
        	$(setting.base.divShow).find("tr:gt(" + begin + "):lt(" + setting.base.pageSize + ")").show();
        	pager.updateShow();
        },
        
        init : function(pager) {
        	$("#pagerDiv").remove();
        	var divShow = pager.setting.base.divShow;
        	//分页显示的html
        	var html = '<div id="pagerDiv" class="fenye-bk" style="display : none;">' +
                        '<a href="javascript:pager.firstPage();" title="首页" class="fenye-normalBg fenye-huise">首页</a>'  +
                        '<a href="javascript:pager.previousPage();" title="上一页" class="fenye-normalBg fenye-huise">上一页</a>'  +
                        '<a href="javascript:pager.forwordPage();" title="下一页" class="fenye-normalBg fenye-huise">下一页</a>'  +
                        '<a href="javascript:pager.lastPage();" title="尾页" class="fenye-normalBg fenye-huise">尾页</a>'  +
                        '<span>跳转到&nbsp;&nbsp;</span>'  +
                        '<input name="" class="fenye-srk" onkeyup = "javascript:pager.checkInput(this)" type="text" id="jumpPage"/>'+
                        '<span>&nbsp;&nbsp;页</span>'+
                        '<button class="fenye-tiaoz" onclick="javascript:pager.jumpPage();">跳转</button>'+
                        '<span>&nbsp;&nbsp;<span id="curPage">1</span>/<span id="totalPage">1</span>&nbsp;&nbsp;</span>'  +
                        '<span>共&nbsp;<span id="totalRecord">0</span>&nbsp;条记录</span>'  +
                    '</div>';
        	$(divShow).after(html);
        },
        
        /**
         * 默认导出方法
         * @param pager
         */
        defaultExp : function(pager) {
        	var exp = pager.setting.exp;
			var params = $.param({"colKey":exp.colKey,"colName":exp.colName});
			var url = (exp.url.indexOf("?") >0) ? 
            		(exp.url + "&" + params) : (exp.url + "?" + params);
			var frm = $(exp.frm).get(0);
			frm.action = url;
			frm.method = "POST";
			frm.submit();
        },
        
        /**
         * 默认方法，初始化colKey,colName
         * @param pager
         */
        initExpCol : function(pager) {
        	var divShow = pager.setting.base.divShow;
        	var exp = pager.setting.exp;
			var ths = $(divShow).find("th");
			var th = null;
			for(var i = 0; i < ths.length; i++) {
				th = ths.eq(i);
				if(!th.attr("col"))
					continue;
				exp.colKey.push(th.attr("col"));
				exp.colName.push(th.text());
			}
        }
    };
    
    var Pager = function () {
    };
    
    /**
     * 默认配置
     */
    var _setting = {
		base : {
			divShow : null,
			curPage : 1,
			pageSize : 10,
			totalRecord : 0,
			pageSizeDiv:0,   //对分页底下样式做修改
			totalPage : 1
		},
		async : {
			enable : false,
			url : "",
			dataType : "html",
			type : "get",
			callback : null
		},
		exp : {
			url : "",
			frm : null,
			initExpCol : tool.initExpCol, /*初始化colKey+colName*/
			colKey : [],
			colName : []
		}
	};
    
	Pager.prototype = {
			
		setting : $.extend(true,{},_setting),
		
		/**
		 * 初始化setting值
		 * @param setting
		 */
		init : function (setting) {
			$.extend(true,this.setting,setting);
			tool.init(this);
		},
		
		extend : function(setting) {
			$.extend(true,this.setting,setting);
		},
		
		/**
		 * 导出全部,初始化colKey+colName --》 导出
		 * @param form
		 * @param url
		 */
		expAll : function() {
			var exp = this.setting.exp;
			exp.initExpCol(this);
			tool.defaultExp(this);
		},
		
		/**
	     * @description: update显示效果
	     */
	    updateShow : function() {
	    	var setting = this.setting;
	    	setting.base.totalPage = tool.totalPage(setting);
	    	$("#curPage").text(setting.base.curPage);
	    	$("#totalRecord").text(setting.base.totalRecord);
	    	$("#totalPage").text(setting.base.totalPage);
	    },
	    
	    /**
	    * @description:去往第N页
	    */
	    gotoPage:function(currentPage){
	    	var setting = this.setting;
	    	if(!isNaN(currentPage)){
	    		setting.base.curPage=currentPage;
	    	}
	    	if(setting.base.curPage > setting.base.totalPage) {
	    		setting.base.curPage = setting.base.totalPage;
	    	} else if (setting.base.curPage < 1) {
	    		setting.base.curPage = 1;
	    	}
	        var begin = (setting.base.curPage -1 ) * setting.base.pageSize + 1;
	        var end = setting.base.curPage*setting.base.pageSize;
	        if(setting.async.enable) {
	        	tool.ajaxAction(this,begin,end);
	        } else {
	        	tool.defaultAction(this,begin,end);
	        }
	    },
	    
	    /**
	    * @description: 第一页
	    */
	    firstPage : function(){
	        this.setting.base.curPage = 1;
	        this.gotoPage();
	    },
	    
	    /**
	    * @description: 最后一页
	    */
	    lastPage : function(){
	        this.setting.base.curPage = this.setting.base.totalPage;
	        this.gotoPage();
	    },
	    
	    /**
	    * @description: 后一页
	    */
	    forwordPage : function(){
	        this.setting.base.curPage++;
	        this.gotoPage();
	    },
	    
	    /**
	    * @description: 前一页
	    */
	    previousPage : function(){
	        this.setting.base.curPage--;
	        this.gotoPage();
	    },
	    
	    jumpPage : function() {
	    	var currentPage = $("#jumpPage").val();
	    	this.gotoPage(currentPage);
	    },
	    
	    checkInput : function(obj){
	    	var setting = this.setting;
		    var totalNum = setting.base.totalPage;
		    if($(obj).val().length > 3){
		        $(obj).val($(obj).val().substring(0, 3));
		    }
		    $(obj).val($(obj).val().replace(/[^\d]/g,"").replace(/^0/g,""));
		    if(parseInt($(obj).val()) > parseInt(totalNum) ){
		        $(obj).val(totalNum);
		    }
	    }
	};
	
	window._pager = window.pager;
	
	window.pager = new Pager();
})(window,undefined);