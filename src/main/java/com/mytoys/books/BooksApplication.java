package com.mytoys.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksApplication.class, args);
	}
	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.mytoys.books.api"))
                .paths(PathSelectors.ant("/**")).build().useDefaultResponseMessages(false).apiInfo(apiInfo());
    }

    @Bean
    public UiConfiguration uiConfig() {
        return UiConfiguration.DEFAULT;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Books-api")
                .description("Example of a books search API based on google official API").version("1.0")
                .build();
    }
}
