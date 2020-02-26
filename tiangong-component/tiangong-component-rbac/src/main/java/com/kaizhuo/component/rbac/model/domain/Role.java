package com.kaizhuo.component.rbac.model.domain;

import com.kaizhuo.core.model.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.model.domain
 * @description: 角色
 * @author: miaochen
 * @create: 2020-02-25 22:34
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@ApiModel("角色")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tiangong-rbac-role", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Role extends BaseDomain {
    private static final long serialVersionUID = -5226442007656778582L;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("备注")
    private String remark;


}
