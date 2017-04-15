package com.zx.whm.service;

import com.zx.whm.domain.SysDictitem;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhangfz3
 * Date: 2017/1/16
 * Time: 15:28
 * Tel:18665598790
 * Email:zhangfz3@asiainfo.com
 */
public interface SysDictitemSV {
    SysDictitem[] findByDictName(String dictName);

    List<SysDictitem> findAll();
}
