package org.learnify.com.counsellor_portal_app;

import org.apache.catalina.mapper.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class configs {
    @Bean
    public Mapper getMapper() {
        return new Mapper();
    }
}
