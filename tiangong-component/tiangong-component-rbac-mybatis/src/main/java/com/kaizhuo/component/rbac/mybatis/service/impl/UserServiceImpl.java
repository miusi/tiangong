package com.kaizhuo.component.rbac.mybatis.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaizhuo.component.rbac.mybatis.mapper.UserMapper;
import com.kaizhuo.component.rbac.mybatis.pojo.User;
import com.kaizhuo.component.rbac.mybatis.qo.UserQueryRequest;
import com.kaizhuo.component.rbac.mybatis.service.UserService;
import com.kaizhuo.component.rbac.mybatis.vo.UserVo;
import com.kaizhuo.core.model.domain.BaseService;
import com.kaizhuo.core.model.domain.enums.YnEnums;
import com.kaizhuo.core.security.TiangongSecurityService;
import com.kaizhuo.core.security.TiangongSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<UserVo> findWithPage(UserQueryRequest qo) {
        return userMapper.findWithPage(qo.getPage(), qo);
    }

    @Override
    public UserVo findById(Integer id) {
        return userMapper.findById(id);
    }


    @Override
    public void insert(User user) {
        String phone = user.getPhone();
        checkUnique(User::getUsername, user.getUsername(), null, "用户已存在");
        checkUnique(User::getPhone, phone, null, "手机号已经存在，请更换");
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setStatus(YnEnums.Y.getKey());
        super.save(user);
        //插入角色
        if (isNotEmpty(user.getRoles())) {
            userMapper.insertUserRole(user.getId(), user.getRoles());
        }
    }

    @Override
    public void updateByPrimaryKey(User user) {
        checkUnique(User::getPhone, user.getPhone(), user.getId(), "手机号已经存在,请更换");
        user.setUsername(null);
        user.setPassword(null);
        user.setPassword(null);
        user.setStatus(null);
        user.setCreateBy(null);
        user.setCreateDate(null);
        user.setUpdateDate(LocalDateTime.now());
        super.updateById(user);
        //插入角色
        if (isNotEmpty(user.getRoles())) {
            userMapper.deleteUserRole(user.getId());
            userMapper.insertUserRole(user.getId(), user.getRoles());
        }
    }

    @Override
    public void toggleStatus(Integer id) {
        User userOld = super.getById(id);
        Integer status = YnEnums.Y.getKey().equals(userOld.getStatus()) ? YnEnums.N.getKey() : YnEnums.Y.getKey();
        userOld.setStatus(status);
        super.updateById(userOld);
    }

    @Override
    public void updatePassword(Integer id, String password) {
        User user = new User();
        user.setId(id);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        super.updateById(user);
    }


    @Override
    public User selectByUserName(String userName) {
        return super.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername,userName));
    }

    @Override
    public User selectByPhone(String phone) {
        return super.getOne(new LambdaQueryWrapper<User>().eq(User::getPhone,phone));
    }
}
