package org.age.mag.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) { // ugly way, good for static htmls (imo: not for us)
        registry.addViewController("/").setViewName("home"); 
        registry.addViewController("/hello").setViewName("hello");
    }

}