package com.kaizhuo.component.rbac.controller;

import com.kaizhuo.component.rbac.model.domain.User;
import com.kaizhuo.component.rbac.model.dto.UserQueryRequest;
import com.kaizhuo.component.rbac.model.dto.UserSaveRequest;
import com.kaizhuo.component.rbac.model.dto.UserStatusChangeRequest;
import com.kaizhuo.component.rbac.service.UserService;
import com.kaizhuo.core.controller.BaseController;
import com.kaizhuo.core.model.dto.PageModel;
import com.kaizhuo.core.model.dto.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.controller
 * @description:
 * @author: miaochen
 * @create: 2020-02-28 19:45
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Api(tags = "user", description = "用户管理")
@RequestMapping("tiangong/component/rbac/user")
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation("分页获取用户数据")
    @GetMapping("page")
    public R<PageModel<User>> page(
            @ApiParam(name = "查询条件") UserQueryRequest queryRequest) {
        PageModel<User> page = userService.findAll(queryRequest.toUser(), queryRequest.toPageRequest());
        userService.convertDataMap(page.getList());
        return R.ok(page);
    }

    @ApiOperation("获取详情")
    @GetMapping("detail/{id}")
    public R<User> detail(
            @ApiParam(name = "id") @PathVariable("id") Long id
            ){
        User user =userService.detail(id);
        userService.convertDataMap(user);
        return R.ok(user);
    }

    @ApiOperation("保存用户信息")
    @PostMapping("save")
    public R save(@RequestBody UserSaveRequest request){
        User user=request.toUser();
        userService.save(user);
        return R.ok();
    }

    @ApiOperation("修改密码")
    @PostMapping("changePassword")
    public R changePassword(@ApiParam("新密码") String newPassword){
        userService.updatePasswordByUsername(getUsername(),newPassword);
        return R.ok();
    }

    @ApiOperation("修改状态")
    @PostMapping("changeStatus")
    public R changeStatus(@RequestBody UserStatusChangeRequest request) {
        userService.changeStatus(request.getUserId(), request.getTargetStatus());
        return R.ok();
    }


    @ApiOperation("重置密码")
    @PostMapping("restPassword/{id}")
    public R resetPassword(@ApiParam("用户id") @PathVariable("id") Long id) {
        userService.resetPasswordByUserId(id);
        return R.ok();
    }

    @ApiOperation("删除用户")
    @DeleteMapping("delete/{id}")
    public R delete(@ApiParam("用户id") @PathVariable("id") Long id) {
        userService.delete(id);
        return R.ok();
    }
}
