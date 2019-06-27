package com.paulocezar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.paulocezar.config.FileStorageConfig;

@EnableConfigurationProperties({FileStorageConfig.class})
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class Startup {

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
		
		/* Define a senha encriptada
		 * BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder(16);
		 * String result = bCryptPasswordEncoder.encode("admin123");
		 * System.out.println("My Hash"+ result);
		 */
	}
}
