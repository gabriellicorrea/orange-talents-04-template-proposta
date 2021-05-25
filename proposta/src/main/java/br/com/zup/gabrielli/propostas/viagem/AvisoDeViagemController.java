package br.com.zup.gabrielli.propostas.viagem;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.gabrielli.propostas.cartao.Cartao;

@RestController
@RequestMapping("/viagem")
public class AvisoDeViagemController {

	@PersistenceContext
	EntityManager manager;

	@PostMapping("/{id}")
	public ResponseEntity<?> cadastrar(@PathVariable("id") Long idCartao, @Valid AvisoDeViagemRequest avisoDeViagemRequest,
				HttpServletRequest request, UriComponentsBuilder uriBuilder) {
		
		Cartao cartao = manager.find(Cartao.class, idCartao);
		if(cartao == null)
			return ResponseEntity.notFound().build();
		
		if(!cartao.getAtivo())
			return ResponseEntity.unprocessableEntity().body("Não possivel solictar viagem em um cartão bloqueado.");
		
		avisoDeViagemRequest.setIpCliente(request.getRemoteAddr());
		avisoDeViagemRequest.setAgenteUsuario(request.getHeader("User-Agent"));
		
		AvisoDeViagem viagem = avisoDeViagemRequest.toModel(cartao);
		
		manager.persist(viagem);
		
		URI uri = uriBuilder.path("/proposta").buildAndExpand().toUri();
		
		return ResponseEntity.created(uri).body("Notificação de viagem criada");
	}

}
