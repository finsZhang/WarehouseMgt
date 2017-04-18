package com.zx.whm.web.controller;

import com.zx.whm.common.domain.ResultDTO;
import com.zx.whm.common.util.AjaxUtil;
import com.zx.whm.domain.ShipmentRecord;
import com.zx.whm.domain.SysUser;
import com.zx.whm.service.ShipmentRecordSV;
import com.zx.whm.service.SysUserSV;
import com.zx.whm.vo.ShipmentRecordVo;
import com.zx.whm.vo.SysUserVo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Walter on 2017/4/17.
 */
@Controller
@RequestMapping("/userMgt/")
public class UserController {

    @Autowired
    private SysUserSV sysUserSV;
    @RequestMapping(value = "/page/{one}.html")
    public String getPage(@PathVariable String one, @RequestParam(value="id",required=false) String id, Map map) {
        map.put("id",id);
        return "userMgt/" + one;
    }

    @RequestMapping("/querySysUserList.ajax")
    @ResponseBody
    public JSONObject queryMakeCardList(@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "rows", defaultValue = "10") int rows,
                                        SysUserVo sysUserVo) throws Exception {

        ResultDTO<SysUser> result = new ResultDTO<SysUser>(page,rows);
        result = sysUserSV.queryPageList(sysUserVo, result);
        return AjaxUtil.jqGridJson(result);
    }

    @RequestMapping("/saveUser.ajax")
    @ResponseBody
    public Map saveTerminal(@RequestBody SysUser bean) {
        Map<String, String> map = new HashMap();
        map.put("ERRCODE", "0");
        bean.setStatus(0);//00有效01无效
        try {
            sysUserSV.saveUser(bean);
        } catch (Exception e) {
            map.put("ERRCODE","1");
            map.put("ERRINFO","保存失败，请检查参数");
        }
        return map;
    }

    @RequestMapping("/userDel.ajax")
    @ResponseBody
    public Map userDel(@RequestParam long id) throws Exception {
        Map map = new HashMap();
        sysUserSV.deleteUserById(id);
        map.put("ERRCODE", 0);
        return map;
    }
}
