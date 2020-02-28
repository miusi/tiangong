package com.kaizhuo.component.rbac.model.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.kaizhuo.component.rbac.model.domain.Dept;
import com.kaizhuo.component.rbac.model.domain.Role;
import com.kaizhuo.component.rbac.model.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.model.dto
 * @description:
 * @author: miaochen
 * @create: 2020-02-28 19:52
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@ApiModel("用户保存请求")
@Data
public class UserSaveRequest implements Serializable {

    private static final long serialVersionUID = -8973589647887096315L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("部门id")
    private Long deptId;

    @ApiModelProperty("角色id")
    private List<Long> roleIds;

    public User toUser() {
        User user = BeanUtil.toBean(this, User.class);
        //角色
        if (CollectionUtil.isNotEmpty(roleIds)) {
            List<Role> roles = new ArrayList<Role>();
            for (Long roleId : roleIds) {
                Role role=new Role();
                role.setId(roleId);
                roles.add(role);
            }
            user.setRoles(roles);
        }

        if(deptId!=null){
            user.setDept(new Dept(deptId));
        }

        return user;
    }
}
