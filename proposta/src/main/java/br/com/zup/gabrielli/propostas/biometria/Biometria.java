package br.com.zup.gabrielli.propostas.biometria;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zup.gabrielli.propostas.cartao.Cartao;

@Entity
public class Biometria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String biometria;

	@CreationTimestamp
	private OffsetDateTime dataDeCriação;

	// muitas biometrias para um cartão
	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public Biometria() {
	}

	public Biometria(String biometria, Cartao cartao) {
		this.biometria = biometria;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public Cartao getCartao() {
		return cartao;
	}

}
