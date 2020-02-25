package com.kaizhuo.core.security;

import lombok.Data;

import java.io.Serializable;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: miaochen
 * \* @Date: 2020/2/25
 * \* @Time: 15:13
 * \* To change this template use File | Settings | File Templates.
 * \* @Description:
 * \
 */
@Data
public class TiangongSecurityUser implements Serializable {

    private static final long serialVersionUID = 8839024805184914251L;

    private String username;
    private String password;
}
