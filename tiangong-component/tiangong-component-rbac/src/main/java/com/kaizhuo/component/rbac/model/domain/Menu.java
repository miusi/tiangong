package com.kaizhuo.component.rbac.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kaizhuo.core.model.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

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
@Table(name = "tiangong-rbac-menu")
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
}
