package br.edu.ifpb.ads.util;

import br.edu.ifpb.ads.exceptions.ElementoInexistenteException;
import br.edu.ifpb.ads.exceptions.EstruturaVaziaException;
import br.edu.ifpb.ads.exceptions.PosicaoInvalidaException;

/**
 * 
 * Classe respons�vel por implementar lista duplamente encadeada com n�
 * descritor
 * 
 * @author Franck Arag�o
 * 
 * @param <T>
 */
public class TListaDuplamenteEncadeada<T> implements TLista<T> {

	private TNo<T> noInicio;
	private TNo<T> noFim;

	/**
	 * construtor default, que inicializa os atributos
	 */
	public TListaDuplamenteEncadeada() {
		noInicio = null;
		noFim = null;
	}

	/**
	 * 
	 * Metodo respons�vel por enserir um elemento no final da lista
	 * 
	 * @param valor - valor que ser� inserido na lista
	 * 
	 */
	@Override
	public void inserirNoFinal(T valor) {

		TNo<T> novoNo = new TNo(noFim, null, valor);

		if (isVazia()) {
			this.inserirNoInicio(valor);
		} else {
			noFim.setProx(novoNo);
			noFim = novoNo;
		}
	}

	/**
	 * Insere um elemento no inicio da lista
	 * 
	 * @param valor - valor que ser� inserido na lista
	 * 
	 */
	@Override
	public void inserirNoInicio(T valor) {

		TNo<T> novoNo = new TNo(null, noInicio, valor);

		if (isVazia()) {
			noInicio = novoNo;
			noFim = novoNo;
		}
		noInicio.setAnt(novoNo);
		noInicio = novoNo;
	}

	/**
	 * Metodo insere um elemento na lista em uma posicao passada por parametro
	 * 
	 * @param valor - valor a ser inserido na lista
	 * 
	 * @param posicao - posicao em que o valor ser� inserido
	 * 
	 * @throws PosicaoInvalidaException - posi��o invalida
	 * 
	 */
	@Override
	public void inserirNumaPosicao(T valor, int posicao) throws PosicaoInvalidaException {

		if (posicao < 1 || posicao > getTamanho())
			throw new PosicaoInvalidaException();

		if (posicao == 0)
			this.inserirNoInicio(valor);

		else if (posicao == this.getTamanho())
			this.inserirNoFinal(valor);

		else {
			TNo<T> noAnterior = this.getNoPelaPosicao(posicao - 2);
			TNo<T> noProximo = noAnterior.getProx();
			TNo<T> novoNo = new TNo(noAnterior.getAnt(), noAnterior.getProx(), valor);

			noAnterior.setProx(novoNo);
			noProximo.setAnt(novoNo);
		}
	}

	/**
	 * Metodo remove o elemento do fim da lista
	 * 
	 * @throws EstruturaVaziaException
	 * 
	 * @return elemento removido
	 */
	@Override
	public T removerDoFinal() throws EstruturaVaziaException {

		if (isVazia())
			throw new EstruturaVaziaException();

		TNo<T> ultimo = noFim;

		if (getTamanho() == 1) {
			this.removerDoInicio();
		} else {
			TNo<T> penultimo = ultimo.getAnt();
			penultimo.setProx(null);
			noFim = penultimo;
		}
		return ultimo.getDado();
	}

	/**
	 * metodo remove um elemento do inicio da lista
	 * 
	 * @throws EstruturaVaziaException
	 * 
	 * @return elemento removido
	 */
	@Override
	public T removerDoInicio() throws EstruturaVaziaException {
		if (isVazia())
			throw new EstruturaVaziaException();

		TNo<T> ultimo = noInicio;
		noInicio = noInicio.getProx();
		ultimo.setProx(null);

		return ultimo.getDado();
	}

	/**
	 * metodo remove um elemento de uma posicao especifica
	 * 
	 * @throws EstruturaVaziaException - lista vazia
	 * 
	 * @throws PosicaoInvalidaException - posi��o invalida
	 *
	 * @return elemento removido
	 */
	@Override
	public T removerDaPosicao(int posicao) throws EstruturaVaziaException, PosicaoInvalidaException {

		if (posicao < 1 || posicao > getTamanho())
			throw new PosicaoInvalidaException();

		if (isVazia())
			throw new EstruturaVaziaException();

		TNo<T> noDaPosicao = this.getNoPelaPosicao(posicao - 1);

		if (posicao == 1) {
			this.removerDoInicio();

		} else if (posicao == this.getTamanho()) {
			this.removerDoFinal();

		} else {
			TNo<T> noProx = noDaPosicao.getProx();
			TNo<T> noAnt = noDaPosicao.getAnt();

			noAnt.setProx(noProx);
			noProx.setAnt(noAnt);
		}
		return noDaPosicao.getDado();
	}

