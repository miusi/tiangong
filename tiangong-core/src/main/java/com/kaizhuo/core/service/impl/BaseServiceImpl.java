package com.kaizhuo.core.service.impl;

import com.kaizhuo.core.model.domain.BaseDomain;
import com.kaizhuo.core.model.domain.enums.YnEnums;
import com.kaizhuo.core.model.dto.PageModel;
import com.kaizhuo.core.service.BaseService;
import com.kaizhuo.data.core.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Optional;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: miaochen
 * \* @Date: 2020/2/25
 * \* @Time: 15:03
 * \* To change this template use File | Settings | File Templates.
 * \* @Description:
 * \
 */
public abstract class BaseServiceImpl<D extends BaseRepository, T extends BaseDomain> implements BaseService<T> {
    @Autowired
    protected D baseDao;

    /**
     * 默认逻辑删除
     */
    private final boolean physical = false;

    @Override
    public T save(T t) {
        return (T) baseDao.save(t);
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> ts) {
        return baseDao.saveAll(ts);
    }

    @Override
    public T findById(Object id) {
        T t = null;
        Optional<T> optional = baseDao.findById(id);
        if (optional.isPresent()) {
            t = optional.get();
        }
        return t;
    }

    @Override
    public Iterable<T> findAll() {
        return baseDao.findAll();
    }

    @Override
    public Iterable<T> findAllById(Iterable<Serializable> ids) {
        return baseDao.findAllById(ids);
    }

    @Override
    public PageModel<T> findAll(Pageable pageable) {
        return new PageModel<T>(baseDao.findAll(pageable));
    }

    @Override
    public void delete(Serializable id) {
        this.delete(id, physical);
    }

    @Override
    public void delete(T t) {
        this.delete(t, physical);
    }

    @Override
    public void delete(Iterable<? extends T> ts) {
        this.delete(ts, physical);
    }

    @Override
    public void delete(Serializable id, boolean physical) {
        if (physical) {
            baseDao.delete(id);
        } else {
            this.logicDelete(id);
        }
    }

    @Override
    public void delete(T t, boolean physical) {
        if (physical) {
            baseDao.delete(t);
        } else {
            this.logicDelete(t);
        }
    }

    @Override
    public void delete(Iterable<? extends T> ts, boolean physical) {
        if (physical) {
            baseDao.delete(ts);
        } else {
            this.logicDelete(ts);
        }
    }

    private void logicDelete(Serializable id) {
        logicDelete(this.findById(id));
    }

    private void logicDelete(T t) {
        t.setYn(YnEnums.N.getKey());
        baseDao.save(t);
    }

    private void logicDelete(Iterable<? extends T> ts) {
        ts.forEach(t -> t.setYn(YnEnums.N.getKey()));
        baseDao.save(ts);
    }
}
