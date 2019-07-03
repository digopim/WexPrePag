package wex.prepag.model.dto;

public enum Codigo {
	S1("00","Requisição concluida com Sucesso."),
	S2("01","Cartão inexistente."),
	S3("02","Catão Invalido."),
	S4("03","Codigo de segurança Invalido."),
	S5("04","Senha Incorreta."),
	S6("05","Saldo insuficiente.");
	
	private final String valor;
	private final String descricao;
	
	private Codigo(String valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
