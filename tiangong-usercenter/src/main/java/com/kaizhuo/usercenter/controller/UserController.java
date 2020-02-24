package com.kaizhuo.usercenter.controller;

import com.kaizhuo.common.api.CommonResult;
import com.kaizhuo.usercenter.dao.BaseUser;
import com.kaizhuo.usercenter.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @program: tiangong
 * @package: com.kaizhuo.usercenter.controller
 * @description:
 * @author: miaochen
 * @create: 2020-02-22 19:36
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@RestController
@Api(value = "后台用户管理", tags = "UserController")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "创建用户")
    @PostMapping("/create")
    public CommonResult create(@RequestBody BaseUser baseUser) {
        try {
            userService.add(baseUser);
            return CommonResult.success("");
        } catch (Exception e) {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "登录返回token")
    @PostMapping("/login")
    public CommonResult login(@RequestBody BaseUser baseUser) {
        String token = userService.login(baseUser.getUsername(), baseUser.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }

        return CommonResult.success(token);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/info")
    public CommonResult getUserInfo(Principal principal) {
        String userName = principal.getName();
        Optional<BaseUser> optionalBaseUser = userService.getByUserName(userName);
        if (optionalBaseUser.isPresent()) {
            Map<String, Object> map = new HashMap<>();
            map.put("username", userName);
            return CommonResult.success(map);
        } else {
            return CommonResult.failed("用户不存在");
        }
    }

}
