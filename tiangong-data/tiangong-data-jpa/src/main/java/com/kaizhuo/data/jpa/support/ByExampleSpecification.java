package com.kaizhuo.data.jpa.support;

import com.sun.istack.internal.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @program: tiangong
 * @package: com.kaizhuo.data.jpa.support
 * @description: 指定条件查询
 * @author: miaochen
 * @create: 2020-02-24 21:21
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public class ByExampleSpecification<T> implements Specification<T> {

    private static final long serialVersionUID = -8405979176510982471L;

    private final Example<T> example;

    public ByExampleSpecification(@NotNull Example<T> example) {
        this.example = example;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, @NotNull CriteriaBuilder criteriaBuilder) {
        return QueryByExamplePredicateBuilder.getPredicate(root, criteriaBuilder, example);
    }
}
