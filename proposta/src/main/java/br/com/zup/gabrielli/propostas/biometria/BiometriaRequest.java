package br.com.zup.gabrielli.propostas.biometria;

import java.util.Base64;

import javax.validation.constraints.NotBlank;

import br.com.zup.gabrielli.propostas.cartao.Cartao;

public class BiometriaRequest {

	@NotBlank
	public String biometria;

	public String getBiometria() {
		return biometria;
	}

	//transformar a string/biometria em string base64
	public Biometria toModel(Cartao cartao) {
		byte[] biometriaBase = Base64.getDecoder().decode(this.biometria.getBytes());
		String biometriaString = new String(biometriaBase);
		return new Biometria(biometriaString, cartao);
	}

}
