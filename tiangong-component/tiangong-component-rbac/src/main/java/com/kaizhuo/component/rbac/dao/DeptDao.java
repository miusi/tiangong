package com.kaizhuo.component.rbac.dao;

import com.kaizhuo.component.rbac.model.domain.Dept;
import com.kaizhuo.data.jpa.JpaBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
@Repository
public interface DeptDao extends JpaBaseRepository<Dept> {

    @Query("select d from Dept d where d.parent=:parent")
    List<Dept> findAllByParent(Dept parent);

    @Query("select d from Dept d where d.parent=:parent and d.name=:name")
    Dept findFirstByNameAndParent(String name, Dept parent);
}
