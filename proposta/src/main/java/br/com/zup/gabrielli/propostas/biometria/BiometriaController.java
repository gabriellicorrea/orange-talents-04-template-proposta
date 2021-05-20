package br.com.zup.gabrielli.propostas.biometria;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.gabrielli.propostas.cartao.Cartao;

@RestController
@RequestMapping("/biometria")
public class BiometriaController {
		
	@PersistenceContext
	EntityManager manager;
	
	 
	@Transactional
	@PostMapping(value = "/{id}")
	public ResponseEntity<?> cadastrar(@PathVariable("id") Long idCartao, @RequestBody @Valid BiometriaRequest biometriaRequest, 
											UriComponentsBuilder uriBuilder){

		Cartao cartao = manager.find(Cartao.class, idCartao);

		Biometria biometria = biometriaRequest.toModel(cartao);
		manager.persist(biometria);
		
		URI uri = uriBuilder.path("/biometria").buildAndExpand().toUri();
		
		return ResponseEntity.created(uri).body(biometria.toString());
		
	}
	
}
