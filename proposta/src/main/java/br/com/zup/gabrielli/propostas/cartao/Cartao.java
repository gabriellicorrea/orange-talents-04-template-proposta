package br.com.zup.gabrielli.propostas.cartao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.zup.gabrielli.propostas.carteira.TipoCarteira;

@Entity
@Table(name = "cartao")
public class Cartao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String numero;

	private boolean ativo = true;
	
	private boolean associadoPaypal = false;
	
	//public TipoCarteira tipoCarteira = TipoCarteira.PAYPAL;

//	public void setTipoCarteira(TipoCarteira tipoCarteira) {
//		this.tipoCarteira = tipoCarteira;
//	}

	public Cartao() {
	}

	

	public Cartao(String numero) {
		this.numero = numero;
	}

	public Long getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean getAtivo() {

		return ativo;
	}



	public boolean isAssociadoPaypal() {
		return associadoPaypal;
	}



	public void setAssociadoPaypal(boolean associadoPaypal) {
		this.associadoPaypal = associadoPaypal;
	}

}
