package com.backendchallange.backendchallange;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	  @Bean
	    public Docket customerApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	        		.groupName("CustomerApi")
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.backendchallange.backendchallange.controller"))
	                .paths(PathSelectors.ant("/customer/**"))
	                .build()
	                .useDefaultResponseMessages(false)
	                .apiInfo(apiInfo());
	    }

	    @Bean
	    public Docket orderApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	        		.groupName("OrderApi")
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.backendchallange.backendchallange.controller"))
	                .paths(PathSelectors.ant("/order/**"))
	                .build()
	                .useDefaultResponseMessages(false)
	                .apiInfo(apiInfo());
	    }
	    private ApiInfo apiInfo() {
	        return  new ApiInfoBuilder()
	                .title("Challange")
	                .description("BackendChallange")
	                .version("1.0")
	                .build();
	    }

}
