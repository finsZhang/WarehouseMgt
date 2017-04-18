package com.zx.whm.web.controller;

import com.mysql.jdbc.StringUtils;
import com.zx.whm.common.domain.ResultDTO;
import com.zx.whm.common.util.AjaxUtil;
import com.zx.whm.domain.SysDictitem;
import com.zx.whm.domain.SysDictitemPK;
import com.zx.whm.domain.SysUser;
import com.zx.whm.service.SysDictitemSV;
import com.zx.whm.service.SysUserSV;
import com.zx.whm.vo.SysDictitemVo;
import com.zx.whm.vo.SysUserVo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Walter on 2017/4/17.
 */
@Controller
@RequestMapping("/dictMgt/")
public class SysMgtContoller {

    @Autowired
    private SysDictitemSV sysDictitemSV;


    @RequestMapping("list.html")
    public String init() {
        return "dictMgt/list";
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


    @RequestMapping("edit.html")
    public String edit(@RequestParam(value="dictName",required=false)String dictName,@RequestParam(value="itemNo",required=false)String itemNo, Map map)throws Exception {
        if(!(StringUtils.isNullOrEmpty(itemNo)||StringUtils.isNullOrEmpty(dictName))){
            SysDictitemPK pk = new SysDictitemPK();
            pk.setDictName(dictName);
            pk.setItemNo(itemNo);
            SysDictitem dictBean = sysDictitemSV.findByDictByPk(pk);
            if (dictBean!=null){
                map.put("bean",dictBean);
            }
        }
        return "dictMgt/edit";
    }

    @RequestMapping("/saveDict.ajax")
    @ResponseBody
    public Map saveDict(@RequestBody SysDictitem bean) {
        Map<String, String> map = new HashMap();
        map.put("ERRCODE", "0");
        try {
            bean.setCreateDate(new Timestamp(System.currentTimeMillis()));
            sysDictitemSV.saveDict(bean);
        } catch (Exception e) {
            map.put("ERRCODE","1");
            map.put("ERRINFO","保存失败，请检查参数");
        }
        return map;
    }

    @RequestMapping("/dictDel.ajax")
    @ResponseBody
    public Map dictDel(@RequestParam String dictName,@RequestParam String itemNo) throws Exception {
        Map map = new HashMap();
        SysDictitemPK pk = new SysDictitemPK();
        pk.setItemNo(itemNo);
        pk.setDictName(dictName);
        sysDictitemSV.delete(pk);
        map.put("ERRCODE", 0);
        return map;
    }


}
