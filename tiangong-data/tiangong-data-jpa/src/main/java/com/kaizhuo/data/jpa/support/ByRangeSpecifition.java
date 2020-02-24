package com.kaizhuo.data.jpa.support;

import com.sun.istack.internal.NotNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @program: tiangong
 * @package: com.kaizhuo.data.jpa.support
 * @description: 分页查询
 * @author: miaochen
 * @create: 2020-02-24 21:24
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public class ByRangeSpecifition<T> implements Specification<T> {
    private final List<Range<T>> ranges;

    public ByRangeSpecifition(List<Range<T>> ranges) {
        this.ranges = ranges;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        for (Range<T> range : ranges) {
            if (range.isSet()) {
                Predicate rangePredicate = buildRangePredicate(range, root, criteriaBuilder);

                if (rangePredicate != null) {
                    if (!range.isIncludeNullSet() || range.getIncludeNull().equals(FALSE)) {
                        predicates.add(rangePredicate);
                    } else {
                        predicates.add(criteriaBuilder.or(rangePredicate, criteriaBuilder.isNull(root.get(range.getField()))));
                    }
                }

                if (TRUE.equals(range.getIncludeNull())) {
                    predicates.add(criteriaBuilder.isNull(root.get(range.getField())));
                } else if (FALSE.equals(range.getIncludeNull())) {
                    predicates.add(criteriaBuilder.isNotNull(root.get(range.getField())));
                }
            }
        }
        Predicate[] predicateArray = (Predicate[]) predicates.toArray();
        return predicates.isEmpty() ? criteriaBuilder.conjunction() : criteriaBuilder.and(predicateArray);
    }

    private Predicate buildRangePredicate(@NotNull Range<T> range, @NotNull Root<T> root, @NotNull CriteriaBuilder builder) {
        if (range.isBetween()) {
            return builder.between(root.get(range.getField()), range.getFrom(), range.getTo());
        } else if (range.isFromSet()) {
            return builder.greaterThanOrEqualTo(root.get(range.getField()), range.getFrom());
        } else if (range.isToSet()) {
            return builder.lessThanOrEqualTo(root.get(range.getField()), range.getTo());
        }
        return null;
    }

}
