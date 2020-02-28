package com.kaizhuo.component.rbac.controller;

import com.kaizhuo.component.rbac.model.domain.Resource;
import com.kaizhuo.component.rbac.model.dto.ResourceQueryRequest;
import com.kaizhuo.component.rbac.model.dto.ResourceSaveRequest;
import com.kaizhuo.component.rbac.service.ResourceService;
import com.kaizhuo.core.controller.BaseController;
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
 * @create: 2020-02-28 21:03
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Api(tags = "resource", description = "资源管理")
@RequestMapping("tiangong/component/rbac/resource")
@RestController
public class ResourceController  extends BaseController {
    @Autowired
    private ResourceService resourceService;

    @ApiOperation("分页获取数据资源")
    @GetMapping("page")
    public R<PageModel<Resource>> page(
            @ApiParam(name = "查询条件") ResourceQueryRequest queryRequest) {
        PageModel<Resource> page = resourceService.findAll(queryRequest.toResource(), queryRequest.toPageRequest());
        return R.ok(page);
    }

    @ApiOperation("查询标签树")
    @GetMapping("tagTree")
    public R<List<Resource>> tagTree(@ApiParam(name = "目录Id") Long menuId) {
        List<Resource> tags = resourceService.getResourceTags(menuId);
        return R.ok(tags);
    }

    @ApiOperation("获取详情")
    @GetMapping("detail/{id}")
    public R<Resource> detail(
            @ApiParam(name = "id") @PathVariable("id") Long id) {
        Resource resource = resourceService.findById(id);
        return R.ok(resource);
    }

    @ApiOperation("保存资源信息")
    @PostMapping("save")
    public R save(@RequestBody ResourceSaveRequest request) {
        Resource resource = request.toResource();
        resourceService.save(resource);
        return R.ok();
    }

    @ApiOperation("删除资源信息")
    @DeleteMapping("delete/{id}")
    public R delete(@ApiParam("资源id") @PathVariable("id") Long id) {
        resourceService.delete(id);
        return R.ok();
    }
}
