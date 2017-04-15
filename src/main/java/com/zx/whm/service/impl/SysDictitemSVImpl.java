package com.zx.whm.service.impl;

import com.zx.whm.dao.SysDictitemDao;
import com.zx.whm.domain.SysDictitem;
import com.zx.whm.service.SysDictitemSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhangfz3
 * Date: 2017/1/16
 * Time: 15:33
 * Tel:18665598790
 * Email:zhangfz3@asiainfo.com
 */
@Service
public class SysDictitemSVImpl  implements SysDictitemSV {
    @Autowired
    private SysDictitemDao sysDictitemDao;
    public SysDictitem[] findByDictName(String dictName) {
        return sysDictitemDao.findByDictName(dictName);
    }
    public List<SysDictitem> findAll() {
        return sysDictitemDao.findAll();
    }
}
