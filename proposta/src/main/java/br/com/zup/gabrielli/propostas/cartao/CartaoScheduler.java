package br.com.zup.gabrielli.propostas.cartao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zup.gabrielli.propostas.proposta.Proposta;
import br.com.zup.gabrielli.propostas.proposta.PropostaRepository;

@Component
@EnableScheduling
public class CartaoScheduler {
	
	@Autowired
	CartaoClient cartaoClient;

    @Autowired
    PropostaRepository propostaRepository;
    
    static final Logger logger = LogManager.getLogger(CartaoScheduler.class.getName());

    @Scheduled(fixedRate = 10000) 
    private void associarCartaoComProposta(){
    	
    	List<Proposta> propostasParaAnalise = propostaRepository.propostasAprovadasSemCartao();
    	logger.info("Existem {} propostas para analise", propostasParaAnalise.size());
  
    	for (Proposta proposta : propostasParaAnalise) {
    		try {
    			CartaoResponse cartaoResponse = cartaoClient.analisaNumeroCartao(proposta.getId());
    			logger.info("Asssociando cartão {} para a proposta {} ", cartaoResponse.getId(), proposta.getId());
    			Cartao cartao = new Cartao(cartaoResponse.getId());
				proposta.setCartao(cartao);
				propostaRepository.save(proposta);
			} catch (Exception e) {
				logger.error("Falha na atualizacao para a proposta de id {}", proposta.getId());
			}
    	}

    }

}
