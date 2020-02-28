package com.kaizhuo.component.rbac.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.kaizhuo.component.rbac.dao.DeptDao;
import com.kaizhuo.component.rbac.error.RbacError;
import com.kaizhuo.component.rbac.model.domain.Dept;
import com.kaizhuo.component.rbac.service.DeptService;
import com.kaizhuo.core.exception.AppException;
import com.kaizhuo.core.model.dto.PageModel;
import com.kaizhuo.core.service.impl.BaseServiceImpl;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.service.impl
 * @description:
 * @author: miaochen
 * @create: 2020-02-28 19:41
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Service
public class DeptServiceImpl extends BaseServiceImpl<DeptDao,Dept> implements DeptService {

    @Override
    public void doConvertDataMap(Dept... depts) {
        for (Dept dept : depts) {
            dept.addDataMap("createdDate", DateUtil.formatDateTime(dept.getCreatedDate()));
        }
    }

    @Override
    public PageModel<Dept> findAll(Dept param, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        PageModel<Dept> page = new PageModel<>(baseDao.findAll(Example.of(param, matcher), pageable));
        if (CollectionUtil.isNotEmpty(page.getList())) {
            for (Dept dept : page.getList()) {
                // 避免序列化的时候懒加载查询
                dept.setChildren(null);
            }
        }
        return page;
    }

    @Override
    public List<Dept> tree() {
        List<Dept> list = baseDao.findAllByParent(new Dept(0L));
        return list;
    }

    @Override
    public Dept save(Dept dept) {
        Dept saved = baseDao.findFirstByNameAndParent(dept.getName(), dept.getParent());
        if (saved != null) {
            if (dept.getId() == null || !dept.getId().equals(saved.getId())) {
                throw new AppException(RbacError.DEPT_EXISTED);
            }
        }
        return super.save(dept);
    }
}
