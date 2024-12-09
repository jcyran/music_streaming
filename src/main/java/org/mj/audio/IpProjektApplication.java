package org.mj.audio;

import org.mj.audio.property.StorageProperties;
import org.mj.audio.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class IpProjektApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpProjektApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return args -> {
			storageService.init();
		};
	}
}
