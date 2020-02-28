package com.kaizhuo.component.rbac.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.kaizhuo.component.rbac.dao.RoleDao;
import com.kaizhuo.component.rbac.error.RbacError;
import com.kaizhuo.component.rbac.model.domain.Menu;
import com.kaizhuo.component.rbac.model.domain.Role;
import com.kaizhuo.component.rbac.service.RoleService;
import com.kaizhuo.core.exception.AppException;
import com.kaizhuo.core.model.dto.PageModel;
import com.kaizhuo.core.service.impl.BaseServiceImpl;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.service.impl
 * @description:
 * @author: miaochen
 * @create: 2020-02-28 19:42
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDao, Role> implements RoleService {

    @Override
    public void doConvertDataMap(Role... roles) {
        for (Role role : roles) {
            role.addDataMap("createdDate", DateUtil.formatDateTime(role.getCreatedDate()));
            if (CollectionUtil.isNotEmpty(role.getMenus())) {
                role.addDataMap("menuIds", role.getMenus().stream().map(Menu::getId).collect(Collectors.toList()));
            }
        }
    }

    @Override
    public Role save(Role role) {
        Role saved = baseDao.findFirstByName(role.getName());
        if (saved != null) {
            if (role.getId() == null || !role.getId().equals(saved.getId())) {
                throw new AppException(RbacError.ROLE_EXISTED);
            }
        }
        role = super.save(role);
        return role;
    }

    @Override
    public PageModel<Role> findAll(Role param, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        return new PageModel<>(baseDao.findAll(Example.of(param, matcher), pageable));
    }

    @Override
    public Role detail(Long id) {
        return super.findById(id);
    }
}
