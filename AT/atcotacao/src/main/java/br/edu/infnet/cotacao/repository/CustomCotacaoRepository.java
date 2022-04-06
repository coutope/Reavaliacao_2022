package br.edu.infnet.cotacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.infnet.cotacao.models.Cotacao;

public interface CustomCotacaoRepository extends  JpaRepository<Cotacao, Long> {

	@Query(value = "SELECT * FROM cotacao c INNER JOIN product p ON c.produto_id = p.id WHERE p.name = ?#{#nomeProduto}", nativeQuery = true)
	List<Cotacao> buscarCotacoesPorProduto(@Param("nomeProduto")String nomeProduto);
}
