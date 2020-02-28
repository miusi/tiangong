package com.kaizhuo.component.rbac.service;

import com.kaizhuo.component.rbac.model.domain.Dept;
import com.kaizhuo.core.model.dto.PageModel;
import com.kaizhuo.core.service.BaseService;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.service
 * @description:
 * @author: miaochen
 * @create: 2020-02-27 23:32
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public interface DeptService extends BaseService<Dept> {

    /**
     * 根据条件分页查询部门列表
     *
     * @param param    查询条件
     * @param pageable 分页条件
     * @return 部门列表
     */
    PageModel<Dept> findAll(Dept param, Pageable pageable);

    /**
     * 查询完整树结构
     *
     * @return 树结构
     */
    List<Dept> tree();
}
