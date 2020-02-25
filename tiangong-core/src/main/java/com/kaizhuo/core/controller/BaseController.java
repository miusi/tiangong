package com.kaizhuo.core.controller;

import com.kaizhuo.core.exception.AppException;
import com.kaizhuo.core.security.TiangongSecurityUtil;

/**
 * @program: tiangong
 * @package: com.kaizhuo.tiangongcore.controller
 * @description:
 * @author: miaochen
 * @create: 2020-02-25 07:51
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public class BaseController {
    protected String getUsername() {
        String username = TiangongSecurityUtil.getUsername();
        if (username == null) {
            throw new AppException();
        }

        return TiangongSecurityUtil.getUsername();
    }
}
