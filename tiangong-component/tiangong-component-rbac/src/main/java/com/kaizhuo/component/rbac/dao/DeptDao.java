package com.kaizhuo.component.rbac.dao;

import com.kaizhuo.component.rbac.model.domain.Dept;
import com.kaizhuo.data.jpa.JpaBaseRepository;

import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.error
 * @description:
 * @author: miaochen
 * @create: 2020-02-27 23:27
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public interface DeptDao extends JpaBaseRepository<Dept> {

    List<Dept> findAllByParent(Dept parent);

    Dept findFirstByNameAndParent(String name,Dept parent);
}
