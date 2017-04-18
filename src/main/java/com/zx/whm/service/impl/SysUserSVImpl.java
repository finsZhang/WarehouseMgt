package com.zx.whm.service.impl;

import com.zx.whm.common.domain.ResultDTO;
import com.zx.whm.dao.SysUserDao;
import com.zx.whm.domain.SysUser;
import com.zx.whm.service.SysUserSV;
import com.zx.whm.specification.SysUserSpec;
import com.zx.whm.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by Walter on 2017/4/17.
 */
@Service
public class SysUserSVImpl implements SysUserSV{


    @Autowired
    private SysUserDao userDao;
    @Override
    public ResultDTO queryPageList(final SysUserVo userVo, ResultDTO<SysUser> resultDTO) throws Exception {
        Sort s=new Sort(Sort.Direction.DESC, "createDate");
        Pageable pageable = new PageRequest(resultDTO.getPage()-1,resultDTO.getLimit(),s);
        Specification<SysUser> sysUserSpec = new SysUserSpec(userVo);
        Page<SysUser> page = userDao.findAll(where(sysUserSpec),pageable);
        resultDTO.setRows(page.getContent());
        resultDTO.setRecords((int)page.getTotalElements());
        return resultDTO;
    }

   public void saveUser(SysUser bean)throws Exception{
        userDao.save(bean);
   }

    public void deleteUserById(long id)throws Exception{
        userDao.delete(id);
    }


}
