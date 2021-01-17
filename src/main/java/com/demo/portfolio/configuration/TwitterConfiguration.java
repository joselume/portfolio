package com.demo.portfolio.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Configuration
@PropertySource("classpath:twitter.properties")
public class TwitterConfiguration {
    @Value("${twitter.api.key}")
    private String API_KEY;
    @Value("${twitter.api.private.key}")
    private String API_PRIVATE_KEY;
    @Value("${twitter.access.token}")
    private String ACCESS_TOKEN;
    @Value("${twitter.access.token.secret}")
    private String ACCESS_TOKEN_SECRET;

    @Bean
    TwitterTemplate getTwtTemplate(){
        return new TwitterTemplate(API_KEY, API_PRIVATE_KEY, ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
    }
}
