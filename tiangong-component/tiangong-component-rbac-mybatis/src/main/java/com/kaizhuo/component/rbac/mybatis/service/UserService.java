package com.kaizhuo.component.rbac.mybatis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kaizhuo.component.rbac.mybatis.pojo.User;
import com.kaizhuo.component.rbac.mybatis.qo.UserQueryRequest;
import com.kaizhuo.component.rbac.mybatis.vo.UserVo;

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
public interface UserService extends IService<User> {
    /**
     * 分页查找用户列表
     * @param qo
     * @return
     */
    Page<UserVo> findWithPage(UserQueryRequest qo);

    /**
     * 主键搜索
     * @param id
     * @return
     */
    UserVo findById(Integer id);

    /**
     * 新增用户，同时插入角色
     * @param user
     */
    void insert(User user);

    /**
     * 根据主键更新
     * @param user
     */
    void updateByPrimaryKey(User user);

    /**
     * 改变用户状态
     * @param id
     */
    void toggleStatus(Integer id);

    /**
     * 更新密码
     * @param id
     * @param password
     */
    void updatePassword(Integer id,String password);

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     */
    //void changePassword(String oldPassword, String newPassword)

    /**
     * 根据用户名获取用户
     * @param userName
     * @return
     */
    User selectByUserName(String userName);

    /**
     * 根据phone获取用户
     * @param phone
     * @return
     */
    User selectByPhone(String phone);
}
