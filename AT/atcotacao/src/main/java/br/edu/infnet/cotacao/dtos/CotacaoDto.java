package br.edu.infnet.cotacao.dtos;

import java.math.BigDecimal;

import br.edu.infnet.cotacao.models.Product;

public class CotacaoDto {

	private long id;
	private String fornecedor;
	private String data;
	private BigDecimal preco;
	private Product produto;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public Product getProduto() {
		return produto;
	}
	public void setProduto(Product produto) {
		this.produto = produto;
	}
	
}
