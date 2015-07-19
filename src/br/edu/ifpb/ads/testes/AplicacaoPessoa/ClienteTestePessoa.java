package br.edu.ifpb.ads.testes.AplicacaoPessoa;

import br.edu.ifpb.ads.exceptions.PosicaoInvalidaException;

public class ClienteTestePessoa {

	public static void main(String[] args) {
		AplicacaoPessoa aplicacao = new AplicacaoPessoa();

		try {
			aplicacao.addPessoaFim("Fulano", 'M', 19);

			aplicacao.addPessoaInicio("Fulano2", 'F', 20);

			aplicacao.addPessoaPosicao("Fulano3", 'M', 25);
			

			System.out.println(aplicacao.getListaPessoas());

		} catch (PosicaoInvalidaException e) {
			e.printStackTrace();
		}

	}
}
