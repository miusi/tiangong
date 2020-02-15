package com.kaizhuo.auth.service.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @program: tiangong
 * @package: com.kaizhuo.auth.service.jwt
 * @description: TokenKey
 * @author: miaochen
 * @create: 2020-02-14 20:51
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Configuration
public class TokenKeyConfig {

    @Value("${jwt.key.password}")
    private String password;

    @Value("${jwt.key.path}")
    private String path;

    @Value("${jwt.key.alias}")
    private String alias;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
