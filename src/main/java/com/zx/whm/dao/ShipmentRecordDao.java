package com.zx.whm.dao;

import com.zx.whm.domain.ShipmentRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

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
}
