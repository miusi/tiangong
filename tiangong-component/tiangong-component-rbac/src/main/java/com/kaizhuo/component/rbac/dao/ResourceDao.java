package com.kaizhuo.component.rbac.dao;

import com.kaizhuo.component.rbac.model.domain.Resource;
import com.kaizhuo.data.jpa.JpaBaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

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
public interface ResourceDao extends JpaBaseRepository<Resource> {
    Resource findFirstByPath(String path);

    @Query(value = "select r.tag from Resource r where r.yn = 1 group by tag")
    List<String> findTagsGroupByTag();

    List<Resource> findAllByTag(String tag);
}
