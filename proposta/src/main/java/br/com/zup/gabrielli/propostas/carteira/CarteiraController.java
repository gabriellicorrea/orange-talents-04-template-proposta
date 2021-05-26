package br.com.zup.gabrielli.propostas.carteira;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.gabrielli.propostas.cartao.Cartao;
import br.com.zup.gabrielli.propostas.cartao.CartaoClient;

@RestController
public class CarteiraController {

	@PersistenceContext
	EntityManager manager;

	@Autowired
	CartaoClient cartaoClient;

	@PostMapping(value = "/associarPaypal/{id}")
	@Transactional
	public ResponseEntity<?> associarPaypal(@PathVariable("id") Long idCartao,
			@Valid @RequestBody CarteiraRequest carteiraRequest, UriComponentsBuilder uriBuilder) {

		return associar(idCartao, carteiraRequest, uriBuilder, TipoCarteira.PAYPAL);

	}

	private ResponseEntity<?> associar(Long idCartao, @Valid CarteiraRequest carteiraRequest,
			UriComponentsBuilder uriBuilder, TipoCarteira tipoCarteira) {

		Cartao cartao = manager.find(Cartao.class, idCartao);
		if (cartao == null)
            return ResponseEntity.notFound().build();
		
		boolean resultadoOK = resultadoAssociacao(cartao.getNumero(), carteiraRequest);
        if (!resultadoOK)
            return ResponseEntity.badRequest().body("Erro");
		
        Carteira carteira = carteiraRequest.toModel(cartao, tipoCarteira);
        manager.persist(carteira);
        cartao.setAssociadoPaypal(true);
        manager.persist(cartao);
        URI uri = uriBuilder.path("/proposta").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body("Associado com sucesso!");
	}

	
	
	private boolean resultadoAssociacao( String numeroCartao, CarteiraRequest  carteiraRequest) {
		CarteiraResponse carteiraResponse = cartaoClient.associarCarteira(numeroCartao, carteiraRequest); 
		return carteiraResponse.getResultadoAssociacao().estaOK();
	}

}
