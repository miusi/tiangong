package com.kaizhuo.component.rbac.mybatis.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kaizhuo.core.model.domain.BaseLogEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.mybatis.pojo
 * @description: 部门
 * @author: godric
 * @create: 2020/3/7 0007
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
@ApiModel("部门Entity")
@Getter
@Setter
@Accessors(chain = true)
@TableName("tiangong_dept")
public class Dept extends BaseLogEntity {

    @ApiModelProperty("部门名称")
    private String name;

    private Integer parentId;
}
