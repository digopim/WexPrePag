package wex.prepag.model.dto;

import java.io.Serializable;

import wex.prepag.model.Cartao;

public class Venda implements Serializable{
	private static final long serialVersionUID = -7045465494992231689L;
	
	private Cartao cartao;
	private String estabelecimento;
	private Double valor;
	
	public Cartao getCartao() {
		return cartao;
	}
	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	public String getEstabelecimento() {
		return estabelecimento;
	}
	public void setEstabelecimento(String estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
}
