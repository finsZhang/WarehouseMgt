$(document).ready(function () {
    jQuery.validator.addMethod("isIdCardNo", function (value, element) {
        return this.optional(element) || isIdCardNo(value);
    }, "请正确输入您的身份证号码");
    
    jQuery.validator.addMethod("dateYMD", function (value, element) {
        return this.optional(element) || isDateYMD(value);
    }, "日期格式错误");
    
    jQuery.validator.addMethod("isMobilePhoneNumber", function (value, element) {
        return this.optional(element) || isMobilePhoneNumber(value);
    }, "手机号码格式错误");
    //number(9,3)
    jQuery.validator.addMethod("numFormat30_2",function(value,element){
            return this.optional(element) || /^[0-9]{1,30}(\.|)(\d{0,2})$/.test(value);
        },$.validator.format("请输入合法数字,精度格式1-30位整数0-2位小数")
    );
});
/**
 * 手机号码
 * @param {Object} value
 * @return {TypeName} 
 */
function isMobilePhoneNumber(value){
	   var myreg = /^[0-9]{11}$/;
        if(!myreg.test(value))
        {
            return false;
        }else{
        	return true;
        }
}

/** 根据身份证得出性别和出生日期*/
function getData(id,birthday,sexcode){
	   var ido=document.getElementById(id);
	   var bd=document.getElementById(birthday);
	   var sex=document.getElementById(sexcode);
	   if(!/^\d{6}((?:19|20)((?:\d{2}(?:0[13578]|1[02])(?:0[1-9]|[12]\d|3[01]))|(?:\d{2}(?:0[13456789]|1[012])(?:0[1-9]|[12]\d|30))|(?:\d{2}02(?:0[1-9]|1\d|2[0-8]))|(?:(?:0[48]|[2468][048]|[13579][26])0229)))\d{2}(\d)[xX\d]$/.test(ido.value)){
	      alert('身份证号非法!');
	      return;
	   }
	   
	   bd.value=(RegExp.$1).substr(0,4)+'-'+(RegExp.$1).substr(4,2)+'-'+(RegExp.$1).substr(6,2);
	   sex.value=(ido.value.substr(14,3)%2==0)?'2':'1';
	   //sex.value=(parseInt(RegExp.$2)%2==0?'2':'1');
	}

/**
 * 验证yyyy-MM-DD
 * @param {Object} num
 * @return {TypeName} 
 */
function isDateYMD(value){
	
	var reg=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/; 
	if(!value.match(reg)){ 
	    return false;
	}else{ 
		return true;
    } 

}
//增加身份证验证
function isIdCardNo(num) {
    var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
    var parityBit = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
    var varArray = new Array();
    var intValue;
    var lngProduct = 0;
    var intCheckDigit;
    var intStrLen = num.length;
    var idNumber = num;
    // initialize
    if ((intStrLen != 15) && (intStrLen != 18)) {
        return false;
    }
    // check and set value
    for (i = 0; i < intStrLen; i++) {
        varArray[i] = idNumber.charAt(i);
        if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
            return false;
        } else if (i < 17) {
            varArray[i] = varArray[i] * factorArr[i];
        }
    }

    if (intStrLen == 18) {
        //check date
        var date8 = idNumber.substring(6, 14);
        if (isDate8(date8) == false) {
            return false;
        }
        // calculate the sum of the products
        for (i = 0; i < 17; i++) {
            lngProduct = lngProduct + varArray[i];
        }
        // calculate the check digit
        intCheckDigit = parityBit[lngProduct % 11];
        // check last digit
        if (varArray[17] != intCheckDigit) {
            return false;
        }
    }
    else {        //length is 15
        //check date
        var date6 = idNumber.substring(6, 12);
        if (isDate6(date6) == false) {
            return false;
        }
    }
    return true;
}

function isDate6(sDate) {
    if (!/^[0-9]{6}$/.test(sDate)) {
        return false;
    }
    var year, month, day;
    year = sDate.substring(0, 4);
    month = sDate.substring(4, 6);
    if (year < 1700 || year > 2500) return false
    if (month < 1 || month > 12) return false
    return true
}
/**
* 判断是否为“YYYYMMDD”式的时期
*
*/
function isDate8(sDate) {
    if (!/^[0-9]{8}$/.test(sDate)) {
        return false;
    }
    var year, month, day;
    year = sDate.substring(0, 4);
    month = sDate.substring(4, 6);
    day = sDate.substring(6, 8);
    var iaMonthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    if (year < 1700 || year > 2500) return false
    if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1] = 29;
    if (month < 1 || month > 12) return false
    if (day < 1 || day > iaMonthDays[month - 1]) return false
    return true
}