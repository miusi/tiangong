package com.kaizhuo.core.model.domain;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaizhuo.core.exception.AppException;
import org.springframework.core.GenericTypeResolver;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * @program: tiangong
 * @package: com.kaizhuo.core.model.domain
 * @description:
 * @author: godric
 * @create: 2020/3/7 0007
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
public class BaseService<T> extends ServiceImpl<BaseMapper<T>,T> implements IService<T> {
    /**
     * 判断非空
     */
    protected static boolean isNotEmpty(Integer value) {
        return value != null;
    }

    /**
     * 判断非空
     */
    protected static boolean isNotEmpty(BigDecimal value) {
        return value != null;
    }

    /**
     * 字符串判断非空
     */
    protected static boolean isNotEmpty(String str) {
        return StringUtils.isNotBlank(str);
    }

    /**
     * 集合判断是否非空
     */
    protected static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 集合判断是否空
     */
    protected static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断某个字段是否唯一，不唯一抛出异常
     *
     * @param fn        要检查的字段
     * @param value     要检查的字段值
     * @param excludeId 要排除的id，可以为null，新增不用排除自身
     * @param errorMsg  错误提示文字
     */
    public void checkUnique(SFunction<T,Object> fn, Object value, @NotNull Integer excludeId,String errorMsg){
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if (excludeId != null) {
            queryWrapper.ne("id", excludeId);
        }
        LambdaQueryWrapper<T> lambdaQueryWrapper = queryWrapper.lambda();
        lambdaQueryWrapper.eq(fn, value);
        int count = this.count(lambdaQueryWrapper);
        if (count > 0) {
            throw new AppException(errorMsg);
        }
    }

    /**
     * 覆写mybatis-plus获取泛型class方式，防止多重继承时无法获取class
     */
    @SuppressWarnings("unchecked")
    @Override
    protected Class<T> currentModelClass() {
        return (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), BaseService.class);
    }
}
