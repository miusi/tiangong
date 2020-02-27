package com.kaizhuo.component.rbac.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kaizhuo.core.model.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.model.domain
 * @description: 角色
 * @author: miaochen
 * @create: 2020-02-25 22:34
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@ApiModel("角色")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tiangong_rbac_role", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Role extends BaseDomain {
    private static final long serialVersionUID = -5226442007656778582L;

    @ApiModelProperty("角色名称")
    @Column(name = "name")
    private String name;

    @ApiModelProperty("备注")
    @Column(name = "remark")
    private String remark;

    @ApiModelProperty("目录")
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tiangong_rbac_role_menu",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "menu_id", referencedColumnName = "id")})
    private List<Menu> menus;

    @ApiModelProperty("目录")
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "tiangong_rbac_user_role",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    public List<User> users;
}
