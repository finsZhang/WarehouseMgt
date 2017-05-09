package com.zx.whm.service.impl;

import com.zx.whm.common.domain.ResultDTO;
import com.zx.whm.common.util.DateUtils;
import com.zx.whm.dao.CommonDao;
import com.zx.whm.dao.ShipmentRecordDao;
import com.zx.whm.domain.ShipmentRecord;
import com.zx.whm.domain.SysUser;
import com.zx.whm.service.ShipmentRecordSV;
import com.zx.whm.specification.ShipmentRecordSpec;
import com.zx.whm.vo.ShipmentRecordStats;
import com.zx.whm.vo.ShipmentRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

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
    @Autowired
    private CommonDao commonDao;
    public ResultDTO queryPageList(final ShipmentRecordVo shipmentRecordVo, ResultDTO<ShipmentRecord> resultDTO) throws Exception {
        Sort s=new Sort(Sort.Direction.DESC, "createDate");
        Pageable pageable = new PageRequest(resultDTO.getPage()-1,resultDTO.getLimit(),s);
        Specification<ShipmentRecord> logRecordEntitySpecification = new ShipmentRecordSpec(shipmentRecordVo);
        Page<ShipmentRecord> page = shipmentRecordDao.findAll(where(logRecordEntitySpecification),pageable);
        resultDTO.setRows(page.getContent());
        resultDTO.setRecords((int)page.getTotalElements());
        return resultDTO;
    }

    public void delete(long id){
        shipmentRecordDao.delete(id);
    }

    public ShipmentRecord getRecord(long id){
        return shipmentRecordDao.findOne(id);
    }

    public void save(SysUser user, ShipmentRecord shipmentRecord){
        if(shipmentRecord.getId()!=0){
            shipmentRecord.setModifyDate(new Timestamp(DateUtils.getTime().getTime()));

            ShipmentRecord shipmentRecordTmp =  getRecord(shipmentRecord.getId());
            shipmentRecord.setCreatorName(shipmentRecordTmp.getCreatorName());
            shipmentRecord.setCreatorUserName(shipmentRecordTmp.getCreatorUserName());
            shipmentRecord.setCreateDate(shipmentRecordTmp.getCreateDate());
            shipmentRecord.setWeekNo(shipmentRecordTmp.getWeekNo());
        }else{//新增
            shipmentRecord.setCreatorName(user.getName());
            shipmentRecord.setCreatorUserName(user.getUserName());
            shipmentRecord.setCreateDate(new Timestamp(DateUtils.getTime().getTime()));
            shipmentRecord.setWeekNo(DateUtils.getCurWeekOfDate());
        }
        shipmentRecordDao.save(shipmentRecord);
    }

    public ShipmentRecord getRecordByCreatorUserName(String  creatorUserName){
        return shipmentRecordDao.findOneByCreatorUserNameOrderByCreateDateDesc(creatorUserName);
    }
    public List<ShipmentRecordStats> getRecordsByCreatorUserName(String  creatorName){
        String sql = "SELECT  date_format(a.CREATE_DATE,\n" +
                "                '%Y%m%d') create_date,\n" +
                "a.week_no,\n" +
                "a.DAY_NO,\n" +
                "(select b.ITEM_NAME from sys_dictitem b WHERE b.DICT_NAME='SHIPMENT_RECORD_TYPE' and b.ITEM_NO=a.type) type,\n" +
                "a.PROD_DETAIL,\n" +
                "a.AMOUNT,\n" +
                "a.LINE_NUM,\n" +
                "(select b.ITEM_NAME from sys_dictitem b WHERE b.DICT_NAME='SHIPMENT_RECORD_PAY_TYPE' and b.ITEM_NO=a.pay_type) pay_type,\n" +
                "a.CUST_INFO,\n" +
                "a.`COMMENT`,\n" +
                "(select b.ITEM_NAME from sys_dictitem b WHERE b.DICT_NAME='SHIPMENT_RECORD_CLERK' and b.ITEM_NO=a.DISPATCH_CLERK) DISPATCH_CLERK,\n" +
                "a.MODIFY_CONENT\n" +
                "\n" +
                "from shipment_record a where creator_User_Name='"+creatorName+"'";
        return commonDao.queryListEntity(sql, ShipmentRecordStats.class);
    }
}
