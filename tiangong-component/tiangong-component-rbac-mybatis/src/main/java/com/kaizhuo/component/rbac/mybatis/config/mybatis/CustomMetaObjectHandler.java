package com.kaizhuo.component.rbac.mybatis.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.kaizhuo.core.security.TiangongSecurityUser;
import com.kaizhuo.core.security.TiangongSecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.mybatis.config.mybatis
 * @description: 填充器
 * @author: godric
 * @create: 2020/3/8 0008
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
@Slf4j
@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        try {
            this.setFieldValByName("createBy", TiangongSecurityUtil.getUsername(), metaObject);
            this.setFieldValByName("createDate", LocalDateTime.now(), metaObject);
            this.setFieldValByName("updateBy", TiangongSecurityUtil.getUsername(), metaObject);
            this.setFieldValByName("updateDate", LocalDateTime.now(), metaObject);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        try {
            this.setFieldValByName("updateBy",  TiangongSecurityUtil.getUsername(), metaObject);
            this.setFieldValByName("updateDate", LocalDateTime.now(), metaObject);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
