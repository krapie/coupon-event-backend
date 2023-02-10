package com.krapi.coupon.interfaces.coupon;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Optional;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build()
                .genericModelSubstitutes(Optional.class, Flux.class, Mono.class);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("쿠폰 발급 API")
                .description("<h3>쿠폰 발급 API 명세</h3>Coupon Controller와 Model 토글을 클릭하시면 API 목록을 확인하실 수 있습니다.<br>")
                .contact(new Contact("박지환", "https://github.com/krapi0314", "wlghks0314@naver.com"))
                .license("No License")
                .licenseUrl("https://github.com/krapi0314")
                .version("0.1").build();
    }
}
