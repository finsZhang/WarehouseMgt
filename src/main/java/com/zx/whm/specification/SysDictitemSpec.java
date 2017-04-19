package com.zx.whm.specification;

import com.zx.whm.domain.ShipmentRecord;
import com.zx.whm.domain.SysDictitem;
import com.zx.whm.vo.ShipmentRecordVo;
import com.zx.whm.vo.SysDictitemVo;
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
public class SysDictitemSpec implements Specification<SysDictitem> {
    private SysDictitemVo sysDictitemVo;
    public SysDictitemSpec() {
    }

    public SysDictitemSpec(SysDictitemVo sysDictitemVo) {
        this.sysDictitemVo = sysDictitemVo;
    }

    public Predicate toPredicate(Root<SysDictitem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        if(StringUtils.isNotBlank(sysDictitemVo.getDictName())){
            predicates.add(cb.like(root.get("dictName").as(String.class), "%"+sysDictitemVo.getDictName().trim()+"%"));
        }
        if(StringUtils.isNotBlank(sysDictitemVo.getItemDesc())){
            predicates.add(cb.like(root.get("itemDesc").as(String.class), "%"+sysDictitemVo.getItemDesc().trim()+"%"));
        }
        Predicate[] p = new Predicate[predicates.size()];
        return cb.and(predicates.toArray(p));
    }
}
