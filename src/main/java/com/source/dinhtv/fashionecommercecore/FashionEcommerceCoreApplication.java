package com.source.dinhtv.fashionecommercecore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.CROSS_ORIGIN;

@SpringBootApplication
public class FashionEcommerceCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(FashionEcommerceCoreApplication.class, args);
	}

	@Configuration
	public class WebConfig implements WebMvcConfigurer {

		@Bean
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**")
							.allowedOrigins(CROSS_ORIGIN)  // Allow requests from Next.js development server
							.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
							.allowedHeaders("*")
							.allowCredentials(true);
				}
			};
		}
	}

}
