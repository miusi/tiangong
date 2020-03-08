package com.kaizhuo.component.rbac.mybatis.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaizhuo.component.rbac.mybatis.pojo.User;
import com.kaizhuo.component.rbac.mybatis.qo.UserQueryRequest;
import com.kaizhuo.component.rbac.mybatis.service.UserService;
import com.kaizhuo.component.rbac.mybatis.vo.UserVo;
import com.kaizhuo.core.model.dto.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "用户")
@RestController
@RequestMapping("/tiangong/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("获取用户列表")
    @GetMapping("/page")
    public Page<UserVo> findWithPage(UserQueryRequest qo){
        return userService.findWithPage(qo);
    }

    @ApiOperation("获取用户详情")
    @GetMapping("{id}")
    public R<UserVo> getById(@PathVariable Integer id){
        UserVo userVo= userService.findById(id);
        return R.ok(userVo);
    }

    @ApiOperation("新增用户")
    @PostMapping("/add")
    public R insert(@RequestBody User user){
        userService.insert(user);
        return R.ok();
    }

    @ApiOperation("编辑用户")
    @PostMapping("/edit")
    public R updateByPrimaryKey(@RequestBody User user){
        userService.updateByPrimaryKey(user);
        return R.ok();
    }

    @ApiOperation("禁用启用账号")
    @PutMapping("{id}/toggleStatus")
    public R toggleStatus(@PathVariable Integer id){
        userService.toggleStatus(id);
        return R.ok();
    }

    @ApiOperation("重置密码")
    @PutMapping("{id}/resetPassword")
    public R resetPassword(@PathVariable Integer id){
        userService.updatePassword(id,"123456");
        return R.ok();
    }

    @ApiOperation("删除")
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id) {
        userService.removeById(id);
    }

//    @ApiOperation("修改密码")
//    @GetMapping("changePassword")
//    public void changePassword(@RequestParam @ApiParam(value = "原密码", required = true) String oldPassword,
//                               @RequestParam @ApiParam(value = "新密码", required = true) String newPassword) {
//        userService.updatePassword(oldPassword, newPassword);
//    }
}
