package br.com.zup.gabrielli.propostas.proposta;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.gabrielli.propostas.feign.AnaliseClient;
import br.com.zup.gabrielli.propostas.feign.AnaliseRequest;
import br.com.zup.gabrielli.propostas.feign.AnaliseResponse;

@RestController
@RequestMapping("/proposta")
public class PropostaController {
	
	@Autowired
	PropostaRepository propostaRepository;
	
	@Autowired
	private AnaliseClient analiseClient;
	
	@PostMapping
	public ResponseEntity<?> salvarProposta(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder uriBuilder) {
		Proposta proposta = request.converter();
		
		Optional<Proposta> cpfCnpjList = propostaRepository.findByDocumento(proposta.getDocumento());
		
		if(cpfCnpjList.isPresent()) {
			return ResponseEntity.unprocessableEntity().body("Proposta invalida! Já existe esse numero de documento cadastrado.");
		}
		
		propostaRepository.save(proposta);
		
		AnaliseRequest analiseRequest = new AnaliseRequest(proposta.getDocumento(),
														   proposta.getNome(),
														   proposta.getId()); 
														
		AnaliseResponse resultadoDaConsulta = analiseClient.analisa(analiseRequest);
		
		Status status = resultadoDaConsulta.status();
		
		proposta.setStatus(status);
		
		propostaRepository.save(proposta);
		
		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		//return "tudo funcionando";
		return ResponseEntity.created(uri).build();	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<NovaPropostaRequest> detalhar(@PathVariable Long id) {
		Optional<Proposta> possivelProposta = propostaRepository.findById(id);
		if (possivelProposta.isPresent()) {
			return ResponseEntity.ok(new NovaPropostaRequest(possivelProposta.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
}
