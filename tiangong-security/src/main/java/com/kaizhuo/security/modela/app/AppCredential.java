package com.kaizhuo.security.modela.app;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @program: tiangong
 * @package: com.kaizhuo.security.modela.app
 * @description:
 * @author: godric
 * @create: 2020/3/11 0011
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AppCredential {

    @Length(min=1, max=127)
    private String appId;

    @Length(min=1, max=255)
    private String appSecret;

    @NotEmpty
    private String grantTypes;

    private String redirectUrl;

    @NotEmpty
    private String scopes;

    @NotEmpty
    private int accessExpireTime;

    @NotEmpty
    private int refreshExpireTime;
}
