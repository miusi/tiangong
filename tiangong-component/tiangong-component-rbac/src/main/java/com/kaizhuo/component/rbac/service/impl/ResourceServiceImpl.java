package com.kaizhuo.component.rbac.service.impl;

import com.google.common.collect.Lists;
import com.kaizhuo.component.rbac.dao.ResourceDao;
import com.kaizhuo.component.rbac.model.domain.Menu;
import com.kaizhuo.component.rbac.model.domain.Resource;
import com.kaizhuo.component.rbac.model.domain.Role;
import com.kaizhuo.component.rbac.service.ResourceService;
import com.kaizhuo.core.model.dto.PageModel;
import com.kaizhuo.core.service.impl.BaseServiceImpl;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.service.impl
 * @description:
 * @author: miaochen
 * @create: 2020-02-28 19:43
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Service
public class ResourceServiceImpl extends BaseServiceImpl<ResourceDao, Resource> implements ResourceService {
    @Override
    public PageModel<Resource> findAll(Resource param, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("tag", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("path", ExampleMatcher.GenericPropertyMatchers.contains());
        return new PageModel<>(baseDao.findAll(Example.of(param, matcher), pageable));
    }

    @Override
    public List<Resource> list(Resource param) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("tag", ExampleMatcher.GenericPropertyMatchers.exact());
        return baseDao.findAll(Example.of(param, matcher));
    }

    @Override
    public Resource getResourceByPath(String path) {
        return baseDao.findFirstByPath(path);
    }

    @Override
    public List<Role> getRolesById(Long id) {
        List<Role> roles = Lists.newArrayList();
        Resource resource = super.findById(id);
        if (resource != null) {
            List<Menu> menus = resource.getMenus();
            for (Menu menu : menus) {
                roles.addAll(menu.getRoles());
            }
        }
        return roles;
    }

    @Override
    public List<Resource> getResourceTags(Long menuId) {
        List<String> tags = baseDao.findTagsGroupByTag();
        List<Resource> tagsResources = new ArrayList<>(tags.size());
        for (String tag : tags) {
            Resource resource = new Resource();
            resource.setTag(tag);
            tagsResources.add(resource);
            List<Resource> resources = baseDao.findAllByTag(tag);
            for (Resource item : resources) {
                boolean checked = false;
                List<Long> menuIds = item.getMenus().stream().map(Menu::getId).collect(Collectors.toList());
                if (menuIds.contains(menuId)) {
                    checked = true;
                }
                item.addDataMap("checked", checked);
            }
            resource.addDataMap("resources", resources);
        }
        return tagsResources;
    }
}
