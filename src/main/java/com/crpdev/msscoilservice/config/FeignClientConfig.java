package com.crpdev.msscoilservice.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by rajapandian
 * Date: 18/09/20
 * Project: mssc-oil-eureka
 * Package: com.crpdev.msscoilservice.config
 **/
@Configuration
public class FeignClientConfig {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(@Value("${crp.factory.inventory-user}") String inventoryUser,
                                                                   @Value("${crp.factory.inventory-password}")String inventoryPassword){
        return new BasicAuthRequestInterceptor(inventoryUser, inventoryPassword);
    }
}
