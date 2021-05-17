package br.com.zup.gabrielli.propostas.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cartao", url = "http://localhost:8888")
@Component
public interface CartaoClient {
	
	@GetMapping(value = "/api/cartoes")
	CartaoResponse analisaNumeroCartao(@RequestParam("idProposta") Long  idProposta);

}