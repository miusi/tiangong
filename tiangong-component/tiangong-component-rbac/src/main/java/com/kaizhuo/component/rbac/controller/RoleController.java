package com.kaizhuo.component.rbac.controller;

import com.kaizhuo.component.rbac.model.domain.Role;
import com.kaizhuo.component.rbac.model.dto.RoleQueryRequest;
import com.kaizhuo.component.rbac.model.dto.RoleSaveRequest;
import com.kaizhuo.component.rbac.service.RoleService;
import com.kaizhuo.core.model.dto.PageModel;
import com.kaizhuo.core.model.dto.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.controller
 * @description:
 * @author: miaochen
 * @create: 2020-02-28 20:46
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Api(tags = "role", description = "角色管理")
@RequestMapping("tiangong/component/rbac/role")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("分页获取角色数据")
    @GetMapping("page")
    public R<PageModel<Role>> page(@ApiParam(name = "查询参数") RoleQueryRequest queryRequest) {
        PageModel<Role> page=roleService.findAll(queryRequest.toRole(),queryRequest.toPageRequest());
        roleService.convertDataMap(page.getList());
        return R.ok(page);
    }

    @ApiOperation("获取所有角色")
    @GetMapping("list")
    public R<List<Role>> list(){
        List<Role> list=(List<Role>) roleService.findAll();
        return R.ok(list);
    }

    @ApiOperation("获取详情")
    @GetMapping("detail/{id}")
    public R<Role> detail(
            @ApiParam(name = "id") @PathVariable("id") Long id) {
        Role role = roleService.detail(id);
        roleService.convertDataMap(role);
        return R.ok(role);
    }


    @ApiOperation("保存角色信息")
    @PostMapping("save")
    public R save(@RequestBody RoleSaveRequest request) {
        Role role = request.toRole();
        roleService.save(role);
        return R.ok();
    }

    @ApiOperation("删除角色")
    @DeleteMapping("delete/{id}")
    public R delete(@ApiParam("角色id") @PathVariable("id") Long id) {
        roleService.delete(id);
        return R.ok();
    }
}
