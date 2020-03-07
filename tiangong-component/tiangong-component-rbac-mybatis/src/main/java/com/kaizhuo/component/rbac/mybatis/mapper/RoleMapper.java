package com.kaizhuo.component.rbac.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kaizhuo.component.rbac.mybatis.pojo.Permission;
import com.kaizhuo.component.rbac.mybatis.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.mybatis.mapper
 * @description:
 * @author: godric
 * @create: 2020/3/7 0007
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    Role getById(@Param("id") Integer id);

    void insertRolePermission(@Param("roleId") Integer roleId, @Param("permissions") List<Permission> permissions);

    void deleteRolePermission(@Param("roleId") Integer roleId);
}
