package testspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import testspring.configs.MySiteMeshFilter;

@SpringBootApplication
public class TestspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestspringApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean<MySiteMeshFilter> siteMeshFilter() {
	   FilterRegistrationBean<MySiteMeshFilter> filterRegistrationBean=new FilterRegistrationBean<MySiteMeshFilter>();
	   filterRegistrationBean.setFilter(new MySiteMeshFilter());
	   filterRegistrationBean.addUrlPatterns("/*");
	   return filterRegistrationBean;
	}
}
