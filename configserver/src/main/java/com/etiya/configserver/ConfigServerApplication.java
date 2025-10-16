package com.etiya.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

//Tüm yapılar buraya bağlanacak. Artık ilk burası çalışacak.
@SpringBootApplication
@EnableConfigServer //config server'i aktifleştirmek için kullanılır.
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
