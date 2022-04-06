package br.edu.infnet.atnuvem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.atnuvem.dtos.ProductDto;
import br.edu.infnet.atnuvem.models.Product;
import br.edu.infnet.atnuvem.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private AmazonClient amazonClient;
	
	@Autowired
	private ProductRepository repository;
	
	public void createProduct(ProductDto productDto)
	{
		String url = amazonClient.uploadFile(productDto.getPhoto());
		
		Product product = new Product();
		product.setName(productDto.getName());
		product.setCategory(productDto.getCategory());
		product.setBrand(productDto.getBrand());
		product.setPhoto(url);		
		
		repository.save(product);
	}
	
	public List<Product> getAllProducts()
	{
		return repository.findAll();
	}
	
	public List<String> getDistinctProductsName()
	{
		return repository.findDistinctProductsName();
	}
	
	public Optional<Product> getProductById(long id)
	{
		return repository.findById(id);
	}
	
	public Optional<Product> getProductByName(String name)
	{
		return repository.findByName(name);
	}
	
	public List<Product> getProductByCategory(String category)
	{
		return repository.findByCategory(category);
	}
	
	public void updateProduct(ProductDto productDto)
	{
		Product product = getProductById(productDto.getId()).get();
		
		amazonClient.deleteFileFromS3Bucket(product.getPhoto());
		
		String newUrl = amazonClient.uploadFile(productDto.getPhoto());
		
		product.setName(productDto.getName());
		product.setCategory(productDto.getCategory());
		product.setBrand(productDto.getBrand());
		product.setPhoto(newUrl);		
		
		repository.save(product);
	}
	
	public void deleteProduct(long id)
	{
		Optional<Product> product = repository.findById(id);
		
		if(product.isPresent())
		{
			amazonClient.deleteFileFromS3Bucket(product.get().getPhoto());
			
			repository.deleteById(id);
		}		
		
	}
}
