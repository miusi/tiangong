package com.kaizhuo.component.rbac.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kaizhuo.component.rbac.mybatis.pojo.Role;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.mybatis.service
 * @description:
 * @author: godric
 * @create: 2020/3/7 0007
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
public interface RoleService extends IService<Role> {

    /**
     * 查询角色，同时查询包含的权限
     * @param id
     * @return
     */
    Role selectById(Integer id);

    /**
     * 插入角色，同时插入包含的权限
     * @param role
     */
    void insertRole(Role role);

    /**
     * 更新角色，同时更新包含的权限
     */
    void updateRole(Role role);

    /**
     * 删除权限角色
     * @param roleId
     */
    void deleteRole(Integer roleId);
}
