package com.zx.whm.web.controller;

import com.zx.whm.common.domain.ResultDTO;
import com.zx.whm.common.util.AjaxUtil;
import com.zx.whm.common.util.Constants;
import com.zx.whm.domain.ShipmentRecord;
import com.zx.whm.domain.SysUser;
import com.zx.whm.service.ShipmentRecordSV;
import com.zx.whm.vo.ShipmentRecordVo;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
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
    public String getPage(@PathVariable String one, @RequestParam(value="id",required=false) String id, Map map,HttpServletRequest request) {
        map.put("id",id);
        if ("edit".equals(one)) {
            if(id==null){
                SysUser user = (SysUser)request.getSession().getAttribute(Constants.SESSION_USER_OBJ);
                ShipmentRecord shipmentRecord = shipmentRecordSV.getRecordByCreatorUserName(user.getUserName());
                if(shipmentRecord==null){
                    map.put("dayNo","1");
                }else{
                    map.put("dayNo",Integer.parseInt(shipmentRecord.getDayNo())+1);
                }
            }
        }
        return "dataMgt/shipmentMgt/" + one;
    }
    @RequestMapping("/queryShipmentList.ajax")
    @ResponseBody
    public JSONObject queryShipmentList(@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "rows", defaultValue = "10") int rows,
                                        ShipmentRecordVo shipmentRecordVo, HttpServletRequest request) throws Exception {

        ResultDTO<ShipmentRecord> result = new ResultDTO<ShipmentRecord>(page,rows);
        SysUser sysUser = (SysUser) request.getSession().getAttribute(Constants.SESSION_USER_OBJ);
        if(StringUtils.isNotBlank(sysUser.getRoleCode())&&sysUser.getStationCode().equals("COMMON_TELLER")&&sysUser.getStationCode().equals("COMMON_TELLER")){
            shipmentRecordVo.setCreatorUserName(sysUser.getUserName());
        }
        result = shipmentRecordSV.queryPageList(shipmentRecordVo, result);
        return AjaxUtil.jqGridJson(result);
    }

    @RequestMapping("/deleteRecord")
    @ResponseBody
    public Map deleteRecord(@RequestParam long id) throws Exception{
        Map map = new HashMap();
        shipmentRecordSV.delete(id);
        map.put("ERRCODE",0);
        return map;
    }

    @RequestMapping("/getRecord")
    @ResponseBody
    public Map getRecord( @RequestParam long id) throws Exception {
        Map map = new HashMap();
        ShipmentRecord shipmentRecord = shipmentRecordSV.getRecord(id);
        map.put("ERRCODE", "0");
        map.put("RECORD",shipmentRecord);
        return map;
    }

    @RequestMapping("/saveRecord")
    @ResponseBody
    public Map saveRecord(@RequestBody ShipmentRecord bean, HttpServletRequest request) throws Exception {
        shipmentRecordSV.save((SysUser)(request.getSession().getAttribute(Constants.SESSION_USER_OBJ)), bean);
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }

    //导出汇总记录
    @RequestMapping("/exportRecord.ajax")
    public void exportExcelIn(HttpServletRequest request, HttpServletResponse response, @RequestParam Map param)throws Exception{

      /*  ResultDTO<ShipmentRecord> result = new ResultDTO<>(1,99999);
        result = shipmentRecordSV.queryPageList(shipmentRecordVo, result);
        String title="汇总记录表";
        String[]  head={"日期","星期","派单总数","派单金额","派单人"};
        String[]  headColumn={"createDate","weekNo","num","amt","dispatchClerk"};
        String[] sumColum = {"amt"};
        String[] intColum = {"num"};
        try{
            ShipmentRecordTotalVo shipmentRecordTotalVo = new ShipmentRecordTotalVo();
            if(param.get("startDate")!=null){
                shipmentRecordTotalVo.setStartDate(param.get("startDate").toString());
            }
            if(param.get("endDate")!=null){
                shipmentRecordTotalVo.setEndDate(param.get("endDate").toString());
            }
            if(param.get("dispatchClerk")!=null){
                shipmentRecordTotalVo.setDispatchClerk(param.get("dispatchClerk").toString());
            }
            result=reportSV.queryPageList(shipmentRecordTotalVo,result);
            ExportExcelUtil excel=new ExportExcelUtil();
            excel.exportExcelForTotal(title,head,headColumn,sumColum,result.getRows(),response,intColum,"amt");
        }catch(Exception e){
            e.printStackTrace();
        }*/
    }
}
