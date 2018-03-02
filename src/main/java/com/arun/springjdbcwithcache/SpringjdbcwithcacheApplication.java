package com.arun.springjdbcwithcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringjdbcwithcacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringjdbcwithcacheApplication.class, args);
	}
}
