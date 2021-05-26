package br.com.zup.gabrielli.propostas.carteira;

public enum ResultadoAssociacao {

	ASSOCIADA, FALHA;

    public boolean estaOK() {
        if (this.equals(ASSOCIADA))
            return true;
        return false;
    }

}
