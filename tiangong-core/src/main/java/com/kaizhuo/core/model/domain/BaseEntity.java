package com.kaizhuo.core.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.kaizhuo.core.exception.AppException;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @program: tiangong
 * @package: com.kaizhuo.core.model.domain
 * @description: 实体父类
 * @author: godric
 * @create: 2020/3/7 0007
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
@Data
public class BaseEntity {
    @TableId(type= IdType.AUTO)
    private Integer id;

    /**
     * model和vo互转
     */
    public <D> D toBean(Class<D> clazz) {
        try {
            D d = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(this, d);
            return d;
        } catch (Exception e) {
            throw new AppException(e);
        }
    }
}
