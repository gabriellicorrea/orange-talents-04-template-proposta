package br.com.zup.gabrielli.propostas.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "solicitacao", url = "${financeiro.dominio}")
@Component
public interface AnaliseClient {

	@RequestMapping(method = RequestMethod.POST)
	public AnaliseResponse analisa(AnaliseRequest analiseRequest);

}
