package com.kaizhuo.component.rbac.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.model.dto
 * @description:
 * @author: miaochen
 * @create: 2020-02-28 19:57
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@ApiModel("修改用户状态")
@Data
public class UserStatusChangeRequest implements Serializable {

    private static final long serialVersionUID = 232069522782479641L;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户")
    private Integer targetStatus;
}
