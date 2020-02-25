package com.kaizhuo.core.model.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: miaochen
 * \* @Date: 2020/2/25
 * \* @Time: 14:33
 * \* To change this template use File | Settings | File Templates.
 * \* @Description:
 * \
 */
@Data
public class PageModel<T> implements Serializable {

    private static final long serialVersionUID = -5830898974321183191L;

    /**
     * 当前页码
     */
    private int currentPage;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 总记录数
     */
    private long totalCount;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 列表数据
     */
    private List<T> list;

    public PageModel() {
    }

    /**
     * 分页
     *
     * @param currentPage 当前页数
     */
    public PageModel(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 分页
     *
     * @param currentPage 当前页数
     * @param pageSize    每页记录数
     */
    public PageModel(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public PageModel(List<T> list, int pageSize, long totalCount, int currentPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }

    /**
     * 分页
     *
     * @param page
     */
    public PageModel(Page<T> page) {
        this.list = page.getContent();
        this.totalCount = page.getTotalElements();
        this.pageSize = page.getSize();
        this.currentPage = page.getNumber() + 1;
        this.totalPage = page.getTotalPages();
    }

    /**
     * pageModel 转pageRequest
     * @return
     */
    public PageRequest toPageRequest() {
        currentPage = currentPage <= 1 ? 1 : currentPage;
        pageSize = pageSize <= 0 ? 10 : pageSize;
        return PageRequest.of(currentPage - 1, pageSize);
    }

    /**
     * pageUtil 转 pageRequest
     *
     * @param sort 排序方式
     * @return pageRequest
     */
    public PageRequest toPageRequest(Sort sort) {
        currentPage = currentPage <= 1 ? 1 : currentPage;
        pageSize = pageSize <= 0 ? 10 : pageSize;
        return PageRequest.of(currentPage - 1, pageSize, sort);
    }
}
