package com.kaizhuo.component.rbac.mybatis.controller;

import com.kaizhuo.component.rbac.mybatis.pojo.Menu;
import com.kaizhuo.component.rbac.mybatis.service.MenuService;
import com.kaizhuo.component.rbac.mybatis.vo.TreeNode;
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
 * @create: 2020/3/8 0008
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
@Api(tags = "系统菜单")
@RestController
@RequestMapping("tiangong/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @ApiOperation("获取所有")
    @GetMapping("all")
    public R<List<TreeNode>> selectAll(){
        return R.ok(menuService.selectAll());
    }
    @ApiOperation("创建")
    @PostMapping("/add")
    public R insert(@RequestBody Menu menu){
        return R.ok(menuService.save(menu));
    }

    @ApiOperation("编辑")
    @PutMapping("/edit")
    public R updateById(@RequestBody Menu menu){
        return R.ok(menuService.updateById(menu));
    }
    @ApiOperation("删除")
    @PostMapping("/remove")
    public R deleteById(@PathVariable Integer id) {

        menuService.deleteById(id);
        return R.ok();
    }

}
