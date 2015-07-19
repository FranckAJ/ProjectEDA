package br.edu.ifpb.ads.util;

import br.edu.ifpb.ads.exceptions.ElementoInexistenteException;
import br.edu.ifpb.ads.exceptions.EstruturaVaziaException;
import br.edu.ifpb.ads.exceptions.PosicaoInvalidaException;

/**
 * Interface que será implementada pela lista duplamente encandeada
 * 
 * @author Franck Aragão
 *
 * @param <T>
 */
public interface TLista<T> {

	void inserirNoFinal(T valor);
	
	void inserirNoInicio(T valor);
	
	void inserirNumaPosicao(T valor, int posicao) throws PosicaoInvalidaException;
	
	T removerDoFinal() throws EstruturaVaziaException;
	
	T removerDoInicio() throws EstruturaVaziaException;

	T removerDaPosicao(int posicao) throws EstruturaVaziaException, PosicaoInvalidaException;
	
	T removerElemento(T valor) throws EstruturaVaziaException, ElementoInexistenteException;
	
	boolean existe(T valor);
	
	T elementoDaPosicao(int posicao) throws EstruturaVaziaException, PosicaoInvalidaException;
	
	int posicaoDoElemento(T valor) throws EstruturaVaziaException, ElementoInexistenteException;
	
	boolean isVazia();
	
	int getTamanho();
	
	
}
