package com.kaizhuo.data.jpa;

import com.kaizhuo.data.core.BaseRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.List;

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
public interface JpaBaseRepository<T>  extends BaseRepository<T>, JpaRepository<T, Serializable>, JpaSpecificationExecutor {
    default Page<T> findAll(Example example, List<Range<T>> ranges, Pageable pageable){

    }
}
