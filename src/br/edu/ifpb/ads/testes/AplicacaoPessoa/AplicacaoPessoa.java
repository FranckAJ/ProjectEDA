package br.edu.ifpb.ads.testes.AplicacaoPessoa;

import br.edu.ifpb.ads.exceptions.ElementoInexistenteException;
import br.edu.ifpb.ads.exceptions.EstruturaVaziaException;
import br.edu.ifpb.ads.exceptions.PosicaoInvalidaException;
import br.edu.ifpb.ads.util.TListaDuplamenteEncadeada;

public class AplicacaoPessoa {

	private TListaDuplamenteEncadeada<Pessoa> pessoas;

	public AplicacaoPessoa() {
		pessoas = new TListaDuplamenteEncadeada<>();
	}

	public void addPessoaFim(String nome, char sexo, int idade) {
		Pessoa pessoa = new Pessoa(nome, sexo, idade);
		pessoas.inserirNoFinal(pessoa);
	}

	public void addPessoaInicio(String nome, char sexo, int idade) {
		Pessoa pessoa = new Pessoa(nome, sexo, idade);
		pessoas.inserirNoInicio(pessoa);
	}

	public void addPessoaPosicao(String nome, char sexo, int idade) throws PosicaoInvalidaException {
		Pessoa pessoa = new Pessoa(nome, sexo, idade);
		pessoas.inserirNumaPosicao(pessoa, 2);
	}
	
	public void removeInicio() throws EstruturaVaziaException{
		pessoas.removerDoInicio();
	}
	
	public void removeFim() throws EstruturaVaziaException{
		pessoas.removerDoFinal();
	}
	
	public void removePosicao(int posicao) throws EstruturaVaziaException, PosicaoInvalidaException{
		pessoas.removerDaPosicao(posicao);
	}
	
	public void removerPessoa(Pessoa pessoa) throws EstruturaVaziaException, ElementoInexistenteException{
		pessoas.removerElemento(pessoa);
	}
	
	public boolean pessoaExiste(Pessoa pessoa){
		return pessoas.existe(pessoa);
	}
	
	public Pessoa getPessoa(int posicao) throws EstruturaVaziaException, PosicaoInvalidaException{
		return pessoas.elementoDaPosicao(posicao);
	}
	
	public int getPosicao(Pessoa pessoa) throws EstruturaVaziaException, ElementoInexistenteException{
		return pessoas.posicaoDoElemento(pessoa);
	}

	public String getListaPessoas() {
		return pessoas.toString();
	}
}
