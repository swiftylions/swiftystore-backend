package com.swiftylions.SLStore;

import com.swiftylions.SLStore.dto.ContactInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication //(exclude={DataSourceAutoConfiguration.class})
// @ComponentScan(basePackages = {"com.swiftylions.SLStore.controller"})
@EnableCaching
@EnableConfigurationProperties(value = {ContactInfoDto.class})
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class SlStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlStoreApplication.class, args);
	}

}
