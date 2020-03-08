package com.kaizhuo.component.rbac.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kaizhuo.component.rbac.mybatis.pojo.Permission;
import com.kaizhuo.component.rbac.mybatis.vo.TreeNode;

import java.util.List;
import java.util.Set;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.mybatis.service
 * @description:
 * @author: godric
 * @create: 2020/3/7 0007
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
public interface PermissionService extends IService<Permission> {
    List<TreeNode> selectAll();

    Set<String> selectByUserId(Integer userId);
}
