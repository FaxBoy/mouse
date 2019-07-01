package com.mouse.springbootshiro.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: SwaggerConfig.java
 * @Description: TODO(Swagger配置类)
 * @author: liuyx
 * @date: 2019/3/8 11:27
 * @Copyright:  www.wavenet.com.cn. All rights reserved.
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "wavenet", name = "swagger-open", havingValue = "true")
public class SwaggerConfig {

    /**
     * @Title: createRestApi
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param: []
     * @return: springfox.documentation.spring.web.plugins.Docket
     * @throws
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //这里采用包含注解的方式来确定要显示的接口
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //.apis(RequestHandlerSelectors.basePackage("com.mouse.springbootshiro.controller"))
                .paths(PathSelectors.any())
                .build();

    }


    /**
     * @Title: apiInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param: []
     * @return: springfox.documentation.service.ApiInfo
     * @throws
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger-api文档")
                .description("swagger接入教程")
                //服务条款网址
                .termsOfServiceUrl("http://xxxx.com")
                .version("1.0")
                .build();
    }

}
