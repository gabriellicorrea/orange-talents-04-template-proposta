package br.com.zup.gabrielli.propostas.feign;

public enum ResultadoBloqueio {

	BLOQUEADO, FALHA;

    public boolean converter() {
        if(this.equals(BLOQUEADO))
            return true;
        return false;
    }
}
