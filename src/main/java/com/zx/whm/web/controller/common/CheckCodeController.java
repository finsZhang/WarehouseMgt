package com.zx.whm.web.controller.common;

import com.zx.whm.common.util.AjaxUtil;
import com.zx.whm.web.util.VerifyCodeUtils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-3-18
 * Time: 下午4:58
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/com/zx/whm/common/checkCode/")
public class CheckCodeController {

    private Logger logger = LoggerFactory.getLogger(CheckCodeController.class);
    @RequestMapping("/code")
    public void getCode(HttpServletResponse response, String page, HttpSession httpSession)
            throws IOException {
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        VerifyCodeUtils.outputImage(120, 40, response.getOutputStream(), verifyCode);
        httpSession.setAttribute("sessionCheckCode_" + page , verifyCode);
        logger.info("sessionCheckCode_" + page + ":" + verifyCode);
    }

    @RequestMapping("validate.ajax")
    @ResponseBody
    public JSONObject validate(String checkCode, String page, HttpSession httpSession){
        String sessionCheckCode = (String) httpSession.getAttribute("sessionCheckCode_" + page);
        logger.info("sessionCheckCode:"+sessionCheckCode);
        logger.info("checkCode:"+checkCode);
        if(sessionCheckCode != null && sessionCheckCode.toLowerCase().equals(checkCode.toLowerCase())){
            return AjaxUtil.success("");
        }
        return AjaxUtil.failure("");
    }
}