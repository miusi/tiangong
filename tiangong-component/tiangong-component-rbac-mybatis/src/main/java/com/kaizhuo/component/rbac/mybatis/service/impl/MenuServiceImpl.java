package com.kaizhuo.component.rbac.mybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kaizhuo.component.rbac.mybatis.pojo.Dept;
import com.kaizhuo.component.rbac.mybatis.pojo.Menu;
import com.kaizhuo.component.rbac.mybatis.service.MenuService;
import com.kaizhuo.component.rbac.mybatis.util.CommonUtil;
import com.kaizhuo.component.rbac.mybatis.vo.TreeNode;
import com.kaizhuo.core.exception.AppException;
import com.kaizhuo.core.model.domain.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.mybatis.service.impl
 * @description:
 * @author: godric
 * @create: 2020/3/8 0008
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
@Service
public class MenuServiceImpl extends BaseService<Menu> implements MenuService {
    @Override
    public List<TreeNode> selectAll() {
        List<Menu> deptList = super.list();
        //转化为TreeNodeList
        List<TreeNode> treeNodes = deptList.stream().map(item -> new TreeNode(item.getId(), item.getParentId(), item.getName(), item)).collect(Collectors.toList());
        //转化为树
        return  CommonUtil.listToTree(treeNodes);
    }

    @Override
    public void deleteById(Integer id) {
        int childrenCount = super.count(new LambdaQueryWrapper<Menu>().eq(Menu::getParentId, id));
        if (childrenCount > 0) {
            throw new AppException("该菜单有下级菜单，无法删除");
        }
        super.removeById(id);
    }
}
