package br.com.zup.gabrielli.propostas.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zup.gabrielli.propostas.feign.BloqueioRequest;
import br.com.zup.gabrielli.propostas.feign.BloqueioResponse;

@FeignClient(value = "cartao", url = "${cartao.dominio}")
@Component
public interface CartaoClient {
	
	@RequestMapping(method = RequestMethod.GET)
	CartaoResponse analisaNumeroCartao(@RequestParam("idProposta") Long  idProposta);

	@RequestMapping(method = RequestMethod.POST, value = "{id}/bloqueios", produces  = "application/json")
    public BloqueioResponse bloquearCartao(@PathVariable("id") String numeroCartao, @RequestBody BloqueioRequest bloqueioRequest);
}