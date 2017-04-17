package com.zx.whm.dao;

import com.zx.whm.domain.ShipmentRecord;
import com.zx.whm.domain.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Walter on 2017/4/17.
 */
public interface SysUserDao extends JpaRepository<SysUser,Long>,JpaSpecificationExecutor<SysUser> {
    Page<SysUser> findAll(Specification<SysUser> spec, Pageable pageable);
}