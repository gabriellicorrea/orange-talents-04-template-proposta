package br.com.zup.gabrielli.propostas.proposta;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.gabrielli.propostas.validacao.CpfCnpj;

@Entity
public class Proposta {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull @NotEmpty @CpfCnpj
	private String cpfCnpj;
	@NotNull @NotEmpty @Email
	private String email;
	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String endereco;
	@NotNull @Positive
	private BigDecimal  salario;
	
	//para uso do hibernate
	@Deprecated
	public Proposta() {
	}

	
	
	public Proposta(@NotNull @NotEmpty  String cpfCnpj, @NotNull @NotEmpty @Email String email,
			@NotNull @NotEmpty String nome, @NotNull @NotEmpty String endereco, @NotNull @Positive BigDecimal salario) {
		
		this.cpfCnpj = cpfCnpj;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}



	public Long getId() {
		return id;
	}

	
	

	
}

/*
 * documento do solicitante deve ser obrigatório e válido email não pode ser
 * vazio, nulo ou inválido nome não pode ser vazio ou nulo endereço não pode ser
 * vazio ou nulo salário bruto não pode ser vazio, nulo ou negativo
 */