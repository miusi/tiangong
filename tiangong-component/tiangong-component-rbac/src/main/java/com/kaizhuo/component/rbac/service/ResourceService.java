package com.kaizhuo.component.rbac.service;

import com.kaizhuo.component.rbac.model.domain.Resource;
import com.kaizhuo.component.rbac.model.domain.Role;
import com.kaizhuo.core.model.dto.PageModel;
import com.kaizhuo.core.service.BaseService;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.service
 * @description:
 * @author: miaochen
 * @create: 2020-02-27 23:34
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public interface ResourceService extends BaseService<Resource> {

    /**
     * 根据条件分页查询资源列表
     *
     * @param param    查询条件
     * @param pageable 分页条件
     * @return 资源列表
     */
    PageModel<Resource> findAll(Resource param, Pageable pageable);

    /**
     * 查询所有
     *
     * @param param 查询条件
     * @return 资源列表
     */
    List<Resource> list(Resource param);

    /**
     * 根据路径获取资源
     *
     * @param path 路径
     * @return 资源
     */
    Resource getResourceByPath(String path);

    /**
     * 根据resourceId 获取对应的角色
     *
     * @param id resourceId
     * @return 角色
     */
    List<Role> getRolesById(Long id);

    /**
     * 获取资源标签
     *
     * @param menuId 目录id
     * @return tags
     */
    List<Resource> getResourceTags(Long menuId);
}
