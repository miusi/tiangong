package com.kaizhuo.component.rbac.mybatis.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kaizhuo.core.model.domain.BaseLogEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.mybatis.pojo
 * @description: 菜单
 * @author: godric
 * @create: 2020/3/7 0007
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
@ApiModel("菜单")
@Getter
@Setter
@Accessors(chain = true)
@TableName("tiangong_menu")
public class Menu extends BaseLogEntity {
    @ApiModelProperty("菜单名称")
    private String name;

    @ApiModelProperty("父级")
    private Integer parentId;

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
