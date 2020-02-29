package com.kaizhuo.security.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.kaizhuo.security.auth.common.TiangongUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: tiangong
 * @package: com.kaizhuo.security.auth.jwt
 * @description:
 * @author: miaochen
 * @create: 2020-02-29 12:19
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Slf4j
@Service
public class JwtUserDetailsService extends TiangongUserDetailsService {

    private static final String SALT = "123456";

    /**
     * 登录成功
     *
     * @param userDetails
     * @return
     */
    public String loginSuccess(UserDetails userDetails) {
        Algorithm algorithm = Algorithm.HMAC256(SALT);
        Date date = new Date(System.currentTimeMillis() + 3600 * 1000);
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    /**
     * 获取用户登录信息
     * @param username  用户名
     * @return
     */
    public UserDetails getUserLoginInfo(String username){
        UserDetails userDetails=loadUserByUsername(username);
        if(userDetails!=null){

        }
        return userDetails;
    }

    /**
     * 清理用户登录信息
     *
     * @param username 用户名
     */
    public void deleteUserLoginInfo(String username) {
        // todo
    }

}
