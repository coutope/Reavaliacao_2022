package br.edu.infnet.cotacao.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.infnet.cotacao.models.Product;

@FeignClient(url="http://localhost:8080/api/", name = "ProductService")
public interface ObterProdutoService {

	@GetMapping("product/name/{name}")
	Product getProductByName(@PathVariable("name") String name);
	
	@GetMapping("products")
	List<String> getDistinctProducts();
}
