package br.com.zup.gabrielli.propostas.carteira;

import javax.validation.constraints.*;

import br.com.zup.gabrielli.propostas.cartao.Cartao;

public class CarteiraRequest {

	@NotBlank
	private String email;

	private String carteira;

	public CarteiraRequest(String email, String carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public String getEmail() {
		return email;
	}

	public String getCarteira() {
		return carteira;
	}

	public Carteira toModel(Cartao cartao, TipoCarteira tipoCarteira) {
		return new Carteira(email, carteira, cartao, tipoCarteira);
	}

}
