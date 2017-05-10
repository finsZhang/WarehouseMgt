package com.zx.whm.specification;

import com.zx.whm.common.util.DateUtils;
import com.zx.whm.domain.ShipmentRecord;
import com.zx.whm.vo.ShipmentRecordVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhangfz3
 * Date: 2017/4/15
 * Time: 13:28
 * Tel:18665598790
 * Email:zhangfz3@asiainfo.com
 */
public class ShipmentRecordSpec implements Specification<ShipmentRecord> {
    private ShipmentRecordVo shipmentRecordVo;
    public ShipmentRecordSpec() {
    }

    public ShipmentRecordSpec(ShipmentRecordVo shipmentRecordVo) {
        this.shipmentRecordVo = shipmentRecordVo;
    }

    public Predicate toPredicate(Root<ShipmentRecord> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        if(!"-1".equals(shipmentRecordVo.getType())&&shipmentRecordVo.getType()!=null){
            predicates.add(cb.equal(root.get("type"),shipmentRecordVo.getType()));//.like(root.get("type").as(String.class), "%"+shipmentRecordVo.getCreatorName().trim()+"%"));
        }
        if(!"-1".equals(shipmentRecordVo.getPayType())&&shipmentRecordVo.getPayType()!=null){
            predicates.add(cb.equal(root.get("payType"),shipmentRecordVo.getPayType()));
        }
        if(!"-1".equals(shipmentRecordVo.getDispatchClerk())&&shipmentRecordVo.getDispatchClerk()!=null){
            predicates.add(cb.equal(root.get("dispatchClerk"),shipmentRecordVo.getDispatchClerk()));
        }
        if(StringUtils.isNotBlank(shipmentRecordVo.getCreatorUserName())){
            predicates.add(cb.equal(root.get("creatorUserName"),shipmentRecordVo.getCreatorUserName()));
        }
        if(StringUtils.isNotBlank(shipmentRecordVo.getStartOperateDate())){
            predicates.add(cb.greaterThanOrEqualTo(root.get("createDate").as(Timestamp.class), DateUtils.parseTimestamp(shipmentRecordVo.getStartOperateDate(),"yyyy-MM-dd HH:mm:ss")));
        }
        if(StringUtils.isNotBlank(shipmentRecordVo.getEndOperateDate())){
            predicates.add(cb.lessThanOrEqualTo(root.get("createDate").as(Timestamp.class), DateUtils.parseTimestamp(shipmentRecordVo.getEndOperateDate(),"yyyy-MM-dd HH:mm:ss")));
        }
        if(StringUtils.isNotBlank(shipmentRecordVo.getCreatorName())){
            predicates.add(cb.like(root.get("creatorName").as(String.class),"%"+shipmentRecordVo.getCreatorName()+"%"));
        }
        if(StringUtils.isNotBlank(shipmentRecordVo.getProdDetail())){
            predicates.add(cb.like(root.get("prodDetail").as(String.class),"%"+shipmentRecordVo.getProdDetail()+"%"));
        }
        Predicate[] p = new Predicate[predicates.size()];
        return cb.and(predicates.toArray(p));
    }
}
