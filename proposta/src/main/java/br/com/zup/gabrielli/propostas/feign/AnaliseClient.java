package br.com.zup.gabrielli.propostas.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "proposta", url = "http://localhost:9999")
@Component
public interface AnaliseClient {

	@PostMapping("api/solicitacao")
	AnaliseResponse analisa(AnaliseRequest analiseRequest);

}
