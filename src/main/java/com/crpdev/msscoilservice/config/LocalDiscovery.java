package com.crpdev.msscoilservice.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by rajapandian
 * Date: 14/09/20
 * Project: mssc-oil-eureka
 * Package: com.crpdev.msscoilservice.config
 **/
@Profile("local-discovery")
@EnableDiscoveryClient
@Configuration
public class LocalDiscovery {
}
