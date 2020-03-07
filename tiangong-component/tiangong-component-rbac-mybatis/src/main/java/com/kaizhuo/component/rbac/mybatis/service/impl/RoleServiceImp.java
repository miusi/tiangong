package com.kaizhuo.component.rbac.mybatis.service.impl;

import com.kaizhuo.component.rbac.mybatis.mapper.RoleMapper;
import com.kaizhuo.component.rbac.mybatis.pojo.Permission;
import com.kaizhuo.component.rbac.mybatis.pojo.Role;
import com.kaizhuo.component.rbac.mybatis.service.RoleService;
import com.kaizhuo.core.model.domain.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.mybatis.service.impl
 * @description:
 * @author: godric
 * @create: 2020/3/7 0007
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
@Service
public class RoleServiceImp extends BaseService<Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role selectById(Integer id) {
        return roleMapper.selectById(id);
    }

    @Override
    public void insertRole(Role role) {
        checkUnique(Role::getName,role.getName(),null,"角色名已存在");
        super.save(role);
        //插入权限
        List<Permission> permissions = role.getPermissions();
        if (isNotEmpty(permissions)) {
            roleMapper.insertRolePermission(role.getId(), permissions);
        }
    }

    @Override
    public void updateRole(Role role) {
        super.updateById(role);
        //先删除旧的权限
        roleMapper.deleteRolePermission(role.getId());
        //插入新的权限
        if (isNotEmpty(role.getPermissions())) {
            roleMapper.insertRolePermission(role.getId(), role.getPermissions());
        }
    }

    @Override
    public void deleteRole(Integer roleId) {
        super.removeById(roleId);
        //删除权限
        roleMapper.deleteRolePermission(roleId);
    }
}
