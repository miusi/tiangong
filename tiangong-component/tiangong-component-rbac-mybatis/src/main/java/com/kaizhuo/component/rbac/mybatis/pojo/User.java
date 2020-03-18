package com.kaizhuo.component.rbac.mybatis.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kaizhuo.core.model.domain.BaseLogEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.mybatis.pojo
 * @description: 用户
 * @author: godric
 * @create: 2020/3/7 0007
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
@ApiModel("系统用户")
@Getter
@Setter
@TableName("tiangong_user")
public class User extends BaseLogEntity {

    @ApiModelProperty("登录名,备用字段,目前随机自动生成")
    private String username;


    @ApiModelProperty("密码")
    @JsonIgnore
    private String password;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("包含的角色")
    private transient List<Role> roles;
}
