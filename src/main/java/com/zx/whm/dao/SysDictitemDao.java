package com.zx.whm.dao;


import com.zx.whm.domain.SysDictitem;
import com.zx.whm.domain.SysDictitemPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * User: zhangfz3
 * Date: 2017/1/16
 * Time: 15:27
 * Tel:18665598790
 * Email:zhangfz3@asiainfo.com
 */
public interface SysDictitemDao extends JpaRepository<SysDictitem, SysDictitemPK> {
    SysDictitem[] findByDictName(String dictName);
}
