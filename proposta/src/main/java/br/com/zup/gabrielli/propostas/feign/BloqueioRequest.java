package br.com.zup.gabrielli.propostas.feign;

public class BloqueioRequest {

	public String sistemaResponsavel = "bloquear";

	public BloqueioRequest(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

}
