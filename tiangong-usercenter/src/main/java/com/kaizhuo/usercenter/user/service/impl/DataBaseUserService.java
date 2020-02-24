package com.kaizhuo.usercenter.user.service.impl;

import com.kaizhuo.usercenter.dao.BaseUser;
import com.kaizhuo.usercenter.dao.Role;
import com.kaizhuo.usercenter.user.repository.UserRepository;
import com.kaizhuo.usercenter.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @program: tiangong
 * @package: com.kaizhuo.usercenter.user.service.impl
 * @description:
 * @author: miaochen
 * @create: 2020-02-22 18:20
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Service
public class DataBaseUserService implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<BaseUser> getByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public String login(String username, String password) {
        return null;
    }

    @Override
    public BaseUser getById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public BaseUser add(BaseUser baseUser) {
        baseUser.setPassword(passwordEncoder.encode(baseUser.getPassword()));
        return userRepository.save(baseUser);
    }

    @Override
    public BaseUser updateByKey(Long id, BaseUser baseUser) {
        baseUser.setId(id);
        return userRepository.save(baseUser);
    }

    @Override
    public List<Role> getRoleList(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public int updateRole(Long userId, List<Role> roleList) {
        return 0;
    }


}
