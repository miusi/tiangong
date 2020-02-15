package com.kaizhuo.auth.service.interfaces;

/**
 * @program: tiangong
 * @package: com.kaizhuo.auth.service.interfaces
 * @description: 用户和密码操作
 * @author: miaochen
 * @create: 2020-02-14 19:15
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public interface UsernamePasswordUserDetailService {

    boolean verifyCredential(String name,String password);

    Object getUserDetails(String username);
}
