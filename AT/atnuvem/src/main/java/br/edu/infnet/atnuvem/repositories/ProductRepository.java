package br.edu.infnet.atnuvem.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.atnuvem.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, CustomProductRepository  {
	Optional<Product> findByName(String name);
	List<Product> findByCategory(String category);
}
