package br.com.zup.gabrielli.propostas.feign;

public class AnaliseRequest {

	private String documento;
	private String nome;
	private Long idProposta;
	

	/*Para uso do hibernate*/
	@Deprecated
	public AnaliseRequest() {
	}

	public AnaliseRequest(String documento, 
						  String nome, 
						  Long idProposta) {

		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
	}

	

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public Long getIdProposta() {
		return idProposta;
	}
	
	
	
	
	
}
