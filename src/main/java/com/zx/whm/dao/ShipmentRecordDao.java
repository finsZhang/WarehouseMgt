package com.zx.whm.dao;

import com.zx.whm.domain.ShipmentRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by IntelliJ IDEA.
 * User: zhangfz3
 * Date: 2017/4/15
 * Time: 13:26
 * Tel:18665598790
 * Email:zhangfz3@asiainfo.com
 */

public interface ShipmentRecordDao extends JpaRepository<ShipmentRecord,Long>,JpaSpecificationExecutor<ShipmentRecord> {
    Page<ShipmentRecord> findAll(Specification<ShipmentRecord> spec, Pageable pageable);
    @Query(value = "select * from SHIPMENT_RECORD  t where t.creator_user_name =?1 and date_format(t.create_date,'%Y%m%d')=date_format(sysdate(),'%Y%m%d') order by t.create_date desc",nativeQuery = true)
    ShipmentRecord findOneByCreatorUserNameOrderByCreateDateDesc(String creatorUserName);
}
