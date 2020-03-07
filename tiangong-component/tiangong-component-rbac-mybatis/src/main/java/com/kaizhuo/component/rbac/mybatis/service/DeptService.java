package com.kaizhuo.component.rbac.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kaizhuo.component.rbac.mybatis.pojo.Dept;
import com.kaizhuo.component.rbac.mybatis.vo.TreeNode;

import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.mybatis.service
 * @description:
 * @author: godric
 * @create: 2020/3/7 0007
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
public interface DeptService extends IService<Dept> {
    List<TreeNode> selectAll();

    void deleteById(int id);
}
