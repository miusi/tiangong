package com.kaizhuo.component.rbac.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kaizhuo.core.model.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
@Table(name = "tiangong_rbac_user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@DynamicUpdate
@Where(clause = "yn=1")
public class User extends BaseDomain {

    private static final long serialVersionUID = 4345940515014716863L;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @ApiModelProperty("盐")
    private String salt;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("尝试次数")
    private Integer triedTimes;

    @ApiModelProperty("锁定时间")
    private Date lockedTime;

    @ApiModelProperty("状态  0：禁用 1：正常  2：锁定")
    private Integer status;


    @ApiModelProperty("部门id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    @JsonBackReference
    private Dept dept;

    @ApiModelProperty("用户角色")
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tiangong_rbac_user_role"
            , joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

}
