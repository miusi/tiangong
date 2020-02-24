package com.kaizhuo.usercenter.user.service;

import com.kaizhuo.usercenter.dao.BaseUser;
import com.kaizhuo.usercenter.dao.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @program: tiangong
 * @package: com.kaizhuo.usercenter.user.service
 * @description:
 * @author: miaochen
 * @create: 2020-02-22 18:20
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public interface UserService {
    /**
     * 根据用户名获取后台管理员
     * @param username
     * @return
     */
    Optional<BaseUser> getByUserName(String username);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    String login(String username,String password);

    /**
     * 根据id获取后台管理员
     * @param id
     * @return
     */
    BaseUser getById(Long id);

    /**
     * 新增用户
     * @param baseUser
     * @return
     */
    BaseUser add(BaseUser baseUser);

    /**
     * 修改指定用户信息
     * @param id
     * @param baseUser
     * @return
     */
    BaseUser updateByKey(Long id, BaseUser baseUser);

    /**
     * 获取角色列表
     * @param id
     * @return
     */
    List<Role> getRoleList(Long id);

    /**
     * 删除指定用户
     * @param id
     * @return
     */
    void delete(Long id);

    /**
     * 更新指定用户角色
     * @param userId
     * @param roleList
     * @return
     */
    @Transactional
    int updateRole(Long userId,List<Role> roleList);
}
