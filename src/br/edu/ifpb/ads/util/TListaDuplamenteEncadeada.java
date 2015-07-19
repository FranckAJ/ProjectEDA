package br.edu.ifpb.ads.util;

import br.edu.ifpb.ads.exceptions.ElementoInexistenteException;
import br.edu.ifpb.ads.exceptions.EstruturaVaziaException;
import br.edu.ifpb.ads.exceptions.PosicaoInvalidaException;

/**
 * 
 * Classe responsável por implementar lista duplamente encadeada com nó
 * descritor
 * 
 * @author Franck Aragão
 * 
 * @version 1.0
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
	 * Metodo responsável por enserir um elemento no final da lista
	 * 
	 * @param valor - valor que será inserido na lista
	 * 
	 */
	@Override
	public void inserirNoFinal(Object valor){

		TNo<T> novoNo = new TNo(noInicio, null, valor);

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
	 * @param valor - valor que será inserido na lista
	 * 
	 */
	@Override
	public void inserirNoInicio(Object valor){

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
	 * @param posicao - posicao em que o valor será inserido
	 * 
	 * @throws  PosicaoInvalidaException - posição invalida          
	 * 
	 */
	@Override
	public void inserirNumaPosicao(int valor, int posicao) throws PosicaoInvalidaException {

		if (posicao < 0 || posicao > getTamanho())
			throw new PosicaoInvalidaException();

		if (posicao == 0)
			this.inserirNoInicio(valor);

		else if (posicao == this.getTamanho())
			this.inserirNoFinal(valor);

		else {
			TNo<T> noNaPosicao = this.getNoPelaPosicao(posicao - 1);
			TNo<T> novoNo = new TNo(noNaPosicao.getAnt(), noNaPosicao.getProx(), valor);
			noNaPosicao = noNaPosicao.getAnt();
			noNaPosicao.setProx(novoNo);

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

		if (getTamanho() == 1)
			this.removerDoInicio();

		TNo<T> ultimo = noFim;
		TNo<T> penultimo = ultimo.getAnt();
		penultimo.setProx(null);
		noFim = penultimo;

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
	 *@throws  PosicaoInvalidaException - posição invalida
	 *
	 *@return elemento removido            
	 */
	@Override
	public T removerDaPosicao(int posicao) throws EstruturaVaziaException, PosicaoInvalidaException {

		if (posicao < 0 || posicao > getTamanho())
			throw new PosicaoInvalidaException();

		if (isVazia())
			throw new EstruturaVaziaException();

		TNo<T> noDaPosicao = this.getNoPelaPosicao(posicao - 1);
		TNo<T> noProx = noDaPosicao.getProx();
		TNo<T> noAnt = noDaPosicao.getAnt();

		noAnt.setProx(noProx);
		noProx.setAnt(noAnt);

		return noDaPosicao.getDado();
	}

	/**
	 * Metódo remove um nó da lista correspodente valor passado por paramentro
	 * 
	 * @param valor - valor da correspodente ao nó
	 *            
	 * @return - elemento corrrespodente ao no
	 * 
	 * @throws EstruturaVaziaException - se a lista vazia
	 * 
	 * @throws ElementoInexistenteException - quando valor não existe na lista
	 *          
	 */
	@Override
	public T removerElemento(Object valor) throws EstruturaVaziaException, ElementoInexistenteException {

		if (isVazia())
			throw new EstruturaVaziaException();

		TNo<T> noDoValor = this.getNoPeloValor(valor);
		TNo<T> noProx = noDoValor.getProx();
		TNo<T> noAnt = noDoValor.getAnt();

		noAnt.setProx(noProx);
		noProx.setAnt(noAnt);

		return noDoValor.getDado();
	}

	/**
	 * Metódo verifica se o elemento passado por parametro existe na lista
	 * 
	 * @param valor - valor que será verificado se existe
	 * 
	 * @return - true se existir, false se não existir
	 */
	@Override
	public boolean existe(Object valor) {
		if (isVazia())
			return false;

		TNo<T> aux = noInicio;
		while (aux != null){
			if (aux.getDado().equals(valor))
				return true;
			aux = aux.getProx();
		}
		return false;
	}

	/**
	 * Metódo retorna um valor correspondente um uma posicão da lista
	 * 
	 * @param posicao - posição da lista
	 * 
	 * @return - elemento da posição
	 * 
	 * @throws EstruturaVaziaException - lista vazia
	 * 
	 * @throws PosicaoInvalidaException - posição inexistente
	 *             
	 */
	@Override
	public T elementoDaPosicao(int posicao) throws EstruturaVaziaException, PosicaoInvalidaException {

		if (posicao < 0 || posicao > getTamanho())
			throw new PosicaoInvalidaException();

		if (isVazia())
			throw new EstruturaVaziaException();

		TNo<T> noPosicao = this.getNoPelaPosicao(posicao - 1);

		return noPosicao.getDado();
	}

	/**
	 * Metodo retorna a posição de um elemento passado por parametro
	 * 
	 * @param valor - valor que contem na lista
	 *            
	 * @return - posição do elemento
	 * 
	 * @throws EstruturaVaziaException - lista estiver fazia
	 *             
	 * @throws ElementoInexistenteException - elemento não existe na lista
	 *             
	 */
	@Override
	public int posicaoDoElemento(Object valor) throws EstruturaVaziaException, ElementoInexistenteException {
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
	 * Retorna um nó na lista referente a posição passada por parametro
	 * 
	 * @param posicao - posicão da lista
	 *            
	 * @return - nó referente a posição
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
	 * @param valor - valor do no
	 *            
	 * @return - nó correspodente ao valor
	 * 
	 * @throws ElementoInexistenteException - valor não existe na lista
	 */
	public TNo getNoPeloValor(Object valor) throws ElementoInexistenteException {

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
	 * retorna a representação da lista em String
	 * 
	 * @return - a lista em formato de String
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
