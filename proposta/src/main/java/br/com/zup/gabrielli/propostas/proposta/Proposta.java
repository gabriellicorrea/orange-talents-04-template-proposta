package br.com.zup.gabrielli.propostas.proposta;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.gabrielli.propostas.cartao.Cartao;
import br.com.zup.gabrielli.propostas.validacao.Documento;

@Entity
@Table(name = "tb_proposta")
public class Proposta implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotEmpty
	@Documento
	private String documento;
	@NotNull
	@NotEmpty
	@Email
	private String email;
	@NotNull
	@NotEmpty
	private String nome;
	@NotNull
	@NotEmpty
	private String endereco;
	@NotNull
	@Positive
	private BigDecimal salario;

	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToOne(cascade = { CascadeType.ALL })
	private Cartao cartao;

	// para uso do hibernate
	@Deprecated
	public Proposta() {
	}

	public Proposta(@NotNull @NotEmpty String documento, @NotNull @NotEmpty @Email String email,
			@NotNull @NotEmpty String nome, @NotNull @NotEmpty String endereco, @NotNull @Positive BigDecimal salario) {

		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

}

/*
 * documento do solicitante deve ser obrigatório e válido email não pode ser
 * vazio, nulo ou inválido nome não pode ser vazio ou nulo endereço não pode ser
 * vazio ou nulo salário bruto não pode ser vazio, nulo ou negativo
 */