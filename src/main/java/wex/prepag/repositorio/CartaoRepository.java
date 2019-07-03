package wex.prepag.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import wex.prepag.model.Cartao;

public interface CartaoRepository extends CrudRepository<Cartao, Long>{
	
	Cartao findByNumero(@Param("Cartao") String numero);
}
