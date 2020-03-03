package com.kaizhuo.component.rbac.dao;

import com.kaizhuo.component.rbac.model.domain.Role;
import com.kaizhuo.data.jpa.JpaBaseRepository;
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
@Repository
public interface RoleDao extends JpaBaseRepository<Role> {
    Role findFirstByName(String name);
}
