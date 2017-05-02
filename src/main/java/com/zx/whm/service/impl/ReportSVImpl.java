package com.zx.whm.service.impl;

import com.sun.org.apache.xerces.internal.xs.ShortList;
import com.zx.whm.common.domain.ResultDTO;
import com.zx.whm.dao.CommonDao;
import com.zx.whm.service.ReportSV;
import com.zx.whm.vo.ShipmentRecordPersonTotal;
import com.zx.whm.vo.ShipmentRecordTotalVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Walter on 2017/4/20.
 */
@Service
public class ReportSVImpl implements ReportSV {

    @Autowired
    private CommonDao commonDao;

    public ResultDTO queryPageList(final ShipmentRecordTotalVo shipmentRecordTotalVo, ResultDTO resultDTO) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT c.* ");
        String[]dispatchClerks = null;
        if(StringUtils.isNotBlank(shipmentRecordTotalVo.getDispatchClerk())&&!"-1".equals(shipmentRecordTotalVo.getDispatchClerk())){
            dispatchClerks = shipmentRecordTotalVo.getDispatchClerk().split(",");
            for(int i=0;i<dispatchClerks.length;i++){
                sb.append(",if(x"+(i+1)+".amt_"+(i+1)+",x"+(i+1)+".amt_"+(i+1)+",0) amt_"+(i+1));
                sb.append(",if(x"+(i+1)+".lineNum_"+(i+1)+",x"+(i+1)+".lineNum_"+(i+1)+",0)lineNum_"+(i+1));
                sb.append(",if(x"+(i+1)+".num_"+(i+1)+",x"+(i+1)+".num_"+(i+1)+",0 ) num_"+(i+1));
            }
        }
        sb.append(" FROM (");
        sb.append("SELECT\n" +
               "\tdate_format(a.CREATE_DATE, '%Y%m%d') createDate,\n" +
               "\ta.WEEK_NO weekNo,\n" +
               "\tsum(a.AMOUNT) amt,\n" +
               "\tsum(a.LINE_NUM) lineNum,\n" +
               "\tcount(1) num\n" +
               "FROM\n" +
               "\tshipment_record a" +
               " WHERE 1 = 1 ");
                if(StringUtils.isNotBlank(shipmentRecordTotalVo.getDispatchClerk())&&!"-1".equals(shipmentRecordTotalVo.getDispatchClerk())){
                    sb.append("AND A.creator_User_Name IN("+shipmentRecordTotalVo.getDispatchClerk()+") ");
                }
                if(StringUtils.isNotBlank(shipmentRecordTotalVo.getStartDate())){
                    sb.append("AND DATE_FORMAT(A.`CREATE_DATE`, '%Y-%m-%d') >= '"+shipmentRecordTotalVo.getStartDate()+"' " );
                }
                if(StringUtils.isNotBlank(shipmentRecordTotalVo.getEndDate())){
                    sb.append("AND DATE_FORMAT(A.`CREATE_DATE`, '%Y-%m-%d') <= '"+shipmentRecordTotalVo.getEndDate()+"' ");
                }
               sb.append(" GROUP BY DATE_FORMAT(A.`CREATE_DATE`,'%Y-%m-%d') ,A.WEEK_NO ) C ");
        if(dispatchClerks!=null){
            for(int i=0;i<dispatchClerks.length;i++){
                sb.append(" LEFT JOIN ( SELECT\n" +
                        "\tdate_format(a.CREATE_DATE, '%Y%m%d') createDate_"+(i+1)+",\n" +
                        "\tsum(a.AMOUNT) amt_"+(i+1)+",\n" +
                        "\tsum(a.LINE_NUM) lineNum_"+(i+1)+",\n" +
                        "\tcount(1) num_"+(i+1)+"\n" +
                        "\tFROM\n" +
                        "\tshipment_record a where A.creator_User_Name in ("+dispatchClerks[i]+")");
                if(StringUtils.isNotBlank(shipmentRecordTotalVo.getStartDate())){
                    sb.append("AND DATE_FORMAT(A.`CREATE_DATE`, '%Y-%m-%d') >= '"+shipmentRecordTotalVo.getStartDate()+"' " );
                }
                if(StringUtils.isNotBlank(shipmentRecordTotalVo.getEndDate())){
                    sb.append("AND DATE_FORMAT(A.`CREATE_DATE`, '%Y-%m-%d') <= '"+shipmentRecordTotalVo.getEndDate()+"' ");
                }
                sb.append("GROUP BY date_format(a.CREATE_DATE, '%Y%m%d') ) X"+(i+1)+" ON c.createDate = x"+(i+1)+".createDate_"+(i+1));
            }
        }

