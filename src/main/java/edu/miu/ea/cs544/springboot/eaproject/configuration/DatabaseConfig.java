package edu.miu.ea.cs544.springboot.eaproject.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
public class DatabaseConfig {

    @Profile("development")
    @Bean
    public String developmentConnectionStrings() {
        System.out.println("DB connection for 'DEVELOPMENT'");
        return "DEV Enviroment Connected!";
    }

    @Profile("production")
    @Bean
    public String productionConnectionStrings() {
        System.out.println("DB connection for 'PRODUCTION'");
        return "PROD Enviroment Connected!";
    }
}
