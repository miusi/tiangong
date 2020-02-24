package com.kaizhuo.usercenter.role.repository;

import com.kaizhuo.usercenter.dao.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: tiangong
 * @package: com.kaizhuo.usercenter.role.repository
 * @description:
 * @author: miaochen
 * @create: 2020-02-23 16:30
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public interface RoleRepository extends JpaRepository<Role,Long> {
}
