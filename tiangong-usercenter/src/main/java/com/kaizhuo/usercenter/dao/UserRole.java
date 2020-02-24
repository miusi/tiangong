package com.kaizhuo.usercenter.dao;

import javax.persistence.*;

/**
 * @program: tiangong
 * @package: com.kaizhuo.usercenter.model
 * @description: 用户角色
 * @author: miaochen
 * @create: 2020-02-22 13:59
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Entity
@Table(name = "base_user_role")
public class UserRole {
    @Id
    @Column(name = "id",columnDefinition = "自增id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "用户Id")
    private Long userId;

    @Column(name = "角色Id")
    private Long roleId;
}
