package com.zx.whm.service.impl;

import com.zx.whm.common.domain.ResultDTO;
import com.zx.whm.dao.CommonDao;
import com.zx.whm.service.ReportSV;
import com.zx.whm.vo.ShipmentRecordTotalVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Walter on 2017/4/20.
 */
@Service
public class ReportSVImpl implements ReportSV {

    @Autowired
    private CommonDao commonDao;

    public ResultDTO queryPageList(final ShipmentRecordTotalVo shipmentRecordTotalVo, ResultDTO<ShipmentRecordTotalVo> resultDTO) throws Exception {
       StringBuilder sb = new StringBuilder("SELECT \n" +
               "  A.`CREATE_DATE`,\n" +
               "  A.`WEEK_NO`,\n" +
               "  A.`DISPATCH_CLERK`,\n" +
               "  SUM(A.`AMOUNT`) AMT,\n" +
               "  COUNT(1) NUM \n" +
               "FROM\n" +
               "  shipment_record A \n" +
               "WHERE 1 = 1 ");
                if(StringUtils.isNotBlank(shipmentRecordTotalVo.getDispatchClerk())&&!"-1".equals(shipmentRecordTotalVo.getDispatchClerk())){
                    sb.append("AND A.`DISPATCH_CLERK` IN("+shipmentRecordTotalVo.getDispatchClerk()+") ");
                }
                if(StringUtils.isNotBlank(shipmentRecordTotalVo.getStartDate())){
                    sb.append("AND A.`CREATE_DATE` >= STR_TO_DATE('"+shipmentRecordTotalVo.getStartDate()+"','%Y-%m-%d')");
                }
                if(StringUtils.isNotBlank(shipmentRecordTotalVo.getStartDate())){
                    sb.append("AND A.`CREATE_DATE` <= STR_TO_DATE('"+shipmentRecordTotalVo.getEndDate()+"','%Y-%m-%d')");
                }
               sb.append(" GROUP BY A.`DISPATCH_CLERK`,A.`CREATE_DATE`");
        return commonDao.findPageListBySql(sb.toString(), resultDTO, ShipmentRecordTotalVo.class,true);
    }

}
