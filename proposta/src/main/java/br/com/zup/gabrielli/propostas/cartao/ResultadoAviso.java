package br.com.zup.gabrielli.propostas.cartao;

public enum ResultadoAviso {

	CRIADO, FALHA;

	public boolean estaOK() {
		if (this.equals(CRIADO))
			return true;
		return false;
	}

}
