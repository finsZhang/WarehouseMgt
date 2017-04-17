package com.zx.whm.web.controller;

import com.zx.whm.common.domain.ResultDTO;
import com.zx.whm.common.util.AjaxUtil;
import com.zx.whm.domain.SysDictitem;
import com.zx.whm.domain.SysUser;
import com.zx.whm.service.SysDictitemSV;
import com.zx.whm.service.SysUserSV;
import com.zx.whm.vo.SysDictitemVo;
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
@RequestMapping("/dictMgt/")
public class SysMgtContoller {

    @Autowired
    private SysDictitemSV sysDictitemSV;
    @RequestMapping(value = "/page/{one}.html")
    public String getPage(@PathVariable String one, @RequestParam(value="id",required=false) String id, Map map) {
        map.put("id",id);
        return "dictMgt/" + one;
    }

    @RequestMapping("/querySysDictList.ajax")
    @ResponseBody
    public JSONObject queryMakeCardList(@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "rows", defaultValue = "10") int rows,
                                        SysDictitemVo sysDictitemVo) throws Exception {

        ResultDTO<SysDictitem> result = new ResultDTO<SysDictitem>(page,rows);
        result = sysDictitemSV.queryPageList(sysDictitemVo, result);
        return AjaxUtil.jqGridJson(result);
    }
}
