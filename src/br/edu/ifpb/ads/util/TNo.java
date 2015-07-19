package br.edu.ifpb.ads.util;

/**
 * Classe representa um nó da lista, que aponta para o nó anterior e o proximo
 * nó e que contem o elemento da lista
 * 
 * @author franck
 *
 * @param <T>
 */
@SuppressWarnings("rawtypes")
public class TNo<T> {

	private TNo prox;
	private TNo ant;
	private T dado;

	public TNo() {
	}

	public TNo(T dado) {
		this.dado = dado;
	}

	public TNo(TNo ant, TNo prox, T dado) {
		this.prox = prox;
		this.ant = ant;
		this.dado = dado;
	}

	public TNo getProx() {
		return prox;
	}

	public void setProx(TNo prox) {
		this.prox = prox;
	}

	public TNo getAnt() {
		return ant;
	}

	public void setAnt(TNo ant) {
		this.ant = ant;
	}

	public T getDado() {
		return dado;
	}

	public void setDado(T dado) {
		this.dado = dado;
	}

	public String toString() {
		return "dado:" + dado;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TNo other = (TNo) obj;
		if (dado == null) {
			if (other.dado != null)
				return false;
		} else if (!dado.equals(other.dado))
			return false;
		return true;
	}
}
