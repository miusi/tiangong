package com.kaizhuo.component.rbac.model.dto;

import cn.hutool.core.bean.BeanUtil;
import com.kaizhuo.component.rbac.model.domain.Role;
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
@ApiModel("角色查询请求")
public class RoleQueryRequest extends QueryRequest {
    private static final long serialVersionUID = 4452748390646340002L;

    @ApiModelProperty("角色名称")
    private String name;

    public Role toRole(){
        return BeanUtil.toBean(this, Role.class);
    }
}
