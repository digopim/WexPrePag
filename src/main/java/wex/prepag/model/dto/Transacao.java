package wex.prepag.model.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.util.Strings;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Transacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	private List<Codigo> codigos;
	private Double saldo;
	
	public Transacao() {
		super();
	}
	
	public Transacao(List<Codigo> codigos, Double saldo) {
		super();
		this.codigos = codigos;
		this.saldo = saldo;
	}

	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public List<Codigo> getCodigos() {
		return codigos;
	}

	public void setCodigos(List<Codigo> codigos) {
		this.codigos = codigos;
	}

	public String getCodigo() {
		if(getCodigos() != null && !getCodigos().isEmpty()) {
			StringBuilder sb = new StringBuilder();
			getCodigos().stream().forEach(c -> sb.append(c.getValor()).append(" ( ").append(c.getDescricao()).append(" ), "));
			return sb.toString();
		}
		return Strings.EMPTY;
	}
	
	
}
