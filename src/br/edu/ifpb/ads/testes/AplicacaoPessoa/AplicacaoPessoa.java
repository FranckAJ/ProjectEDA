package br.edu.ifpb.ads.testes.AplicacaoPessoa;

import br.edu.ifpb.ads.util.TListaDuplamenteEncadeada;

public class AplicacaoPessoa {
	
	private TListaDuplamenteEncadeada<Pessoa> pessoas;
	
	public AplicacaoPessoa() {
		pessoas = new TListaDuplamenteEncadeada<>();
	}
	
	public void addPessoa(String nome, char sexo, int idade){
		Pessoa pessoa = new Pessoa(nome, sexo, idade);
		pessoas.inserirNoFinal(pessoa);
	}
}
