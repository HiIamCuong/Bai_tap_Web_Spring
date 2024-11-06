package springthymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import springthymeleaf.configs.MySiteMeshFilter;

@SpringBootApplication
public class SpringthymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringthymeleafApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean<MySiteMeshFilter> siteMeshFilter() {
	   FilterRegistrationBean<MySiteMeshFilter> filterRegistrationBean=new FilterRegistrationBean<MySiteMeshFilter>();
	   filterRegistrationBean.setFilter(new MySiteMeshFilter());
	   filterRegistrationBean.addUrlPatterns("/*");
	   return filterRegistrationBean;
	}
}
