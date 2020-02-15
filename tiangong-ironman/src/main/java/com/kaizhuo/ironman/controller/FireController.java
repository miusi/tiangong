package com.kaizhuo.ironman.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: tiangong
 * @package: com.kaizhuo.ironman.controller
 * @description:
 * @author: miaochen
 * @create: 2020-02-15 13:42
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@RestController
@RequestMapping("/api/fire")
public class FireController {

    @ApiOperation(value = "Fire in the hole")
    @GetMapping("hole")
    public String hole(){
        return "Fire in the hole";
    }

    @ApiOperation(value = "获取用户实名信息")
    @RequestMapping(path = "/test", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('user')")
    public String demo() {
        return "hello world!";
    }
}
