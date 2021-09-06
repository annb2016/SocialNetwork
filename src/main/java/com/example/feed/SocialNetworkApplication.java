package com.example.feed;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ComponentScan(basePackages= {"com.example.feed"})
@SpringBootApplication
@EnableSwagger2
public class SocialNetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialNetworkApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/api/**"))
				.apis(RequestHandlerSelectors.basePackage("com.example.feed"))
				.build()
				.apiInfo(ApplicationDetails());
	}

	private ApiInfo ApplicationDetails() {
		return new ApiInfo("Feed System", "An application to create posts, comments and likes", "1.0", "",
				new springfox.documentation.service.Contact("Annie Borah", "", ""), "", "", Collections.emptyList());
	}
}
