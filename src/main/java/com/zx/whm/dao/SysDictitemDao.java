package com.zx.whm.dao;


import com.zx.whm.domain.SysDictitem;
import com.zx.whm.domain.SysDictitemPK;
import com.zx.whm.domain.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by IntelliJ IDEA.
 * User: zhangfz3
 * Date: 2017/1/16
 * Time: 15:27
 * Tel:18665598790
 * Email:zhangfz3@asiainfo.com
 */
public interface SysDictitemDao extends JpaRepository<SysDictitem, SysDictitemPK>,JpaSpecificationExecutor<SysDictitem> {
    SysDictitem[] findByDictName(String dictName);
    Page<SysDictitem> findAll(Specification<SysDictitem> spec, Pageable pageable);
}
