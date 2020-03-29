package com.kaizhuo.component.rbac.mybatis.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.mybatis.vo
 * @description:
 * @author: godric
 * @create: 2020/3/7 0007
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
@Getter
@Setter
@Accessors(chain = true)
public class TreeNode {
    private Object id;
    private Object parentId;

    @ApiModelProperty("节点名称")
    private String label;

    @ApiModelProperty("值，和ID一样")
    private Object value;

    @ApiModelProperty("等级")
    private Integer level;

    @ApiModelProperty("其他信息")
    private Object other;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    @ApiModelProperty("修改人")
    private String updateBy;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    private List<TreeNode> children;

    public TreeNode(Object id, Object parentId, String label) {
        this(id, parentId, label, null);
    }

    public TreeNode(Object id, Object parentId, String label, Object other) {
        this.id = id;
        this.value = id;
        this.parentId = parentId;
        this.label = label;
        this.other = other;
    }

    public TreeNode(Object id, Object parentId, String label, LocalDateTime createDate, String createBy, LocalDateTime updateDate, String updateBy, Object other) {
        this.id = id;
        this.value = id;
        this.parentId = parentId;
        this.label = label;
        this.other = other;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
    }
}
