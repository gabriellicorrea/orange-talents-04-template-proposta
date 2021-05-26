package br.com.zup.gabrielli.propostas.viagem;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.gabrielli.propostas.cartao.AvisoRequest;
import br.com.zup.gabrielli.propostas.cartao.AvisoResponse;
import br.com.zup.gabrielli.propostas.cartao.Cartao;
import br.com.zup.gabrielli.propostas.cartao.CartaoClient;

@RestController
@RequestMapping("/viagem")
public class AvisoDeViagemController {

	@PersistenceContext
	EntityManager manager;

	@Autowired
	CartaoClient cartaoClient;

	@PostMapping("/{id}")
	public ResponseEntity<?> cadastrar(@PathVariable("id") Long idCartao,
			@Valid AvisoDeViagemRequest avisoRequest, HttpServletRequest request,
			UriComponentsBuilder uriBuilder) {

		Cartao cartao = manager.find(Cartao.class, idCartao);
		if(cartao == null)
			return ResponseEntity.notFound().build();

		if(!cartao.getAtivo())
			return ResponseEntity.unprocessableEntity().body("Não foi possivel solicitar viagem para um cartão bloqueado.");

		if(!avisarViagemCartao(cartao.getNumero(), new AvisoRequest(avisoRequest.destino, avisoRequest.validoAte)))
		return ResponseEntity.unprocessableEntity().body("Erro.");
		
		avisoRequest.setIpCliente(request.getRemoteAddr());
		avisoRequest.setAgenteUsuario(request.getHeader("User-Agent"));

		AvisoDeViagem viagem = avisoRequest.toModel(cartao);

		manager.persist(viagem);

		URI uri = uriBuilder.path("/proposta").buildAndExpand().toUri();

		return ResponseEntity.created(uri).body("Notificação de viagem criada");
	}

	private boolean avisarViagemCartao(String numeroCartao, AvisoRequest avisoRequest) {

		AvisoResponse avisoResponse = cartaoClient.avisarViagem(numeroCartao, avisoRequest);

		return avisoResponse.getResultadoAviso().estaOK();
	}

}
