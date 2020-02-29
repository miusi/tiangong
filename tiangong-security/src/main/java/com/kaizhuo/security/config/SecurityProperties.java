package com.kaizhuo.security.config;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.security.config
 * @description: Security 配置文件
 * @author: miaochen
 * @create: 2020-02-29 13:21
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Data
@Component
@ConfigurationProperties(prefix = SecurityProperties.PREFIX)
public class SecurityProperties {

    /***
     * 系统配置敞亮
     */
    /***配置前缀*/
    public static final String PREFIX = "tiangong.security";

    /***授权key*/
    public static final String authKey = "authorization";

    /***授权Bearer key*/
    public static final String authBearKey = "Bearer ";

    /***不需要授权的路径*/
    private List<String> noAuthPaths;

    /***token过期时间--单位秒*/
    private int tokenExpiredTime;

    private String loginUrl;

    @PostConstruct
    public void init(){
        noAuthPaths = CollectionUtil.isEmpty(noAuthPaths) ? Lists.newArrayList() : noAuthPaths;
    }

    public int getTokenExpiredTime(){
        return tokenExpiredTime <= 300 ? 300 : tokenExpiredTime;
    }
}
