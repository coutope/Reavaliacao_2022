package br.edu.infnet.tp3aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Tp3awsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tp3awsApplication.class, args);
	}

}
