package br.com.zup.gabrielli.propostas.viagem;

import java.time.LocalDate;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zup.gabrielli.propostas.cartao.Cartao;

@Entity
public class AvisoDeViagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	
	private String destino;

	@NotNull
	private LocalDate validoAte;

	@CreationTimestamp
	private OffsetDateTime criadoEm;

	@NotEmpty
	private String ipCliente;

	@NotEmpty
	private String agenteUsuario;

	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public AvisoDeViagem() {
	}

	public AvisoDeViagem(@NotNull String destino, 
			LocalDate validoAte, 
			@NotEmpty String ipCliente,
			@NotEmpty String agenteUsuario,
			Cartao cartao) {
		this.destino = destino;
		this.validoAte = validoAte;
		this.ipCliente = ipCliente;
		this.agenteUsuario = agenteUsuario;
		this.cartao = cartao;
	}

	public Long getId() {
		return Id;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getvalidoAte() {
		return validoAte;
	}

	public OffsetDateTime getCriadoEm() {
		return criadoEm;
	}

	public String getIpCliente() {
		return ipCliente;
	}

	public String getAgenteUsuario() {
		return agenteUsuario;
	}

	public Cartao getCartao() {
		return cartao;
	}

}

/*
 * 
 * Informar o destino da viagem. Informar a data do término da viagem. Armazenar
 * o instante do aviso de viagem. Armazenar o IP do cliente que fez a
 * requisição. Armazenar o User Agent do cliente que fez a requisição
 */
