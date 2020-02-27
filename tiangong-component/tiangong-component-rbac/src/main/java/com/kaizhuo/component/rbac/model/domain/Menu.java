package com.kaizhuo.component.rbac.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.collect.Lists;
import com.kaizhuo.core.model.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.model.domain
 * @description: 菜单
 * @author: miaochen
 * @create: 2020-02-25 22:36
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@ApiModel("目录菜单")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tiangong_rbac_menu")
@DynamicUpdate
@Where(clause = "yn = 1")
public class Menu extends BaseDomain {

    private static final long serialVersionUID = 2282460696424132106L;

    @ApiModelProperty("菜单名称")
    private String name;

    @ApiModelProperty("父级")
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Menu parent;

    @ApiModelProperty("菜单URL")
    private String url;

    @ApiModelProperty("类型  1：菜单   2：页面组件")
    private Integer type;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("是否显示")
    private Integer visible;

    @ApiModelProperty("排序")
    private Integer orderNum;

    @ApiModelProperty("菜单与资源")
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tiangong_rbac_menu_resource",
            joinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id"))
    private List<Resource> resources = Lists.newArrayList();

    @ApiModelProperty("菜单与角色")
    @JsonIgnore
    @ManyToMany(mappedBy = "menus")
    private List<Role> roles = Lists.newArrayList();

    @ApiModelProperty("子级")
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
    private List<Menu> children;

    public Menu(Long id) {
        this.id = id;
    }
}
