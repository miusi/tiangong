package com.kaizhuo.component.oss.config;

import com.kaizhuo.component.oss.config.storage.Aliyun;
import com.kaizhuo.component.oss.config.storage.Qiniu;
import com.kaizhuo.component.oss.config.storage.Tencent;
import com.kaizhuo.component.oss.config.storage.Upyun;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.oss.config
 * @description: oss config配置
 * @author: miaochen
 * @create: 2020-02-25 21:30
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Configuration
@Data
@ConfigurationProperties("tiangong.oss")
public class OssProperties {

    /**
     * 是否开启OSS
     */
    private boolean enabled;

    /**
     * 存储方式
     */
    private String storage;

    /**
     * 阿里云
     */
    private Aliyun aliyun;


    /*** 七牛云 */
    private Qiniu qiniu;

    /*** 腾讯云 */
    private Tencent tencent;

    /*** 又拍云 */
    private Upyun upyun;
}
