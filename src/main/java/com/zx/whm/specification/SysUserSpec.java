package com.zx.whm.specification;

import com.zx.whm.domain.ShipmentRecord;
import com.zx.whm.domain.SysUser;
import com.zx.whm.vo.ShipmentRecordVo;
import com.zx.whm.vo.SysUserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhangfz3
 * Date: 2017/4/15
 * Time: 13:28
 * Tel:18665598790
 * Email:zhangfz3@asiainfo.com
 */
public class SysUserSpec implements Specification<SysUser> {
    private SysUserVo userVo;
    public SysUserSpec() {
    }

    public SysUserSpec(SysUserVo userVo) {
        this.userVo = userVo;
    }

    public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        if(StringUtils.isNotBlank(userVo.getUserName())){
            predicates.add(cb.like(root.get("userName").as(String.class), "%"+userVo.getUserName().trim()+"%"));
        }
        if(StringUtils.isNotBlank(userVo.getName())){
            predicates.add(cb.like(root.get("name").as(String.class), "%"+userVo.getName().trim()+"%"));
        }
        Predicate[] p = new Predicate[predicates.size()];
        return cb.and(predicates.toArray(p));
    }
}
