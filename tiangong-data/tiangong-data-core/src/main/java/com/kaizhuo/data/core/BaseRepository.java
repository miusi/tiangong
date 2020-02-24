package com.kaizhuo.data.core;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * @program: tiangong
 * @package: com.kaizhuo.data.core
 * @description:
 * @author: miaochen
 * @create: 2020-02-23 22:37
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@NoRepositoryBean
public interface BaseRepository<T> extends PagingAndSortingRepository<T, Serializable> {
}
