package com.kaizhuo.core.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: miaochen
 * \* @Date: 2020/2/25
 * \* @Time: 14:51
 * \* To change this template use File | Settings | File Templates.
 * \* @Description:
 * \
 */
@Data
@ApiModel("查询参数")
public class QueryRequest implements Serializable {
    private static final long serialVersionUID = 6110537432021841029L;

    @ApiModelProperty("当前页码")
    private int currentPage;

    @ApiModelProperty("分页")
    private int pageSize;

    public PageModel toPage() {
        PageModel pageModel = new PageModel(currentPage, pageSize);
        return pageModel;
    }

    public PageRequest toPageRequest(){
        PageModel pageModel = new PageModel(currentPage, pageSize);
        return pageModel.toPageRequest();
    }

    public PageRequest toPageRequest(Sort sort){
        PageModel pageModel = new PageModel(currentPage, pageSize);
        return pageModel.toPageRequest(sort);
    }
}
