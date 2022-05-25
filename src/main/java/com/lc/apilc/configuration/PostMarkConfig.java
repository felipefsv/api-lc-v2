package com.lc.apilc.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class PostMarkConfig {

    @Value("${postmark.url}")
    private String postMarkUrl;

    @Value("${postmark.key}")
    private String postMarkKey;

}
