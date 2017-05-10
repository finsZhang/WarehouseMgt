package com.zx.whm.web.controller;

import com.zx.whm.common.domain.ResultDTO;
import com.zx.whm.common.util.AjaxUtil;
import com.zx.whm.common.util.ExportExcelUtil;
import com.zx.whm.common.util.ExportExcelUtils;
import com.zx.whm.common.util.JsonUtil;
import com.zx.whm.service.ReportSV;
import com.zx.whm.service.ShipmentRecordSV;
import com.zx.whm.service.SysUserSV;
import com.zx.whm.vo.ShipmentRecordStats;
import com.zx.whm.vo.ShipmentRecordTotalVo;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Walter on 2017/4/20.
 */
@Controller("ReportController")
@RequestMapping("/reportMgt/")
public class ReportController {

    @Autowired
    private ReportSV reportSV;

    @Autowired
    private SysUserSV sysUserSV;

    @Autowired
    private ShipmentRecordSV shipmentRecordSV;

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
        String dispatchClerk = shipmentRecordTotalVo.getDispatchClerk();
        String dispatchClerkName = shipmentRecordTotalVo.getDispatchClerkName();
        String[]dispatchClerks = dispatchClerk.split(",");
        String[]dispatchClerkNames = dispatchClerkName.split(",");
        String[]columnNames ={"日期","星期","当日编号","类型","商品明细","金额（元）","行数","付款方式","客户姓名，联系方式","备注","派单员","后期更改"};
        String[]propertyNames={"createDate","weekNo","dayNo","type","prodDetail","amount","lineNum","payType","custInfo","comment","dispatchClerk","modifyConent"};
        String[] sumColum = {"amount"};
        String[] intColum = {""};
        String title= null;
        HSSFWorkbook wb = new HSSFWorkbook();
        ExportExcelUtils.initStyle(wb);
        HSSFSheet sheet;
        for(int i=0;i<dispatchClerks.length;i++){
            title = dispatchClerkNames[i];
            List shipmentRecordStats = shipmentRecordSV.getRecordsByCreatorUserName(dispatchClerks[i]);


            sheet = wb.createSheet(title);
            ExportExcelUtils.exportExcelForTotal(wb,sheet,title, columnNames,propertyNames,sumColum,shipmentRecordStats, response,intColum,"amount");
        }

        response.reset();
        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode("仓库出货报表统计.xls", "UTF-8"));
        OutputStream os=response.getOutputStream();
        wb.write(os);
        os.flush();
        os.close();
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
