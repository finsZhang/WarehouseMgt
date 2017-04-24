package com.zx.whm.service.impl;

import com.zx.whm.common.domain.ResultDTO;
import com.zx.whm.dao.ShipmentRecordDao;
import com.zx.whm.domain.ShipmentRecord;
import com.zx.whm.service.ShipmentRecordSV;
import com.zx.whm.specification.ShipmentRecordSpec;
import com.zx.whm.vo.ShipmentRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by IntelliJ IDEA.
 * User: zhangfz3
 * Date: 2017/4/15
 * Time: 13:35
 * Tel:18665598790
 * Email:zhangfz3@asiainfo.com
 */
@Service
public class ShipmentRecordSVImpl implements ShipmentRecordSV {
    @Autowired
    private ShipmentRecordDao shipmentRecordDao;
    public ResultDTO queryPageList(final ShipmentRecordVo shipmentRecordVo, ResultDTO<ShipmentRecord> resultDTO) throws Exception {
        Sort s=new Sort(Sort.Direction.DESC, "createDate");
        Pageable pageable = new PageRequest(resultDTO.getPage()-1,resultDTO.getLimit(),s);
        Specification<ShipmentRecord> logRecordEntitySpecification = new ShipmentRecordSpec(shipmentRecordVo);
        Page<ShipmentRecord> page = shipmentRecordDao.findAll(where(logRecordEntitySpecification),pageable);
        resultDTO.setRows(page.getContent());
        resultDTO.setRecords((int)page.getTotalElements());
        return resultDTO;
    }


//    SELECT
//    A.`CREATE_DATE`,
//    A.`WEEK_NO`,
//    A.`DISPATCH_CLERK`,
//    SUM(A.`AMOUNT`) AMT,
//    COUNT(1) NUM
//    FROM
//    shipment_record A
//    GROUP BY A.`DISPATCH_CLERK`,A.`CREATE_DATE`
//    ;



}
