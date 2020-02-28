package com.kaizhuo.component.rbac.service;

import com.kaizhuo.component.rbac.model.domain.User;
import com.kaizhuo.core.model.dto.PageModel;
import com.kaizhuo.core.service.BaseService;
import org.springframework.data.domain.Pageable;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.service
 * @description:
 * @author: miaochen
 * @create: 2020-02-27 23:33
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public interface UserService extends BaseService<User> {

    /**
     * 根据条件分页查询
     * @param param     查询参数
     * @param pageable  分页参数
     * @return
     */
    PageModel<User> findAll(User param, Pageable pageable);

    /**
     * 根据用户id 修改密码
     * @param username      用户名
     * @param newPassword   新密码
     */
    void updatePasswordByUsername(String username,String newPassword);

    /**
     * 根据用户id 重置用户密码
     * @param id
     */
    void resetPasswordByUserId(Long id);

    /**
     * 根据用户id 更改用户状态
     * @param userId    用户id
     * @param status    状态
     */
    void changeStatus(Long userId,int status);

    /**
     * 详情
     * @param id
     * @return
     */
    User detail(Long id);
}
