package com.etoak.config;

import com.etoak.common.core.properties.ImageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class WebFluxConfig implements WebFluxConfigurer {

    @Autowired
    ImageProperties imageProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String mapping = imageProperties.getMapping().endsWith("/")?
                imageProperties.getMapping() + "**" :
                imageProperties.getMapping() + "/**";

        String location = imageProperties.getLocation().endsWith("/")?
                "file:" + imageProperties.getLocation() :
                "file:" + imageProperties.getLocation() + "/";
        registry.addResourceHandler(mapping).addResourceLocations(location);

    }
}
