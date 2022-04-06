package br.edu.infnet.cotacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.cotacao.models.Cotacao;

@Repository
public interface CotacaoRepository extends JpaRepository<Cotacao, Long>, CustomCotacaoRepository {
	
}
