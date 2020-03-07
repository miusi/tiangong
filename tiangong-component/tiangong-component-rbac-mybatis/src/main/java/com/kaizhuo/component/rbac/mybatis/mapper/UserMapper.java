package com.kaizhuo.component.rbac.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaizhuo.component.rbac.mybatis.pojo.Role;
import com.kaizhuo.component.rbac.mybatis.pojo.User;
import com.kaizhuo.component.rbac.mybatis.qo.UserQO;
import com.kaizhuo.component.rbac.mybatis.vo.UserVo;
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
public interface UserMapper extends BaseMapper<User> {
    Page<UserVo> findWithPage(Page<UserVo> page, @Param("qo")UserQO qo);

    UserVo findById(@Param("id") Integer id);

    void insertUserRole(@Param("userId") Integer userId, @Param("roles")List<Role> roles);

    void deleteUserRole(@Param("userId") Integer userId);
}
