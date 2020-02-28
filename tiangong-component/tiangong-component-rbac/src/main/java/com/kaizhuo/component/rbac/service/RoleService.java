package com.kaizhuo.component.rbac.service;

import com.kaizhuo.component.rbac.model.domain.Role;
import com.kaizhuo.core.model.dto.PageModel;
import com.kaizhuo.core.service.BaseService;
import org.springframework.data.domain.Pageable;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.service
 * @description:
 * @author: miaochen
 * @create: 2020-02-27 23:33
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public interface RoleService extends BaseService<Role> {

    /**
     * 根据条件分页查询
     *
     * @param param     查询条件
     * @param pageable  分页参数
     * @return
     */
    PageModel<Role> findAll(Role param, Pageable pageable);

    /**
     * 查询角色详情
     *
     * @param id    角色id
     * @return
     */
    Role detail(Long id);
}
