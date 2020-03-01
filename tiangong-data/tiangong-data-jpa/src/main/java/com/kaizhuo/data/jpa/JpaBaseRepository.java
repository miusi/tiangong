package com.kaizhuo.data.jpa;

import com.kaizhuo.data.core.BaseRepository;
import com.kaizhuo.data.jpa.support.ByExampleSpecification;
import com.kaizhuo.data.jpa.support.ByRangeSpecifition;
import com.kaizhuo.data.jpa.support.Range;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

import static org.springframework.data.jpa.domain.Specifications.where;



/**
 * @program: tiangong
 * @package: com.kaizhuo.data.jpa
 * @description:
 * @author: miaochen
 * @create: 2020-02-23 22:44
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@NoRepositoryBean
public interface JpaBaseRepository<T>  extends BaseRepository<T>, JpaRepository<T, Serializable>, JpaSpecificationExecutor {
    default Page<T> findAll(Example example, List<Range<T>> ranges, Pageable pageable){
        Specification<T> byExample=new ByExampleSpecification<>(example);
        Specification<T> byRanges=new ByRangeSpecifition<>(ranges);
        return findAll(where(byExample).and(byRanges),pageable);
    }
}
