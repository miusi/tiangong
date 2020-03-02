package com.kaizhuo.component.rbac.dao;

import com.kaizhuo.component.rbac.model.domain.Menu;
import com.kaizhuo.data.jpa.JpaBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.dao
 * @description:
 * @author: miaochen
 * @create: 2020-02-27 23:28
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public interface MenuDao extends JpaBaseRepository<Menu> {
    @Query("select m from Menu m where m.parent=:parent")
    List<Menu> findAllByParent(Menu parent);

    @Query("select m from Menu m where m.name=:name and m.parent=:parent")
    Menu findFirstByNameAndParent(String name, Menu parent);

    @Query("select m from Menu m where m.parent=:parent and m.url=:url and m.type=:type")
    Menu findFirstByParentAndUrlAndType(Menu parent, String url, Integer type);

    @Query("select  m from Menu m where m.parent=:parent and m.type=:type")
    List<Menu> findAllByParentAndType(Menu parent, Integer type);
}
