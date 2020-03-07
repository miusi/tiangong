package com.kaizhuo.component.rbac.mybatis.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaizhuo.component.rbac.mybatis.pojo.Role;
import com.kaizhuo.component.rbac.mybatis.service.RoleService;
import com.kaizhuo.core.model.domain.BaseQO;
import com.kaizhuo.core.model.dto.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.mybatis.controller
 * @description:
 * @author: godric
 * @create: 2020/3/7 0007
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
@Api(tags = "系统角色")
@RestController
@RequestMapping("tiangong/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ApiOperation("获取角色列表")
    @GetMapping("page")
    public R<Page<Role>> page(BaseQO qo) {
        IPage<Role> page = roleService.page(new Page<>(qo.getPageNum(), qo.getPageSize()));
        return R.ok(page);
    }

    @ApiOperation("获取全部列表")
    @GetMapping("all")
    public R<List<Role>> findAll(){
        return R.ok(roleService.list());
    }

    @ApiOperation("获取详情")
    @GetMapping("{id}")
    public R<Role> selectById(@PathVariable Integer id){
        Role role=roleService.selectById(id);
        return R.ok(role);
    }

    @ApiOperation("创建")
    @PostMapping("add")
    public R insert(@RequestBody Role role){
        roleService.insertRole(role);
        return R.ok();
    }

    @ApiOperation("编辑")
    @PutMapping("updateByKey")
    public R updateByPrimaryKey(@RequestBody Role role){
        roleService.updateRole(role);
        return R.ok();
    }

    @ApiOperation("删除")
    @DeleteMapping("{id}")
    public R deleteByPrimaryKey(@PathVariable Integer id) {
        roleService.deleteRole(id);
        return R.ok();
    }
}
