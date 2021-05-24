package br.com.zup.gabrielli.propostas.cartao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.gabrielli.propostas.feign.BloqueioRequest;
import br.com.zup.gabrielli.propostas.feign.BloqueioResponse;

@RestController
public class CartaoController {

	@PersistenceContext
	EntityManager manager;

	@Autowired
	CartaoClient cartaoClient;

	@Transactional
	@PostMapping(value = "/bloquearCartao/{id}")
	public ResponseEntity<?> bloquear(@PathVariable("id") Long idCartao, UriComponentsBuilder builder,
			HttpServletRequest request) {

		Cartao cartao = manager.find(Cartao.class, idCartao);

		if (cartao == null)
			return ResponseEntity.notFound().build();
		if (!cartao.getAtivo())
			return ResponseEntity.unprocessableEntity().body("Cartão já está bloqueado.");

		Boolean sistemaBancarioOK = avisaSistemaBancario(cartao.getNumero());
		if (!sistemaBancarioOK)
			return ResponseEntity.badRequest().body("Não foi possivel completar a requisição");

		CartaoDadosBloqueio dadosBloqueio = new CartaoDadosBloqueio(request.getRemoteAddr(),
				request.getHeader("User-Agent"), cartao);
		cartao.setAtivo(false);
		manager.persist(cartao);
		manager.persist(dadosBloqueio);
		return ResponseEntity.ok().body("Cartão bloqueado");

	}

	private Boolean avisaSistemaBancario(String numeroCartao) {
		BloqueioResponse bloqueioResponse = cartaoClient.bloquearCartao(numeroCartao,
				new BloqueioRequest("Proposta"));
		return bloqueioResponse.getResultado().converter();
	}

}
