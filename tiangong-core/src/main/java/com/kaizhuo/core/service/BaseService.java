package com.kaizhuo.core.service;

import cn.hutool.core.collection.CollectionUtil;
import com.kaizhuo.core.model.domain.BaseDomain;
import com.kaizhuo.core.model.dto.PageModel;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collection;

/**
 * 基础service类
 */
public interface BaseService<T extends BaseDomain> {

    /**
     * 保存记录
     * @param t
     * @return
     */
    T save(T t);

    /**
     * 批量保持记录
     * @param ts
     * @return
     */
    Iterable<T> saveAll(Iterable<T> ts);

    /**
     * 根据Id 查询一条记录
     * @param id
     * @return
     */
    T findById(Object id);

    /**
     * 查询所有记录
     *
     * @return 所有记录
     */
    Iterable<T> findAll();

    /**
     * 根据ids查询所有记录
     *
     * @param ids ids
     * @return ids包含的记录
     */
    Iterable<T> findAllById(Iterable<Serializable> ids);


    /**
     * 分页查询记录
     *
     * @param pageable 分页参数
     * @return 分页记录
     */
    PageModel<T> findAll(Pageable pageable);

    /**
     * 根据id删除
     *
     * @param id id
     */
    void delete(Serializable id);

    /**
     * 删除对象
     *
     * @param t 要删除的对象
     */
    void delete(T t);

    /**
     * 批量删除对象
     *
     * @param ts 要批量删除的对象
     */
    void delete(Iterable<? extends T> ts);

    /**
     * 根据id删除
     *
     * @param id       id
     * @param physical 物理删除
     */
    void delete(Serializable id, boolean physical);

    /**
     * 删除对象
     *
     * @param t        要删除的对象
     * @param physical 物理删除
     */
    void delete(T t, boolean physical);

    /**
     * 批量删除对象
     *
     * @param ts       要批量删除的对象
     * @param physical 物理删除
     */
    void delete(Iterable<? extends T> ts, boolean physical);

    /**
     * 属性转换
     *
     * @param ts 要转换的实例
     */
    default void convertDataMap(T... ts) {
        if (ts != null && ts.length > 0) {
            this.doConvertDataMap(ts);
        }
    }

    /**
     * 属性批量转换
     *
     * @param ts 要转换的实例
     */
    default void convertDataMap(Collection<T> ts) {
        if (CollectionUtil.isNotEmpty(ts)) {
            Class clazz = null;
            for (T t : ts) {
                clazz = t.getClass();
                break;
            }
            if (clazz != null){
                this.convertDataMap(ts.toArray((T[]) Array.newInstance(clazz, 0)));
            }
        }
    }

    /**
     * 真正的属性转换，减少校验代码
     *
     * @param ts ts
     */
    default void doConvertDataMap(T... ts){}
}
