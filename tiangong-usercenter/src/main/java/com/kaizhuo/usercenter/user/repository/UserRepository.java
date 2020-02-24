package com.kaizhuo.usercenter.user.repository;

import com.kaizhuo.usercenter.dao.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @program: tiangong
 * @package: com.kaizhuo.usercenter.user.repository
 * @description:
 * @author: miaochen
 * @create: 2020-02-22 18:15
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Repository
public interface UserRepository extends JpaRepository<BaseUser,Long>, JpaSpecificationExecutor {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Query("select u from BaseUser u where u.username=:username ")
    Optional<BaseUser> findByUsername(@Param("username") String username);
}
