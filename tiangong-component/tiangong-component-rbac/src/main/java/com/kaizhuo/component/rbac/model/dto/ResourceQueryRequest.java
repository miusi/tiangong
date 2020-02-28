package com.kaizhuo.component.rbac.model.dto;

import cn.hutool.core.bean.BeanUtil;
import com.kaizhuo.component.rbac.model.domain.Resource;
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
@ApiModel("资源查询")
public class ResourceQueryRequest extends QueryRequest {
    private static final long serialVersionUID = 3082986066177529332L;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("标签")
    private String tag;

    @ApiModelProperty("请求方法")
    private String method;

    @ApiModelProperty("目录id")
    private Long menuId;

    public Resource toResource() {
        return BeanUtil.toBean(this, Resource.class);
    }
}
