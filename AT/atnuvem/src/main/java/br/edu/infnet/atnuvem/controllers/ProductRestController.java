package br.edu.infnet.atnuvem.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.atnuvem.models.Product;
import br.edu.infnet.atnuvem.services.ProductService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductRestController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/product/name/{name}")
	public Product getProductByName(@PathVariable String name)
	{
		return productService.getProductByName(name).get();
	}
	
	@GetMapping("/products")
	public List<String> getDistinctProducts()
	{
		return productService.getDistinctProductsName();
	}
	
	@GetMapping("/product/{id}")
	public Product getProduct(@PathVariable long id)
	{
		Optional<Product> product = productService.getProductById(id);
		
		return product.isPresent() ? product.get() : null;
	}
	
}
