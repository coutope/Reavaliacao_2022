package br.edu.infnet.cotacao.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.opencsv.CSVWriter;

import br.edu.infnet.cotacao.models.Cotacao;

public class ExportarCsvService {

	public static void exportar(PrintWriter responseWriter, List<Cotacao> cotacoes) throws IOException
	{
		CSVWriter writer = new CSVWriter(responseWriter);
	    for (Cotacao cotacao : cotacoes) {
	    	String[] linha = converterLinha(cotacao);
	        writer.writeNext(linha);
	    }
	    
	    writer.close();
	}
	
	private static String[] converterLinha(Cotacao cotacao)
	{
		String nome = cotacao.getProduto().getName();
		String marca = cotacao.getProduto().getBrand();
		String fornecedor = cotacao.getFornecedor();
		String preco = cotacao.getPreco().toString();
		String data = cotacao.getData();
		
		String[] linha = { data, nome, marca, fornecedor, preco };
		
		return linha;
	}
}
