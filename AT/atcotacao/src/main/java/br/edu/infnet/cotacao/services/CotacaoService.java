package br.edu.infnet.cotacao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.cotacao.dtos.CotacaoDto;
import br.edu.infnet.cotacao.models.Cotacao;
import br.edu.infnet.cotacao.repository.CotacaoRepository;

@Service
public class CotacaoService {

	@Autowired
	CotacaoRepository cotacaoRepository;
	
	@Autowired
	ObterProdutoService obterProdutoService;
	
	public List<Cotacao> buscarTodasCotacoes()
	{
		return cotacaoRepository.findAll();
	}
	
	public Optional<Cotacao> buscarCotacaoPorId(long id)
	{
		return cotacaoRepository.findById(id);
	}
	
	public List<Cotacao> buscarCotacaoPorNomeProduto(String nomeProduto)
	{		
		return cotacaoRepository.buscarCotacoesPorProduto(nomeProduto);
	}
	
	public void deletarCotacao(long id)
	{
		cotacaoRepository.deleteById(id);
	}
	
	public void cadastrarCotacao(CotacaoDto cotacaoDto)
	{
		if(cotacaoDto != null)
		{
			Cotacao cotacao = new Cotacao();			
			
			cotacao.setFornecedor(cotacaoDto.getFornecedor());	
			cotacao.setData(cotacaoDto.getData());
			cotacao.setPreco(cotacaoDto.getPreco());
			cotacao.setProduto(obterProdutoService.getProductByName(cotacaoDto.getProduto().getName()));
			cotacaoRepository.save(cotacao);			
			
		}
	}
	
	public void atualizarCotacao(CotacaoDto cotacaoDto, long id)
	{
		if(cotacaoDto != null)
		{
			Cotacao cotacao = cotacaoRepository.findById(id).get();			
			
			cotacao.setFornecedor(cotacaoDto.getFornecedor());	
			cotacao.setData(cotacaoDto.getData());
			cotacao.setPreco(cotacaoDto.getPreco());
			cotacao.setProduto(obterProdutoService.getProductByName(cotacaoDto.getProduto().getName()));
			cotacaoRepository.save(cotacao);			
			
		}
	}
	
}
