package com.zx.whm.web.controller;

import com.zx.whm.common.util.Constants;
import com.zx.whm.common.util.JsonUtil;
import com.zx.whm.common.util.MD5;
import com.zx.whm.domain.SysUser;
import com.zx.whm.service.SysUserSV;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-3-13
 * Time: 下午7:56
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/whm/")
public class LoginController {
    @Autowired
    private SysUserSV sysUserSV;
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("login.html")
    public String login(Model model, String errorCode, String errorMessage){
		model.addAttribute("errorCode",errorCode);
        model.addAttribute("errorMessage",errorMessage);
        return "login";
    }

    /**
     * 用户登录，此方法目前只支持普通登录，不支持手机验证码登录
     * TODO 1、前台需要对密码需要加密处理
     * TODO 2、将用户名及密码写入Cookie
     * @param accountName
     * @param passwd
     * @param model
     * @return
     */
    @RequestMapping("index.html")
    public String loginCheck(String accountName, String passwd, Model model, HttpServletRequest request){
        logger.debug("loginCheck");
        String errorCode= "0";
        String errorMessage = "登陆成功";
      if(request.getSession().getAttribute(Constants.SESSION_USER_OBJ)!=null&&!(request.getSession().getAttribute(Constants.SESSION_USER_OBJ).equals("null"))){
          return "index";
      }
     SysUser user = sysUserSV.findSysUserByUserName(accountName);
      if(user==null){
          model.addAttribute("errorCode",1);
          model.addAttribute("errorMessage","用户名不存在");
          return "redirect:/whm/login.html";
      }else if (user.getStatus()!=1) {//用户已禁用
          model.addAttribute("errorCode",9);
          model.addAttribute("errorMessage","用户已禁用，无法登陆");
          return "redirect:/whm/login.html";
      }else if(!user.getPassword().equals(MD5.toMD5(passwd))){
          model.addAttribute("errorCode",2);
          model.addAttribute("errorMessage","密码错误");
          return "redirect:/whm/login.html";
      }

        request.getSession().setAttribute(Constants.SESSION_USER, JsonUtil.toJson(user));
        request.getSession().setAttribute(Constants.SESSION_USER_OBJ, user);
        request.getSession().setAttribute(Constants.SESSION_OPERATOR_STATION, user.getStationCode());
        request.getSession().setAttribute(Constants.SESSION_OPERATOR_ROLE, user.getRoleCode());

        String VERSION = DateFormatUtils.format(new Date(System.currentTimeMillis()), "yyyyMMddHHmmss");
        request.getSession().setAttribute("staticVersion",VERSION);
        request.getSession().setAttribute(Constants.LOGIN_TIME_OUT, "false");
        request.getSession().setAttribute(Constants.LOGINPARAM, "false");
        request.getSession().setAttribute(Constants.VIEWONLY, "false");
        return "index";
    }
    /**
     * 退出
     *
     * @return
     */
    @RequestMapping("logout.html")
    public String loginOut(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        request.getSession().invalidate();
        return "login";
    }

    /**
     * 退出
     *
     * @return
     */
    @RequestMapping("ModifyPassword.html")
    public String ModifyPassword(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        return "/logMgr/ModifyPassword";
    }
    @RequestMapping("PasswordChange.ajax")
    @ResponseBody
    public Map PasswordChange(String passwordOld,String passwordNew,HttpServletRequest request) throws IOException {
        Map<String,String> map = new HashMap<>();
        // 先获取当前登陆用户
        SysUser userSession = (SysUser) request.getSession().getAttribute(Constants.SESSION_USER_OBJ);
        SysUser user = sysUserSV.findSysUserByUserName(userSession.getUserName());
        if(!MD5.toMD5(passwordOld).equals(user.getPassword())){
            map.put("code","1");
            map.put("message","原密码输入错误!");
        }
        user.setPassword(MD5.toMD5(passwordNew));
        sysUserSV.save(user);
        map.put("code","0");
        map.put("message","密码修改成功!");

        return map;
    }
}
