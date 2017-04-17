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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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


}
