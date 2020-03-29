package com.kaizhuo.component.rbac.mybatis.controller;

import com.kaizhuo.component.rbac.mybatis.pojo.Dept;
import com.kaizhuo.component.rbac.mybatis.service.DeptService;
import com.kaizhuo.component.rbac.mybatis.vo.TreeNode;
import com.kaizhuo.core.model.dto.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.mybatis.controller
 * @description: 部门
 * @author: godric
 * @create: 2020/3/7 0007
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
@Api(tags = "部门")
@RestController
@RequestMapping("tiangong/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @ApiOperation("获取部门树")
    @GetMapping("all")
    public R<TreeNode> selectAll(){
        return R.ok(deptService.selectAll());
    }
    @ApiOperation("获取单个数据")
    @GetMapping("{id}")
    public R<Dept> selectById(@PathVariable Integer id) {
        return R.ok(deptService.getById(id));
    }

    @ApiOperation("创建")
    @PostMapping("/add")
    public R insert(@RequestBody Dept dept) {
        deptService.save(dept);
        return R.ok();
    }

    @ApiOperation("编辑")
    @PostMapping("/edit")
    public R updateById(@RequestBody Dept dept) {
        deptService.updateById(dept);
        return R.ok();
    }

    @ApiOperation("删除")
    @PostMapping("/remove")
    public R deleteById(@PathVariable Integer id) {
        deptService.deleteById(id);
        return R.ok();
    }
}
