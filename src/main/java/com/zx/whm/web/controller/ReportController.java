package com.zx.whm.web.controller;

import com.zx.whm.common.domain.ResultDTO;
import com.zx.whm.common.util.AjaxUtil;
import com.zx.whm.common.util.JsonUtil;
import com.zx.whm.service.ReportSV;
import com.zx.whm.vo.ShipmentRecordTotalVo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Walter on 2017/4/20.
 */
@Controller
@RequestMapping("/reportMgt/")
public class ReportController {

    @Autowired
    private ReportSV reportSV;

    @RequestMapping("list.html")
    public String init() {
        return "reportMgt/list";
    }

    @RequestMapping("/queryShipmenRcdAllList.ajax")
    @ResponseBody
    public JSONObject queryMakeCardList(@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "rows", defaultValue = "10") int rows,
                                        ShipmentRecordTotalVo shipmentRecordTotalVo) throws Exception {

        ResultDTO<ShipmentRecordTotalVo> result = new ResultDTO<ShipmentRecordTotalVo>(page,rows);
        result = reportSV.queryPageList(shipmentRecordTotalVo, result);
        return AjaxUtil.jqGridJson(result);
    }


    // 检查状态下拉列表加载
    @RequestMapping("/getSysUserList.ajax")
    @ResponseBody
    public Map getStudyStatus() throws Exception {
        List<Map> status = new ArrayList<Map>();
        Map tmp = new HashMap();
        for(int index=0;index<3;index++){
            tmp = new HashMap();
            tmp.put("key",index);
            tmp.put("value",index);
            status.add(tmp);
        }
        Map map = new HashMap();
        map.put("SYS_USER", JsonUtil.toJson(status));
        return map;
    }

}
