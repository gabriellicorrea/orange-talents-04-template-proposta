package br.com.zup.gabrielli.propostas.proposta;

import java.math.BigDecimal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import br.com.zup.gabrielli.propostas.cartao.Cartao;
import br.com.zup.gabrielli.propostas.validacao.Documento;

public class NovaPropostaRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull @NotEmpty @Documento
	private String documento;
	@NotNull @NotEmpty @Email
	private String email;
	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String endereco;
	@NotNull @Positive
	private BigDecimal salario;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private Cartao cartao;
	
	public NovaPropostaRequest(@NotNull @NotEmpty @CPF @CNPJ String documento, @NotNull @NotEmpty @Email String email,
			@NotNull @NotEmpty String nome, @NotNull @NotEmpty String endereco, @NotNull BigDecimal salario) {
		
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	
	
	public NovaPropostaRequest(Proposta proposta) {
	
		this.id = proposta.getId();
		this.nome = proposta.getNome();
		this.email = proposta.getEmail();
		this.documento = proposta.getDocumento();
		this.endereco = proposta.getEndereco();
		this.salario = proposta.getSalario();
		this.status = proposta.getStatus();
		this.cartao = proposta.getCartao();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public Status getStatus() {
		return status;
	}

	public Cartao getCartao() {
		return cartao;
	}
	
	public Proposta converter() {
		return new Proposta(documento, email, nome, endereco, salario);
	}

}
