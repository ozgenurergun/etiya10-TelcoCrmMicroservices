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

//Config Server, tüm mikroservislerin (Customer, Search, Gateway, Eureka) konfigürasyon dosyalarını (veritabanı şifreleri,
// üçüncü parti servislerin API anahtarları, log seviyeleri, portlar vb.) tek bir merkezi yerden yönetmeyi sağlar. Genellikle
// bu konfigürasyonları bir Git deposunda tutar.

//Best Practice: Eğer bir veritabanı şifresi değişirse, çalışan 50 servisi durdurup yeniden başlatmak yerine, sadece Config
// Server'daki değişikliği yayınlayarak sistemi kesintisiz güncelleyebilirsiniz.

//Mikroservis mimarisinde, Config Server ve Eureka Server genellikle önce ayağa kalkmalıdır ki, diğer servisler (Customer, Gateway)
// başladığında konfigürasyonlarını çekebilsin ve kendilerini kaydedebilsin.