package com.sharey.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    private static final String API_NAME = "Sharey API";
    private static final String APT_VERSION = "0.0.1";
    private static final String API_DESCRIPTION = "Sharey API 명세서";
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sharey"))
                .paths(PathSelectors.ant("/sharey/**"))  // 해당 패키지 하위에 있는 모든 url에 적용
                .build()
                .apiInfo(apiInfo());
    }

    public ApiInfo apiInfo(){ // api의 이름, 버전, 정보
        return new ApiInfoBuilder()
                .title(API_NAME)
                .version(APT_VERSION)
                .description(API_DESCRIPTION)
                .build();
    }

}