	/**
	 * Met�do remove um n� da lista correspodente valor passado por paramentro
	 * 
	 * @param valor - valor da correspodente ao n�
	 * 
	 * @return - elemento corrrespodente ao no
	 * 
	 * @throws EstruturaVaziaException - se a lista vazia
	 * 
	 * @throws ElementoInexistenteException - quando valor n�o existe na lista
	 * 
	 */
	@Override
	public T removerElemento(T valor) throws EstruturaVaziaException, ElementoInexistenteException {

		if (isVazia())
			throw new EstruturaVaziaException();

		TNo<T> noDoValor = this.getNoPeloValor(valor);

		if (noDoValor.getProx() == null)
			this.removerDoFinal();
			
		else if (noDoValor.getAnt().getAnt() == null) 
			this.removerDoInicio();
			
		else {
			TNo<T> noProx = noDoValor.getProx();
			TNo<T> noAnt = noDoValor.getAnt();

			noAnt.setProx(noProx);
			noProx.setAnt(noAnt);
		}
		return noDoValor.getDado();
	}

	/**
	 * Met�do verifica se o elemento passado por parametro existe na lista
	 * 
	 * @param valor - valor que ser� verificado se existe
	 * 
	 * @return - true se existir, false se n�o existir
	 */
	@Override
	public boolean existe(T valor) {
		if (isVazia())
			return false;

		TNo<T> aux = noInicio;
		while (aux != null) {
			if (aux.getDado().equals(valor))
				return true;
			aux = aux.getProx();
		}
		return false;
	}

	/**
	 * Met�do retorna um valor correspondente um uma posic�o da lista
	 * 
	 * @param posicao - posi��o da lista
	 * 
	 * @return - elemento da posi��o
	 * 
	 * @throws EstruturaVaziaException - lista vazia
	 * 
	 * @throws PosicaoInvalidaException - posi��o inexistente
	 * 
	 */
	@Override
	public T elementoDaPosicao(int posicao) throws EstruturaVaziaException, PosicaoInvalidaException {

		if (posicao < 1 || posicao > getTamanho())
			throw new PosicaoInvalidaException();

		if (isVazia())
			throw new EstruturaVaziaException();

		TNo<T> noPosicao = this.getNoPelaPosicao(posicao - 1);

		return noPosicao.getDado();
	}

	/**
	 * Metodo retorna a posi��o de um elemento passado por parametro
	 * 
	 * @param valor - valor que contem na lista
	 * 
	 * @return - posi��o do elemento
	 * 
	 * @throws EstruturaVaziaException - lista estiver fazia
	 * 
	 * @throws ElementoInexistenteException - elemento n�o existe na lista
	 * 
	 */
	@Override
	public int posicaoDoElemento(T valor) throws EstruturaVaziaException, ElementoInexistenteException {
		if (isVazia())
			throw new EstruturaVaziaException();

		int cont = 1;
		TNo<T> aux = noInicio;

		while (aux != null) {
			if (aux.getDado().equals(valor))
				return cont;
			aux = aux.getProx();
			cont++;
		}
		throw new ElementoInexistenteException();
	}

	/**
	 * Verifica se a lista esta vazia
	 * 
	 * @return - verdadeiro se a lista tenha elemento falso contrario
	 */
	@Override
	public boolean isVazia() {
		return (noInicio == null);
	}

	/**
	 * Retorna um n� na lista referente a posi��o passada por parametro
	 * 
	 * @param posicao - posic�o da lista
	 * 
	 * @return n� referente a posi��o
	 */
	public TNo getNoPelaPosicao(int posicao) {

		TNo no = noInicio;

		for (int i = 0; i < posicao; i++) {
			no = no.getProx();
		}
		return no;
	}

	/**
	 * Metodo retorna no correspondente ao valor passado por parametro
	 * 
	 * @param valor do no
	 * 
	 * @return n� correspodente ao valor
	 * 
	 * @throws ElementoInexistenteException valor n�o existe na lista
	 * 
	 */
	public TNo getNoPeloValor(T valor) throws ElementoInexistenteException {

		TNo<T> aux = noInicio;

		while (aux != null) {
			if (aux.getDado().equals(valor))
				return aux;
			aux = aux.getProx();
		}
		throw new ElementoInexistenteException();
	}

	/**
	 * Obtem o tamanho da lista
	 */
	@Override
	public int getTamanho() {
		int tamanho = 0;

		TNo<T> aux = noInicio;
		while (aux != null) {
			tamanho++;
			aux = aux.getProx();
		}
		return tamanho;
	}

	/**
	 * retorna a representa��o da lista em String
	 * 
	 * @return a lista em formato de String
	 */
	public String toString() {

		if (isVazia())
			return "Lista: []";

		TNo<T> aux = noInicio;
		StringBuffer sb = new StringBuffer("[");

		while (aux != null) {
			sb.append(aux.getDado());
			sb.append(", ");
			aux = aux.getProx();
		}
		sb.append("]");

		return "Lista: " + sb.toString();
	}
}
