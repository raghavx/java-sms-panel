package com.raghavx.config;

import org.apache.catalina.webresources.StandardRoot;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * The Class WebMVCConfig.
 */
@Configuration
@EnableWebMvc
public class WebMVCConfig extends WebMvcConfigurerAdapter {

	/** The classpath resources. */
	public final String[] CLASSPATH_RESOURCES = new String[] { "classpath:/static/" };

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		//registry.addViewController("/").setViewName("registration/step1");
		registry.addViewController("/home").setViewName("home");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/static/**/*.*").addResourceLocations(CLASSPATH_RESOURCES);
		
		registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	 	
	/**
	 * Kissgc api.
	 *
	 * @return the docket
	 */
	
	
	
	
	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatFactory() {
	    TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
	    tomcatFactory.addContextCustomizers((context) -> {
	        StandardRoot standardRoot = new StandardRoot(context);
	        standardRoot.setCacheMaxSize(40 * 1024);
	        standardRoot.setCachingAllowed(false);
	    });
	    return tomcatFactory;
	}

}
