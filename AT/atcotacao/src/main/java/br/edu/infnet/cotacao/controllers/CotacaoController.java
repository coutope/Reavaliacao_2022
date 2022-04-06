package br.edu.infnet.cotacao.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.infnet.cotacao.dtos.CotacaoDto;
import br.edu.infnet.cotacao.dtos.InputDto;
import br.edu.infnet.cotacao.models.Cotacao;
import br.edu.infnet.cotacao.services.CotacaoService;
import br.edu.infnet.cotacao.services.ExportarCsvService;
import br.edu.infnet.cotacao.services.ObterProdutoService;

@Controller
public class CotacaoController {

	@Autowired
	CotacaoService cotacaoService;
	
	@Autowired
	ObterProdutoService obterProdutoService;
	
	@GetMapping("/")
	public String index(Model model)
	{
		InputDto inputDto = new InputDto();
		
		model.addAttribute("inputDto",inputDto);
		return "index";
	}	
	
	@GetMapping("/cotacao/detalhe")
	public String BuscarCotacaoPorNomeProduto(@ModelAttribute("inputDto")InputDto inputDto, Model model, BindingResult result)
	{
		List<Cotacao> cotacoes = cotacaoService.buscarCotacaoPorNomeProduto(inputDto.getNomeProduto());
		
		model.addAttribute("cotacoes", cotacoes);
		return "cotacaoDetalhe";
	}	
	
	
	@GetMapping("/cotacao/cadastrar")
	public String cadastrar(Model model)
	{		
		List<String> nomesProdutos = obterProdutoService.getDistinctProducts();
		
		model.addAttribute("nomes", nomesProdutos);
		model.addAttribute("cotacao",new CotacaoDto());
		return "cadastrarCotacao";
	}
	
	@GetMapping("/cotacao/atualizar")
	public String atualizar(@RequestParam(value = "id", required = false) Long id, Model model)
	{
		CotacaoDto cotacaoData = new CotacaoDto();
		Optional<Cotacao> cotacao;
		
		cotacao = cotacaoService.buscarCotacaoPorId(id);		
		
		model.addAttribute("cotacaoData", cotacaoData);
		model.addAttribute("cotacao", cotacao.isPresent() ? cotacao.get() : null);
		return "atualizarCotacao";
	}
	
	
	@PostMapping("/cotacao")
	public String cadastrarCotacao(@ModelAttribute("cotacao")CotacaoDto cotacaoDto, BindingResult bindingResult, Model model)
	{		
		cotacaoService.cadastrarCotacao(cotacaoDto);
		
		InputDto inputDto = new InputDto();
		
		model.addAttribute("inputDto",inputDto);
		return "index";
	}	

	
	@GetMapping("/cotacao/deletar")
	public String deletarCotacao(@RequestParam(value = "id", required = true) long id, Model model)
	{
		InputDto inputDto = new InputDto();
		
		cotacaoService.deletarCotacao(id);		
		
		model.addAttribute("inputDto",inputDto);
		return "index";
	}
	
	@PostMapping("/cotacao/atualizar")
	public String atualizarCotacao(@ModelAttribute("cotacao")CotacaoDto cotacao, BindingResult bindingResult, @RequestParam(value = "id", required = true) long id, Model model)
	{
		cotacao.setId(id);
		cotacaoService.atualizarCotacao(cotacao, id);
		
		InputDto inputDto = new InputDto();
		
		model.addAttribute("inputDto",inputDto);
		return "index";
	}
	
	@GetMapping(value = "/cotacao/exportar", produces = "text/csv")
	public void exportarCotacao(@RequestParam(value = "nome", required = true) String nome, Model model, HttpServletResponse response) throws IOException
	{		
		String csvFileName = "Cotacao_"+ nome + ".csv";
		String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader(headerKey, headerValue);
        
        List<Cotacao> cotacoes = cotacaoService.buscarCotacaoPorNomeProduto(nome);	
		
		ExportarCsvService.exportar(response.getWriter(), cotacoes);
	}
	
	
}
