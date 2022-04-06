package br.edu.infnet.atnuvem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.infnet.atnuvem.models.Product;

public interface CustomProductRepository extends  JpaRepository<Product, Long>  {	
	
	@Query(value = "SELECT Distinct p.name FROM product p", nativeQuery = true)
	List<String> findDistinctProductsName();
}
