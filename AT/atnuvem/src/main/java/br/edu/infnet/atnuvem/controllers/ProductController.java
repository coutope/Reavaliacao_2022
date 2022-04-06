package br.edu.infnet.atnuvem.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.infnet.atnuvem.dtos.InputDto;
import br.edu.infnet.atnuvem.dtos.ProductDto;
import br.edu.infnet.atnuvem.models.Product;
import br.edu.infnet.atnuvem.services.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/")
	public String index(Model model)
	{
		InputDto inputDto = new InputDto();
		List<Product> products = productService.getAllProducts();
		
		model.addAttribute("products", products);
		model.addAttribute("inputDto",inputDto);
		return "index";
	}	
	
	@GetMapping("/product/detail")
	public String getProductByName(@ModelAttribute("inputDto")InputDto inputDto,@RequestParam(value = "id", required = false) Long id, Model model, BindingResult result)
	{
		Optional<Product> product;		
		
		if(inputDto == null || inputDto.getName() == null)
		{
			product = productService.getProductById(id);			
		}
		else
			product = productService.getProductByName(inputDto.getName());
		
		model.addAttribute("product", product.isPresent() ? product.get() : null);
		return "productDetail";
	}
	
	@GetMapping("/products/category")
	public String getProductsByCategory(@ModelAttribute("inputDto")InputDto inputDto, Model model, BindingResult result)
	{
		List<Product> products = productService.getProductByCategory(inputDto.getCategory());
		
		model.addAttribute("products", products);
		return "productsCategory";
	}
	
	@GetMapping("/product/update")
	public String update(@ModelAttribute("inputDto")InputDto inputDto,@RequestParam(value = "id", required = false) Long id, Model model, BindingResult result)
	{
		ProductDto productData = new ProductDto();
		Optional<Product> product;
		
		if(inputDto == null || inputDto.getName() == null)
			product = productService.getProductById(id);
		else
			product = productService.getProductByName(inputDto.getName());
		
		model.addAttribute("productData", productData);
		model.addAttribute("product", product.isPresent() ? product.get() : null);
		return "updateProduct";
	}
	
	@GetMapping("/product/create")
	public String create(Model model)
	{		
		model.addAttribute("product",new ProductDto());
		return "registerProduct";
	}
	
	
	@PostMapping("/product")
	public String createProduct(@ModelAttribute("product")ProductDto productDto, BindingResult bindingResult, Model model)
	{
		productService.createProduct(productDto);
		
		InputDto inputDto = new InputDto();
		List<Product> products = productService.getAllProducts();
		
		model.addAttribute("products", products);
		model.addAttribute("inputDto",inputDto);
		return "index";
	}
	
	@PostMapping("/product/update")
	public String updateProduct(@ModelAttribute("product")ProductDto product, BindingResult bindingResult, @RequestParam(value = "id", required = true) long id, Model model)
	{
		product.setId(id);
		productService.updateProduct(product);
		
		InputDto inputDto = new InputDto();
		List<Product> products = productService.getAllProducts();
		
		model.addAttribute("products", products);
		model.addAttribute("inputDto",inputDto);
		return "index";
	}
	
	@GetMapping("/product/delete")
	public String deleteProduct(@RequestParam(value = "id", required = true) long id, Model model)
	{
		InputDto inputDto = new InputDto();
		
		productService.deleteProduct(id);
		
		List<Product> products = productService.getAllProducts();
		
		model.addAttribute("products", products);
		model.addAttribute("inputDto",inputDto);
		return "index";
	}
}
