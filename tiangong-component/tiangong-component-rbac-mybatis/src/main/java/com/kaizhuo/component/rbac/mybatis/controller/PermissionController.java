package com.kaizhuo.component.rbac.mybatis.controller;

import com.kaizhuo.component.rbac.mybatis.service.PermissionService;
import com.kaizhuo.component.rbac.mybatis.vo.TreeNode;
import com.kaizhuo.core.model.dto.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.mybatis.controller
 * @description: 权限
 * @author: godric
 * @create: 2020/3/8 0008
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
@Api(tags = "系统权限")
@RestController
@RequestMapping("tiangong/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation("获取列表")
    @GetMapping("selectAll")
    public R<TreeNode> selectAll() {
        return R.ok(permissionService.selectAll());
    }

    @ApiOperation("获取某个用户的权限列表")
    @GetMapping("userId/{userId}")
    public R<Set<String>> selectByUserId(@PathVariable Integer userId) {
        return R.ok(permissionService.selectByUserId(userId));
    }
}
