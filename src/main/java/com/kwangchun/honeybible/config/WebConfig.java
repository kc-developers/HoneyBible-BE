package com.kwangchun.honeybible.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//  CORS Configuration
  @Override
  public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**")
              .allowCredentials(true)
              .allowedHeaders("*")
              .allowedOriginPatterns("*")
              .allowedMethods("GET", "POST", "PUT", "DELETE")
//              .allowedHeaders("JwtAuthorization", "Api-version")
              .maxAge(3600);
  }

}
