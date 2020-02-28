package com.kaizhuo.component.rbac.model.dto;

import cn.hutool.core.bean.BeanUtil;
import com.kaizhuo.component.rbac.model.domain.Menu;
import com.kaizhuo.core.model.dto.QueryRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.model.dto
 * @description: 用户查询
 * @author: miaochen
 * @create: 2020-02-28 19:47
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("部门查询请求")
public class MenuQueryRequest extends QueryRequest {
    private static final long serialVersionUID = 8496684025572743107L;

    @ApiModelProperty("父菜单ID，一级菜单为0")
    private Long parentId;

    @ApiModelProperty("菜单名称")
    private String name;

    @ApiModelProperty("类型     0：目录   1：菜单   2：按钮")
    private Integer type;

    public Menu toMenu() {
        parentId = parentId == null ? 0 : parentId;
        Menu menu = BeanUtil.toBean(this, Menu.class);
        menu.setParent(new Menu(parentId));
        return menu;
    }
}
