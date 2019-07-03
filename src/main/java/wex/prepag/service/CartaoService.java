package wex.prepag.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wex.prepag.model.Cartao;
import wex.prepag.model.dto.Codigo;
import wex.prepag.model.dto.Transacao;
import wex.prepag.model.dto.Venda;
import wex.prepag.repositorio.CartaoRepository;

@Service
public class CartaoService {
	
	private static final String BIN = "510922";
	private Random random = new Random();
	
	@Autowired
	CartaoRepository repository;
	
	public List<Cartao> findAll(){
		List<Cartao> cartoes = new ArrayList<Cartao>();
		repository.findAll().forEach(cartoes::add);
		return cartoes;
	}
	
	/**
	 * Metedo de emissão de novo cartão
	 * @param nome
	 * @param saldo
	 * @return
	 */
	public Cartao emitirCartao(String nome, Double saldo) {
		List<Codigo> codigos = new ArrayList<Codigo>();
		Cartao novo  = new Cartao(nome, gerarNumeroCartao(), obterValidade(), gerarSenhaInicial(), saldo);
		repository.save(novo);
		codigos.add(Codigo.S1);
		return novo;
	}
	
	public Transacao autorizarVenda(Venda venda) {
		List<Codigo> erros = new ArrayList<Codigo>();
		Double saldo = 0D;
		if(venda != null && venda.getCartao() != null) {
			Cartao cartao = repository.findByNumero(venda.getCartao().getNumero());
			if(cartao != null) {
				if(cartao.isValido()) {
					if(cartao.isValidoCvv()) {
						if(cartao.getSenha().equals(venda.getCartao().getSenha())) {
							if(venda.getValor() != null && (cartao.getSaldo() - venda.getValor() >= 0D)) {
									Double antigoSaldo = cartao.getSaldo();
									cartao.setSaldo(antigoSaldo - venda.getValor());
									repository.save(cartao);
									erros.add(Codigo.S1);
							} else {
								erros.add(Codigo.S6);
							}
						} else {
							erros.add(Codigo.S5);			
						}
					} else {
						erros.add(Codigo.S4);		
					}
				} else {
					erros.add(Codigo.S3);	
				}
			} else {
				erros.add(Codigo.S2);	
			}
		} else {
			erros.add(Codigo.S2);
		}
		
		return new Transacao(erros, saldo);
	}
	
	/**
	 * Metodo de geração de validade
	 * @return
	 */
	private Date obterValidade() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 2);
		return cal.getTime();
	}
	
	private String gerarNumeroCartao() {
		StringBuilder sb = new StringBuilder(BIN);
		for (int pos = 0; pos < 10; pos++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
	
	private String gerarSenhaInicial() {
		StringBuilder sb = new StringBuilder();
		for (int pos = 0; pos < 4; pos++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
	
}
