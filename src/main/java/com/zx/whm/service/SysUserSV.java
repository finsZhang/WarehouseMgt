package com.zx.whm.service;

import com.zx.whm.common.domain.ResultDTO;
import com.zx.whm.domain.SysUser;
import com.zx.whm.vo.SysUserVo;

/**
 * Created by Walter on 2017/4/17.
 */
public interface SysUserSV {
    public ResultDTO queryPageList(final SysUserVo shipmentRecordVo, ResultDTO<SysUser> resultDTO) throws Exception;
    SysUser findSysUserByUserName(String userName);
    void save(SysUser sysUser);
}
