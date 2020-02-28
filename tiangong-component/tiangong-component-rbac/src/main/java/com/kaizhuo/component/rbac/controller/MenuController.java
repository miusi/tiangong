package com.kaizhuo.component.rbac.controller;

import com.google.common.collect.Maps;
import com.kaizhuo.component.rbac.model.domain.Menu;
import com.kaizhuo.component.rbac.model.domain.enums.MenuTypeEnum;
import com.kaizhuo.component.rbac.model.dto.MenuQueryRequest;
import com.kaizhuo.component.rbac.model.dto.MenuSaveRequest;
import com.kaizhuo.component.rbac.service.MenuService;
import com.kaizhuo.core.controller.BaseController;
import com.kaizhuo.core.model.dto.PageModel;
import com.kaizhuo.core.model.dto.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.controller
 * @description:
 * @author: miaochen
 * @create: 2020-02-28 21:12
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Api(tags = "menu", description = "目录管理")
@RequestMapping("tiangong/component/rbac/menu")
@RestController
public class MenuController  extends BaseController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("页面初始化参数")
    @GetMapping("toPage")
    public R toPage() {
        Map<String, Object> result = Maps.newHashMap();
        result.put("menuTypes", MenuTypeEnum.toJsonArray());
        return R.ok(result);
    }

    @ApiOperation("分页获取目录数据")
    @GetMapping("page")
    public R<PageModel<Menu>> page(
            @ApiParam(name = "查询条件") MenuQueryRequest queryRequest) {
        Sort sort = new Sort(Sort.Direction.ASC, "orderNum");
        PageModel<Menu> page = menuService.findAll(queryRequest.toMenu(), queryRequest.toPageRequest(sort));
        menuService.convertDataMap(page.getList());
        return R.ok(page);
    }

    @ApiOperation("获取详情")
    @GetMapping("detail/{id}")
    public R<Menu> detail(
            @ApiParam(name = "id") @PathVariable("id") Long id) {
        Menu menu = menuService.findById(id);
        menuService.convertDataMap(menu);
        return R.ok(menu);
    }

    @ApiOperation("获取树形结构")
    @GetMapping("tree")
    public R<List<Menu>> tree() {
        List<Menu> menus = menuService.tree();
        return R.ok(menus);
    }

    @ApiOperation("获取授权目录树")
    @GetMapping("permissionMenuTree")
    public R<List<Menu>> permissionMenuTree() {
        String username = getUsername();
        List<Menu> menus = menuService.permissionMenuTree(username);
        return R.ok(menus);
    }

    @ApiOperation("根据目录路径，获取授权页面元素")
    @GetMapping("permissionComponent")
    public R<List<Menu>> permissionComponent(@ApiParam("目录路径") String matchedPaths) {
        String username = getUsername();
        String[] matchedArray = matchedPaths.split(",");
        List<String> matchedPathList = Arrays.asList(matchedArray);
        List<Menu> menus = menuService.permissionComponent(matchedPathList, username);
        return R.ok(menus);
    }

    @ApiModelProperty("保存目录信息")
    @PostMapping("save")
    public R save(@RequestBody MenuSaveRequest request) {
        Menu menu = request.toMenu();
        menuService.save(menu);
        return R.ok();
    }

    @ApiModelProperty("删除目录信息")
    @DeleteMapping("delete/{id}")
    public R delete(@ApiParam("目录id") @PathVariable("id") Long id) {
        menuService.delete(id);
        return R.ok();
    }
}
