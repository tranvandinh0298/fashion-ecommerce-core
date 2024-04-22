package com.source.dinhtv.fashionecommercecore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FashionEcommerceCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(FashionEcommerceCoreApplication.class, args);
	}

	@Configuration
	public class StaticResourceConfig implements WebMvcConfigurer {

		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/uploads/*")
					.addResourceLocations("classpath:/static/uploads/");
		}
	}

}
