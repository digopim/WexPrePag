package wex.prepag.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import wex.prepag.model.Cartao;
import wex.prepag.model.dto.Transacao;
import wex.prepag.model.dto.Venda;
import wex.prepag.service.CartaoService;

@RestController
public class PrePag {
	
	@Autowired
	CartaoService service;
    
    @RequestMapping("/")
    public String index() {
        return "Ola Desafio WEX PrePaG! utilize a URL + /cartoes para acessar as funcionalidades";
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON, value = "/cartoes")
    public List<Cartao> listarCartao() {
        return service.findAll();
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, value = "/cartoes")
    public Cartao emissaoCartao(@RequestBody Cartao cartao) {
        return service.emitirCartao(cartao.getNome(), cartao.getSaldo());
    }
    
    @RequestMapping(method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON, value = "/cartoes")
    public Transacao autorizacaoVenda(@RequestBody Venda venda) {
        return service.autorizarVenda(venda);
    }
    
}
