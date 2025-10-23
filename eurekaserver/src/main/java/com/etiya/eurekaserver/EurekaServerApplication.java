package com.etiya.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // discovery servisin çalışması için bu anotasyonu ekliyoruz
//Bu anotasyon, Spring Boot uygulamasını bir Eureka Sunucusu olarak yapılandırır. Yani bu uygulama artık diğer servislerin
// kayıt olacağı ve sorgu yapacağı merkezi kayıt defteri olarak görev yapacaktır.
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}

//Mikroservisler konteynerler (Docker/Podman) içinde çalıştığında, IP adresleri ve portları sürekli değişebilir.
// Bir servis diğerine ulaşmak istediğinde (Örn: apigateway'in customerservice'e ulaşması), onun anlık adresini bilmesi gerekir.
// Eureka Server işte bu adres defteri görevini görür.