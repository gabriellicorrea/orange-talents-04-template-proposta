package br.com.zup.gabrielli.propostas.cartao;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class CartaoDadosBloqueio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String ip;

	@CreationTimestamp
	private LocalDateTime dataBloqueio;

	@NotEmpty
	private String userAgent;

	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public CartaoDadosBloqueio() {
	}

	public CartaoDadosBloqueio(String ip, String userAgent, Cartao cartao) {
		this.ip = ip;
		this.userAgent = userAgent;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

}
