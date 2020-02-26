package com.kaizhuo.component.rbac.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kaizhuo.core.model.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.model
 * @description: 部门
 * @author: miaochen
 * @create: 2020-02-25 21:57
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@ApiModel("部门")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
@Table(name = "tiangong-rbac-dept")
@DynamicUpdate
@Where(clause = "yn = 1")
public class Dept extends BaseDomain {

    private static final long serialVersionUID = 8486584607874618300L;

    @ApiModelProperty("父级")
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Dept parent;

    @ApiModelProperty("部门名称")
    private String name;

    @ApiModelProperty("排序")
    private Integer orderNum;

}
