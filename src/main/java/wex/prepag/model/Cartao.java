package wex.prepag.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.logging.log4j.util.Strings;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cartao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String numero;
	
	@Transient
	private String cvv;
	
	@Column(nullable = false)
	@JsonFormat(pattern = "MM/yy")
	private Date validade;
	
	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private Double saldo;
	
	public Cartao() {
		super();
	}

	public Cartao(String nome, String numero, Date validade, String senha, Double saldo) {
		super();
		this.nome = nome;
		this.numero = numero;
		this.validade = validade;
		this.senha = senha;
		this.saldo = saldo;
		this.cvv = obterCvv();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getCvv() {
		return cvv;
	}
	
	/**
	 * Implementação de código de verificação
	 * Digito 1 é calculado apartir da redução da soma de todos os digitos do numero do cartão (Sem BIN) Multiplicados pela sua posição cartão
	 * Digito 2 é calculado apartir da redução da soma de todos os digitos do numero do cartão (Sem BIN) Multiplicados pelo mes de validade
	 * Digito 3 é calculado apartir da redução da soma de todos os digitos do numero do cartão (Sem BIN) Multiplicados pelo ano de validade
	 * @return
	 */
	private String obterCvv() {
		StringBuilder sb = new StringBuilder();
		Calendar cal = Calendar.getInstance();
		cal.setTime(getValidade());
		int mes = cal.get(Calendar.MONTH);
		int ano = cal.get(Calendar.YEAR);
		
		List<String> numSemBin = Arrays.asList((getNumero().substring(6)).split(Strings.EMPTY));
		
		Double sDigito01 = 0D;
		Double sDigito02 = 0D;
		Double sDigito03 = 0D;
		
		for (String digit : numSemBin) {
			sDigito01 += Double.valueOf(digit) * numSemBin.indexOf(digit);
			sDigito02 += Double.valueOf(digit) * mes;
			sDigito03 += Double.valueOf(digit) * ano;
		}
		
		sb.append(obterDigito(sDigito01)).append(obterDigito(sDigito02)).append(obterDigito(sDigito03));
		
		return sb.toString();
	}
	
	/**
	 * Redução de double a digito unico.
	 * @param digito
	 * @return
	 */
	private int obterDigito(Double digito) {
		return (int) (11 - (digito % 11) < 10 ? 11 - (digito % 11) : (11 - (digito % 11)) - 10);
	}
	
	@JsonIgnore
	public boolean isValidoCvv() {
		return getCvv() != null && getCvv().equals(obterCvv());
	}
	
	@JsonIgnore
	public boolean isValido() {
		return getValidade() != null && getValidade().after(new Date());
	}
}
