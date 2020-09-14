package com.crpdev.msscoilservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsscOilServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsscOilServiceApplication.class, args);
	}

}
