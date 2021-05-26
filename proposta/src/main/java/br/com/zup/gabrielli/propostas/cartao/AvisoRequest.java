package br.com.zup.gabrielli.propostas.cartao;

import java.util.Date;

public class AvisoRequest {

	public String destino;
	public Date validoAte;
	
	public AvisoRequest(String destino, Date validoAte) {
		this.destino = destino;
		this.validoAte = validoAte;
	}
	public String getDestino() {
		return destino;
	}
	public Date getValidoAte() {
		return validoAte;
	}
	
	
	
}
