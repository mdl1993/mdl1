package it.sincrono;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@SpringBootApplication
public class BadgeRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BadgeRestApplication.class, args);
	}
	
	/**
	 * Deploy as WAR
	 */
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		return application.sources(BadgeRestApplication.class);
	}

	@Bean
	public MessageSource messageSource() {

		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasename("classpath:application-resource");
	    messageSource.setCacheSeconds(10);

	    return messageSource;
	}

	@Bean
	public ViewResolver configureViewResolver() {

		InternalResourceViewResolver viewResolve = new InternalResourceViewResolver();
		viewResolve.setPrefix("/WEB-INF/pages/");
		viewResolve.setSuffix(".jsp");

		return viewResolve;
	}

}
