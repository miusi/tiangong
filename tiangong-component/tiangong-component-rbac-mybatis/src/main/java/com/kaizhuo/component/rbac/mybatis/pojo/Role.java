package com.kaizhuo.component.rbac.mybatis.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kaizhuo.core.model.domain.BaseLogEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.mybatis.pojo
 * @description: 系统角色
 * @author: godric
 * @create: 2020/3/7 0007
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
@ApiModel("系统角色")
@Getter
@Setter
@TableName("tiangong_role")
public class Role extends BaseLogEntity {
    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("备注")
    private String remarks;

    @ApiModelProperty("权限列表")
    private transient List<Permission> Permissions;
}
