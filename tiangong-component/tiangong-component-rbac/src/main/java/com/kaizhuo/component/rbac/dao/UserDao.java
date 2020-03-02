package com.kaizhuo.component.rbac.dao;

import com.kaizhuo.component.rbac.model.domain.User;
import com.kaizhuo.data.jpa.JpaBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.dao
 * @description:
 * @author: miaochen
 * @create: 2020-02-27 23:29
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public interface UserDao extends JpaBaseRepository<User> {
    @Query("select u from User u where u.username=:username")
    User findFirstByUsername(String username);
}
