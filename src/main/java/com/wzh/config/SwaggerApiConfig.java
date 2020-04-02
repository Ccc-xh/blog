package com.wzh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author chenxh
 * @date 2020/3/13 14:43
 * @Description:
 * @modify:
 * @modifyDate:
 * @Description:
 */
//说明该类为配置类，即把该类作为spring的xml配置文件中的bean
@Configuration
//启动swagger
@EnableSwagger2
//自动扫描，该注解会扫描指定路径下的所有的配置，默认扫描该类所在包下面的所有配置文件
//@ComponentScan("com.hand.springboot")
public class SwaggerApiConfig {

    /*
     * 创建API应用
     * apiInfo() 增加API相关信息
     * select() 选择哪些路径和api会生成document
     * apis() 对所有api进行监控
     * paths() 对所有路径进行监控
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wzh.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }
    private List<ApiKey> securitySchemes() {
        return newArrayList(
                new ApiKey("Authorization", "Authorization", "header"));
    }

    private List<SecurityContext> securityContexts() {
        return newArrayList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build()
        );
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(
                new SecurityReference("Authorization", authorizationScopes));
    }
    /*
     * 创建该API的基本信息
     * title:访问界面的标题
     * description：描述
     * version：版本号
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Ccc博客API")
                .description("基于springboot+vue的博客网站")
                .version("1.0")
                .license("version1.0")
                .build();
    }
}
