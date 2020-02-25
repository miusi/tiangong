package com.kaizhuo.core.model.domain;

import com.kaizhuo.core.model.domain.enums.YnEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: miaochen
 * \* @Date: 2020/2/25
 * \* @Time: 14:54
 * \* To change this template use File | Settings | File Templates.
 * \* @Description:
 * \
 */
@ApiModel
@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseDomain implements Serializable {
    private static final long serialVersionUID = 874312453215035497L;

    @Id
    @GeneratedValue
    protected Long id;

    @ApiModelProperty("创建时间")
    @CreatedDate
    protected Date createdDate;

    @ApiModelProperty("创建人")
    @CreatedBy
    protected String createdBy;

    @ApiModelProperty("最后更新时间")
    @LastModifiedDate
    protected Date updatedDate;

    @ApiModelProperty("更新人")
    @LastModifiedBy
    protected String updatedBy;

    @ApiModelProperty("是否删除 0-未删除，1-已删除")
    protected Integer yn = YnEnums.Y.getKey();

    @ApiModelProperty("扩展信息")
    @Transient
    protected Map<String, Object> dataMap = new HashMap<>();

    public void addDataMap(String key, Object value){
        dataMap.put(key, value);
    }
}
