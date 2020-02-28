package com.kaizhuo.component.rbac.dao;

import com.kaizhuo.component.rbac.model.domain.Menu;
import com.kaizhuo.data.jpa.JpaBaseRepository;

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
    List<Menu> findAllByParent(Menu parent);

    Menu findFirstByNameAndParent(String name, Menu parent);

    Menu findFirstByParentAndUrlAndType(Menu parent, String url, Integer type);

    List<Menu> findAllByParentAndType(Menu parent, Integer type);
}
