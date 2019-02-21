package net.mjlinc.trading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MjlTradingApi {

	public static void main(String[] args) {
		SpringApplication.run(MjlTradingApi.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate rt = new RestTemplate();
		return rt;
	}

	@Bean
	public WebMvcConfigurer defaultCors() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}
}
