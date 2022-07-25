package com.management.member.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {
  "com.management.member.controller.api"
})
public class SwaggerConfig {

  /** swagger */
  @Bean
  public Docket ShopApi() {
    return new Docket(DocumentationType.SWAGGER_2)
      .groupName("Shop API")
      .select()
      .apis(RequestHandlerSelectors.basePackage("com.management.member.controller.api"))
      .paths(PathSelectors.any())
      .build()
      .apiInfo(this.memberApiInfo());

  }

  private ApiInfo memberApiInfo() {
    return new ApiInfoBuilder()
      .title("member management API")
      .description("회원 관리 API")
      .version("1.0")
      .build();
  }
}