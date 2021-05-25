package br.com.zup.gabrielli.propostas.viagem;


import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zup.gabrielli.propostas.cartao.Cartao;

public class AvisoDeViagemRequest {

	@NotBlank
	private String destino;

	@Future
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	private LocalDate validoAte;

	@NotNull
	@DateTimeFormat
	public LocalDateTime dataCriacao = LocalDateTime.now();

	public String ipCliente;
	public String agenteUsuario;

	public AvisoDeViagemRequest(@NotBlank String destino, @Future LocalDate validoAte) {
		this.destino = destino;
		this.validoAte = validoAte;

	}

	public String getDestino() {
		return destino;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setIpCliente(String ipCliente) {
		this.ipCliente = ipCliente;
	}

	public void setAgenteUsuario(String agenteUsuario) {
		this.agenteUsuario = agenteUsuario;
	}

	public AvisoDeViagem toModel(Cartao cartao) {
		return new AvisoDeViagem(this.destino, this.validoAte, agenteUsuario, ipCliente, cartao);
	}

}
