package com.zx.whm.common.util;

/**
 * 系统常量
 * @author fangll
 *
 */
public final class Constants {
	public static final String SESSION_USER = "_USER_INFO_MAP_";            //存session中管理平台用户信息
	public static final String SESSION_USER_OBJ = "_USER_INFO";            //存session中管理平台用户信息

	public static final String LOGIN_TIME_OUT = "_LOGIN_TIME_OUT_"; //登录过期标识
	public static final String LOGINPARAM = "_LOGIN_PARAM_";        //存session中登录页面参数
	public static final String VIEWONLY = "_VIEW_ONLY_";			//只有查看权限，没有管理权限

	//员工岗位
	public static final String STATION_CODE_COMMON = "COMMON_TELLER";          //普通柜员
	public static final String STATION_CODE_MANAGER = "MANAGER";          //管理员
	public static final String SESSION_OPERATOR_STATION = "_OPERATOR_STATION";
	//员工角色
	public static final String ROLECODE_COMMON = "COMMON_TELLER";          //普通柜员
	public static final String ROLE_CODE_MANAGER = "MANAGER";          //管理员
	public static final String SESSION_OPERATOR_ROLE = "_OPERATOR_ROLE";
}