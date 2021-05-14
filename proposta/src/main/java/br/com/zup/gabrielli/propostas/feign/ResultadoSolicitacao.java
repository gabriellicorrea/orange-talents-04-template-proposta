package br.com.zup.gabrielli.propostas.feign;

import br.com.zup.gabrielli.propostas.proposta.Status;

public enum ResultadoSolicitacao {

	COM_RESTRICAO(Status.NAO_ELEGIVEL),
	SEM_RESTRICAO(Status.ELEGIVEL);
	
	private Status status;

	ResultadoSolicitacao(Status status){
		this.status = status;
	}

	public Status getStatus(){
		return status;
	}
	
}
