package com.kaizhuo.component.rbac.mybatis.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kaizhuo.core.model.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.mybatis.pojo
 * @description: 系统权限
 * @author: godric
 * @create: 2020/3/7 0007
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
@ApiModel("系统权限")
@Getter
@Setter
@TableName("tiangong_permission")
public class Permission extends BaseEntity {
    @ApiModelProperty("编号")
    private String code;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("父级ID")
    private Integer parentId;
}
