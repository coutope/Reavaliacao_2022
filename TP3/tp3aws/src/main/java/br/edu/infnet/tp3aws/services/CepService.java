package br.edu.infnet.tp3aws.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.infnet.tp3aws.models.Endereco;

@FeignClient(url="https://viacep.com.br/ws/", name = "ViaCepService")
public interface CepService {

	@GetMapping("{cep}/json")
    Endereco buscaEnderecoPor(@PathVariable("cep") String cep);
}
