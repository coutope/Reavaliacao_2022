package br.edu.infnet.cotacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AtcotacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtcotacaoApplication.class, args);
	}

}
