package br.com.zup.gabrielli.propostas.carteira;

import javax.persistence.*;
import javax.validation.constraints.*;

import br.com.zup.gabrielli.propostas.cartao.Cartao;

@Entity
public class Carteira {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@NotBlank
	private String email;

	@NotBlank
	private String carteira;

	@ManyToOne
	private Cartao cartao;

	public TipoCarteira tipoCarteira = TipoCarteira.PAYPAL;
	
	public Carteira(@NotBlank String email, @NotBlank String carteira, Cartao cartao, TipoCarteira tipoCarteira) {
		this.email = email;
		this.carteira = carteira;
		this.cartao = cartao;
		this.tipoCarteira = tipoCarteira;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public Cartao getCartao() {
		return cartao;
	}
	
	public TipoCarteira getTipoCarteira() {
		return tipoCarteira;
	}

}
