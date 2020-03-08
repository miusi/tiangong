package com.kaizhuo.component.rbac.mybatis.service.impl;

import com.kaizhuo.component.rbac.mybatis.mapper.PermissionMapper;
import com.kaizhuo.component.rbac.mybatis.pojo.Permission;
import com.kaizhuo.component.rbac.mybatis.service.PermissionService;
import com.kaizhuo.component.rbac.mybatis.util.CommonUtil;
import com.kaizhuo.component.rbac.mybatis.vo.TreeNode;
import com.kaizhuo.core.model.domain.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.mybatis.service.impl
 * @description:
 * @author: godric
 * @create: 2020/3/8 0008
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
@Service
public class PermissionServiceImpl extends BaseService<Permission> implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<TreeNode> selectAll() {
        List<Permission> permissions =super.list();
        //转化TreeNode
        List<TreeNode> treeNodes=permissions.stream().map(item->
                new TreeNode(item.getId(),item.getParentId(),item.getName(),item)).collect(Collectors.toList());
        return CommonUtil.listToTree(treeNodes);
    }

    @Override
    public Set<String> selectByUserId(Integer userId) {
        return permissionMapper.selectByUserId(userId);
    }
}
