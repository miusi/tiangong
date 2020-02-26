package com.kaizhuo.component.rbac.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kaizhuo.core.model.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.model.domain
 * @description: 用户
 * @author: miaochen
 * @create: 2020-02-25 22:32
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@ApiModel("用户")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tiangong-rbac-user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@DynamicUpdate
@Where(clause = "yn=1")
public class User extends BaseDomain {

    private static final long serialVersionUID = 4345940515014716863L;

    @ApiModelProperty("用户名")
    @Column(name = "username")
    private String username;

    @ApiModelProperty("密码")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;
    @ApiModelProperty("盐")
    private String salt;

    @ApiModelProperty("真实姓名")
    @Column(name = "realname")
    private String realName;

    @ApiModelProperty("邮箱")
    @Column(name = "email")
    private String email;

    @ApiModelProperty("手机号")
    @Column(name = "mobile")
    private String mobile;

    @ApiModelProperty("尝试次数")
    @Column(name = "tried_times")
    private Integer triedTimes;

    @ApiModelProperty("锁定时间")
    @Column(name = "locked_time")
    private Date lockedTime;

    @ApiModelProperty("状态  0：禁用 1：正常  2：锁定")
    private Integer status;


}
