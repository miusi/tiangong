package com.kaizhuo.component.rbac.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kaizhuo.component.rbac.mybatis.pojo.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

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
public interface PermissionMapper extends BaseMapper<Permission> {
    Set<String> selectByUserId(@Param("userId") Integer userId);
}
