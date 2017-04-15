package com.zx.whm.web.controller;

import com.zx.whm.common.domain.ResultDTO;
import com.zx.whm.common.util.AjaxUtil;
import com.zx.whm.common.util.cache.DictCache;
import com.zx.whm.domain.ShipmentRecord;
import com.zx.whm.domain.SysDictitem;
import com.zx.whm.service.ShipmentRecordSV;
import com.zx.whm.vo.ShipmentRecordVo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: zhangfz3
 * Date: 2017/4/15
 * Time: 13:20
 * Tel:18665598790
 * Email:zhangfz3@asiainfo.com
 */
@Controller
@RequestMapping("/shipmentMgt/")
public class ShipmentController {
    @Autowired
    private ShipmentRecordSV shipmentRecordSV;
    @RequestMapping(value = "/page/{one}.html")
    public String getPage(@PathVariable String one, @RequestParam(value="id",required=false) String id, Map map) {
        map.put("id",id);
        return "dataMgt/shipmentMgt/" + one;
    }
    @RequestMapping("/queryShipmentList.ajax")
    @ResponseBody
    public JSONObject queryMakeCardList(@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "rows", defaultValue = "10") int rows,
                                        ShipmentRecordVo shipmentRecordVo) throws Exception {

        ResultDTO<ShipmentRecord> result = new ResultDTO<ShipmentRecord>(page,rows);
        result = shipmentRecordSV.queryPageList(shipmentRecordVo, result);
        return AjaxUtil.jqGridJson(result);
    }
}
