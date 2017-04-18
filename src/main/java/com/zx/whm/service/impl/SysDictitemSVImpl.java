package com.zx.whm.service.impl;

import com.zx.whm.common.domain.ResultDTO;
import com.zx.whm.dao.SysDictitemDao;
import com.zx.whm.domain.ShipmentRecord;
import com.zx.whm.domain.SysDictitem;
import com.zx.whm.domain.SysDictitemPK;
import com.zx.whm.domain.SysUser;
import com.zx.whm.service.SysDictitemSV;
import com.zx.whm.specification.ShipmentRecordSpec;
import com.zx.whm.specification.SysDictitemSpec;
import com.zx.whm.vo.ShipmentRecordVo;
import com.zx.whm.vo.SysDictitemVo;
import com.zx.whm.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.Specifications.where;

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

    public ResultDTO queryPageList(final SysDictitemVo sysDictitemVo, ResultDTO<SysDictitem> resultDTO) throws Exception {
        Sort s=new Sort(Sort.Direction.DESC, "createDate");
        Pageable pageable = new PageRequest(resultDTO.getPage()-1,resultDTO.getLimit(),s);
        Specification<SysDictitem> sysDictitemSpec = new SysDictitemSpec(sysDictitemVo);
        Page<SysDictitem> page = sysDictitemDao.findAll(where(sysDictitemSpec),pageable);
        resultDTO.setRows(page.getContent());
        resultDTO.setRecords((int)page.getTotalElements());
        return resultDTO;
    }


    public void saveDict(SysDictitem bean)throws Exception{
        sysDictitemDao.save(bean);
    }

    public void delete(SysDictitemPK pk)throws Exception{
        sysDictitemDao.delete(pk);
    }

    public SysDictitem findByDictByPk(SysDictitemPK pk)throws Exception{
       return sysDictitemDao.findOne(pk);
    }
}
