package com.kaizhuo.component.rbac.model.dto;

import cn.hutool.core.bean.BeanUtil;
import com.kaizhuo.component.rbac.model.domain.Dept;
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
public class DeptQueryRequest extends QueryRequest {

    private static final long serialVersionUID = 561115640484647662L;

    @ApiModelProperty("上级部门ID，一级部门为0")
    private Long parentId;

    @ApiModelProperty("部门名称")
    private String name;

    public Dept toDept() {
        parentId = parentId == null ? 0L : parentId;
        Dept dept = BeanUtil.toBean(this, Dept.class);
        dept.setParent(new Dept(parentId));
        return dept;
    }
}
