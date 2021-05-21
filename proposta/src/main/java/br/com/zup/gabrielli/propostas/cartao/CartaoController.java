package br.com.zup.gabrielli.propostas.cartao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class CartaoController {
	
	@PersistenceContext
    EntityManager manager;
	
	@Transactional
	@PostMapping (value = "/bloquearCartao/{id}")
	public ResponseEntity<?> bloquear(@PathVariable("id") Long idCartao, UriComponentsBuilder builder, HttpServletRequest request){
		
		Cartao cartao = manager.find(Cartao.class, idCartao);
		
		if(cartao == null)
            return ResponseEntity.notFound().build();
        if(!cartao.getAtivo())
            return ResponseEntity.unprocessableEntity().body("Cartão já está bloqueado.");
        
        CartaoDadosBloqueio dadosBloqueio = new CartaoDadosBloqueio(request.getRemoteAddr(), request.getHeader("User-Agent"), cartao);
        cartao.setAtivo(false);
        manager.persist(cartao);
        manager.persist(dadosBloqueio);
        return ResponseEntity.ok().body("Cartão bloqueado");
      
	}

}
