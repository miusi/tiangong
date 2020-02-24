package com.kaizhuo.data.jpa;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: tiangong
 * @package: com.kaizhuo.data.jpa
 * @description: 自定义实现保存
 * @author: miaochen
 * @create: 2020-02-24 21:43
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public class JpaBaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> {
    private final JpaEntityInformation entityInformation;
    private final EntityManager em;


    @Autowired
    public JpaBaseRepositoryImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.em = entityManager;
    }

    /**
     * save方法，保存或新增
     *
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    @Transactional
    public <S extends T> S save(S entity) {
        if (entityInformation.isNew(entity)) {
            em.persist(entity);
            return entity;
        } else {
            DynamicUpdate dynamicUpdateValue = entity.getClass().getAnnotation(org.hibernate.annotations.DynamicUpdate.class);
            //如果有动态更新的注解，并且值为true，那么动态更新
            if (dynamicUpdateValue != null && dynamicUpdateValue.value()) {
                //获取ID
                ID entityId = (ID) entityInformation.getId(entity);
                //查询数据库数据
                T dataFromDb = findById(entityId).get();
                //获取为空的属性-覆盖的时候忽略
                String[] ignoreProperties = getNotNullProperties(entity);
                //用数据库对象对应的信息覆盖实体中属性为null的信息
                BeanUtils.copyProperties(dataFromDb,entity,ignoreProperties);
            }
            return em.merge(entity);
        }
    }

    /**
     * 获取对象中不为null的属性
     *
     * @param src
     * @return
     */
    private static String[] getNotNullProperties(Object src) {
        //1. 获取Bean
        BeanWrapper srcBean = new BeanWrapperImpl(src);
        //2. 获取Bean的属性描述
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        //3. 获取Bean的空属性
        Set<String> properties = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : pds) {
            String propertyName = propertyDescriptor.getName();
            Object propertyValue = srcBean.getPropertyValue(propertyName);
            if (propertyValue != null) {
                properties.add(propertyName);
            }
        }
        return properties.toArray(new String[0]);
    }
}
