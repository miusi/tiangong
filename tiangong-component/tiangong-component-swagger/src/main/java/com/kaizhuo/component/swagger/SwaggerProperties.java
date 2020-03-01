package com.kaizhuo.component.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.swagger
 * @description: swagger2属性配置
 * @author: miaochen
 * @create: 2020-02-25 08:02
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Data
@ConfigurationProperties(SwaggerConfiguration.preKey)
public class SwaggerProperties {
    /**
     * 是否开启swagger
     */
    private boolean enabled;

    /**
     * 标题
     */
    private String title = "";

    /**
     * 版本
     */
    private String version = "";
    /*** 描述*/
    private String description = "";

    /*** 许可证 */
    private String license = "";
    /**
     * 许可证
     */
    private String licenseUrl = "";
    /*** 服务条款URL */
    private String termsOfServiceUrl = "";
    /*** host信息 */
    private String host = "";
    /*** swagger会解析的包路径 */
    private String basePackage = "";
    /*** swagger会解析的url规则 */
    private List<String> basePath = new ArrayList<>();

    /*** 在basePath基础上需要排除的url规则 */
    private List<String> excludePath = new ArrayList<>();
    /*** 分组文档 */
    private Map<String, DocketInfo> docket = new LinkedHashMap<>();
    /*** 全局参数配置 */
    private List<GlobalOperationParameter> globalOperationParameters;

    @Data
    public static class GlobalOperationParameter {
        /*** 参数名 */
        private String name;
        /*** 描述信息 */
        private String description;
        /*** 指定参数类型 */
        private String modelRef;
        /*** 参数放在哪个地方:header,query,path,body.form */
        private String parameterType;
        /*** 参数是否必须传*/
        private String required;
    }

    @Data
    public static class DocketInfo {
        /*** 标题 */
        private String title = "";
        /*** 描述 */
        private String description = "";
        /*** 版本 */
        private String version = "";
        /*** 许可证 */
        private String license = "";
        /*** 许可证URL */
        private String licenseUrl = "";
        /*** 服务条款URL */
        private String termsOfServiceUrl = "";
        /*** swagger会解析的包路径 */
        private String basePackage = "";
        /*** swagger会解析的url规则 */
        private List<String> basePath = new ArrayList<>();
        /*** 在basePath基础上需要排除的url规则 */
        private List<String> excludePath = new ArrayList<>();
        private Contact contact = new Contact();
        private List<GlobalOperationParameter> globalOperationParameters;
    }

    private Contact contact = new Contact();

    @Data
    public static class Contact {

        /*** 联系人*/
        private String name = "";
        /*** 联系人url */
        private String url = "";
        /*** 联系人email */
        private String email = "";
    }
}