        return commonDao.findPageListBySql(sb.toString(),resultDTO);
    }

    public List<ShipmentRecordTotalVo> queryReportList(final ShipmentRecordTotalVo shipmentRecordTotalVo) throws Exception{
        StringBuilder sb = new StringBuilder("SELECT\n" +
                "\tDATE_FORMAT(A.`CREATE_DATE`, '%Y-%m-%d') AS CREATE_DATE,\n" +
                "\tWEEK_NO,\n" +
                "\tA.creator_User_Name,\n" +
                "\tA.creator_Name,\n" +
                "\tSUM(A.`AMOUNT`) AMT,\n" +
                "\tSUM(A.`LINE_NUM`) LINE_NUM,\n" +
                "\tCOUNT(1) NUM FROM \n" +
                "\tshipment_record A \n" +
                "WHERE 1 = 1 ");
        if(StringUtils.isNotBlank(shipmentRecordTotalVo.getDispatchClerk())&&!"-1".equals(shipmentRecordTotalVo.getDispatchClerk())){
            sb.append("AND A.creator_User_Name IN(’"+shipmentRecordTotalVo.getDispatchClerk().replaceAll(",","','")+"') ");
        }
        if(StringUtils.isNotBlank(shipmentRecordTotalVo.getStartDate())){
            sb.append("AND DATE_FORMAT(A.`CREATE_DATE`, '%Y-%m-%d') >= '"+shipmentRecordTotalVo.getStartDate()+"' " );
        }
        if(StringUtils.isNotBlank(shipmentRecordTotalVo.getEndDate())){
            sb.append("AND DATE_FORMAT(A.`CREATE_DATE`, '%Y-%m-%d')<= '"+shipmentRecordTotalVo.getEndDate()+"' ");
        }
        sb.append(" GROUP BY DATE_FORMAT(A.`CREATE_DATE`,'%Y-%m-%d'),WEEK_NO,A.creator_User_Name,A.creator_Name ORDER BY DATE_FORMAT(A.`CREATE_DATE`, '%Y-%m-%d'),A.creator_User_Name ");
        List<ShipmentRecordPersonTotal> shipmentRecordPersonTotals = commonDao.queryListEntity(sb.toString(), ShipmentRecordPersonTotal.class);
        String createDate = "";
        List<ShipmentRecordTotalVo> shipmentRecordTotalVos = new ArrayList<>();
        List<ShipmentRecordPersonTotal> shipmentRecordPersonTotalList = null;
        ShipmentRecordTotalVo shipmentRecordTotalVoTemp = null;
       for(ShipmentRecordPersonTotal shipmentRecordPersonTotal:shipmentRecordPersonTotals){
           if(shipmentRecordPersonTotal.getCreateDate().equals(createDate)){//更新字段
               shipmentRecordTotalVoTemp.setAmt(shipmentRecordPersonTotal.getAmt()+shipmentRecordTotalVoTemp.getAmt());
               shipmentRecordTotalVoTemp.setLineNum(shipmentRecordPersonTotal.getLineNum()+shipmentRecordTotalVoTemp.getLineNum());
               shipmentRecordTotalVoTemp.setNum(shipmentRecordPersonTotal.getNum()+shipmentRecordTotalVoTemp.getNum());
               shipmentRecordPersonTotalList.add(shipmentRecordPersonTotal);
           }else{
               if(shipmentRecordTotalVoTemp!=null){ //日期变化，取上次的
                   shipmentRecordTotalVoTemp.setShipmentRecordPersonTotals(shipmentRecordPersonTotalList);
                   shipmentRecordTotalVos.add(shipmentRecordTotalVoTemp);
               }
               shipmentRecordPersonTotalList = new ArrayList<>();
               shipmentRecordTotalVoTemp = new ShipmentRecordTotalVo();
               shipmentRecordTotalVoTemp.setCreateDate(shipmentRecordPersonTotal.getCreateDate());
               shipmentRecordTotalVoTemp.setWeekNo(shipmentRecordPersonTotal.getWeekNo());
               shipmentRecordTotalVoTemp.setAmt(shipmentRecordPersonTotal.getAmt());
               shipmentRecordTotalVoTemp.setLineNum(shipmentRecordPersonTotal.getLineNum());
               shipmentRecordTotalVoTemp.setNum(shipmentRecordPersonTotal.getNum());
               shipmentRecordPersonTotalList.add(shipmentRecordPersonTotal);

           }
            createDate = shipmentRecordPersonTotal.getCreateDate();
       }

        return shipmentRecordTotalVos;
    }

}
