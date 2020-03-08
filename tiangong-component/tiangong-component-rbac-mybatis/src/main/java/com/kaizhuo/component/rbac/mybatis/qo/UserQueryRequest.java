package com.kaizhuo.component.rbac.mybatis.qo;

import com.kaizhuo.core.model.domain.BaseQueryRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.mybatis.qo
 * @description:
 * @author: godric
 * @create: 2020/3/7 0007
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
@Data
public class UserQueryRequest extends BaseQueryRequest {

    @ApiModelProperty("搜索-手机号")
    private String phone;

    @ApiModelProperty("搜索-用户姓名")
    private String name;
}
