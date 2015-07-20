package br.edu.ifpb.ads.testes.AplicacaoPessoa;

import br.edu.ifpb.ads.exceptions.ElementoInexistenteException;
import br.edu.ifpb.ads.exceptions.EstruturaVaziaException;
import br.edu.ifpb.ads.exceptions.PosicaoInvalidaException;

public class ClienteTestePessoa {

	public static void main(String[] args) {
		AplicacaoPessoa aplicacao = new AplicacaoPessoa();

		try {
			aplicacao.addPessoaFim("Fulano", 'M', 19);
			
			aplicacao.addPessoaFim("Fulano1", 'M', 32);

			aplicacao.addPessoaInicio("Fulano2", 'F', 20);

			aplicacao.addPessoaPosicao("Fulano3", 'M', 25);
			
			aplicacao.addPessoaFim("Fulano4", 'F', 46);
			
			aplicacao.addPessoaFim("Fulano5", 'F', 28);
			
			aplicacao.addPessoaFim("Fulano6", 'M', 46);
			
			aplicacao.addPessoaFim("Fulano7", 'M', 78);
			
			aplicacao.removeFim();
			
			aplicacao.removeInicio();
			
			aplicacao.removePosicao(4);
			
			aplicacao.removerPessoa(aplicacao.getPessoa(3));
			
			aplicacao.getPessoa(4);
			
			aplicacao.getPosicao(aplicacao.getPessoa(2));

			System.out.println(aplicacao.getListaPessoas());

		} catch (PosicaoInvalidaException | EstruturaVaziaException | ElementoInexistenteException e) {
			e.printStackTrace();
		}

	}
}
