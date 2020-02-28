package com.kaizhuo.component.rbac.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.kaizhuo.component.rbac.dao.UserDao;
import com.kaizhuo.component.rbac.error.RbacError;
import com.kaizhuo.component.rbac.model.domain.Role;
import com.kaizhuo.component.rbac.model.domain.User;
import com.kaizhuo.component.rbac.model.domain.enums.UserStatusEnum;
import com.kaizhuo.component.rbac.service.UserService;
import com.kaizhuo.core.exception.AppException;
import com.kaizhuo.core.model.dto.PageModel;
import com.kaizhuo.core.service.impl.BaseServiceImpl;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
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
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements UserService {

    @Override
    public void doConvertDataMap(User... users) {
        for (User user : users) {
            user.addDataMap("createDate", DateUtil.formatDateTime(user.getCreatedDate()));
            user.addDataMap("status", UserStatusEnum.getNameByKey(user.getStatus()));
            if (user.getDept() != null) {
                user.addDataMap("deptId", user.getDept().getId());
                user.addDataMap("deptName", user.getDept().getName());
            }
            if (CollectionUtil.isNotEmpty(user.getRoles())) {
                user.addDataMap("roleIds", user.getRoles().stream().map(Role::getId).collect(Collectors.toList()));
            }
        }
    }

    @Override
    public PageModel<User> findAll(User param, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("realname", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains());
        PageModel<User> page = new PageModel<>(baseDao.findAll(Example.of(param, matcher), pageable));
        if (!CollectionUtil.isNotEmpty(page.getList())) {
            for (User user : page.getList()) {
                user.setRoles(null);
            }
        }
        return page;
    }

    @Override
    public void updatePasswordByUsername(String username, String newPassword) {
        User user = baseDao.findFirstByUsername(username);
        if (user != null) {
            String encodePwd = getPwd(username, newPassword);
            user.setPassword(encodePwd);
            baseDao.save(user);
        }
    }

    @Override
    public void resetPasswordByUserId(Long id) {
        User user = baseDao.findById(id).get();
        if (user != null) {
            String encodePwd = getPwd(user.getUsername(), "123456");
            user.setPassword(encodePwd);
            baseDao.save(user);
        }
    }

    @Override
    public void changeStatus(Long userId, int status) {
        Optional<User> optionalUser = baseDao.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (status == UserStatusEnum.FORBIDDEN.getKey()) {
                user.setStatus(UserStatusEnum.FORBIDDEN.getKey());
            } else if (status == UserStatusEnum.AVAILABLE.getKey()) {
                user.setStatus(UserStatusEnum.AVAILABLE.getKey());
            } else if (status == UserStatusEnum.LOCKED.getKey()) {
                user.setStatus(UserStatusEnum.LOCKED.getKey());
                user.setLockedTime(new Date());
            }
        }
    }

    @Override
    public User detail(Long id) {
        return baseDao.findById(id).get();
    }

    @Override
    public User save(User user) {
        User saved = baseDao.findFirstByUsername(user.getUsername());
        if (saved != null) {
            if (user.getId() == null) {
                throw new AppException(RbacError.USER_EXISTED);
            } else {
                user.setPassword(saved.getPassword());
            }
        } else {
            String encodePwd = getPwd(user.getUsername(), "123456");
            user.setPassword(encodePwd);
        }
        user = super.save(user);
        return user;
    }

    private String getPwd(String username, String password) {
        String encodePwd = SecureUtil.sha256(SecureUtil.sha256(username) + SecureUtil.sha256(password));
        return encodePwd;
    }
}
