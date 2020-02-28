package com.kaizhuo.component.rbac.model.dto;

import cn.hutool.core.bean.BeanUtil;
import com.kaizhuo.component.rbac.model.domain.User;
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
@ApiModel("用户查询请求")
@Data
public class UserQueryRequest extends QueryRequest {


    private static final long serialVersionUID = 4345637585186193599L;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("真实姓名")
    private String realname;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("部门Id")
    private Long deptId;

    @ApiModelProperty("状态： 0：禁用 1：正常 2：锁定")
    private Integer status;

    public User toUser(){
        return BeanUtil.toBean(this,User.class);
    }
}
