package com.zx.whm.web.controller;

import com.zx.whm.common.domain.ResultDTO;
import com.zx.whm.common.util.AjaxUtil;
import com.zx.whm.common.util.ExportExcelUtil;
import com.zx.whm.common.util.JsonUtil;
import com.zx.whm.service.ReportSV;
import com.zx.whm.service.SysUserSV;
import com.zx.whm.vo.ShipmentRecordTotalVo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private SysUserSV sysUserSV;

    @RequestMapping("list.html")
    public String init() {
        return "reportMgt/list";
    }

    @RequestMapping("/queryShipmenRcdAllList.ajax")
    @ResponseBody
    public JSONObject queryMakeCardList(@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "rows", defaultValue = "10") int rows,
                                        ShipmentRecordTotalVo shipmentRecordTotalVo) throws Exception {

        ResultDTO result = new ResultDTO(page,rows);
        result = reportSV.queryPageList(shipmentRecordTotalVo, result);
        return AjaxUtil.jqGridJson(result);
    }


    //导出汇总记录
    @RequestMapping("/exportRecordTotalRcds.html")
    public void exportExcelIn(HttpServletResponse response,  ShipmentRecordTotalVo shipmentRecordTotalVo)throws Exception{

        String title="汇总记录表";
        String[]  head={"日期","星期","派单总数","派单金额","派单人"};
        String[]  headColumn={"createDate","weekNo","num","amt","dispatchClerk"};
        String[] sumColum = {"amt"};
        String[] intColum = {"num"};
        try{
            List<ShipmentRecordTotalVo> result=reportSV.queryReportList(shipmentRecordTotalVo);
            ExportExcelUtil excel=new ExportExcelUtil();
            //excel.exportExcelForTotal(title,head,headColumn,sumColum,result,response,intColum,"amt");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //获取用户下拉列表
    @RequestMapping("/getSysUserList.ajax")
    @ResponseBody
    public Map getSysUserList() throws Exception {
        Map map = new HashMap();
        map.put("userList", sysUserSV.findAllUser());
        return map;
    }

}
