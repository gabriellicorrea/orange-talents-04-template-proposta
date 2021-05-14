package br.com.zup.gabrielli.propostas.feign;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import br.com.zup.gabrielli.propostas.proposta.Status;

public class AnaliseResponse {
	
	@NotBlank
	private String documento;
	@NotBlank
	private String nome;
	@NotBlank
	private String idProposta;
	
	@Enumerated(EnumType.STRING)
	private ResultadoSolicitacao resultadoSolicitacao;

	public AnaliseResponse( @NotBlank String documento, 
						 	@NotBlank String nome, 
						 	@NotBlank String idProposta,
						 	ResultadoSolicitacao resultadoSolicitacao) {
	
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
		this.resultadoSolicitacao = resultadoSolicitacao;
	}

	public String getDocumento() {
		return documento;
	}

	

	public String getNome() {
		return nome;
	}

	

	public String getIdProposta() {
		return idProposta;
	}

	

	public ResultadoSolicitacao getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	public Status status() {
		return resultadoSolicitacao.getStatus();
	}

	


}
