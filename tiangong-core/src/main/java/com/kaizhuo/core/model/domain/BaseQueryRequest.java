package com.kaizhuo.core.model.domain;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @program: tiangong
 * @package: com.kaizhuo.core.model.domain
 * @description:
 * @author: godric
 * @create: 2020/3/7 0007
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
@Data
public class BaseQueryRequest {
    private int current = 1;
    private int pageSize = 20;

    public <T> Page<T> getPage() {
        return new Page<>(current, pageSize);
    }
}
