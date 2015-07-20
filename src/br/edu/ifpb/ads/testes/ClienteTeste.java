package br.edu.ifpb.ads.testes;

import java.util.Scanner;

import br.edu.ifpb.ads.exceptions.ElementoInexistenteException;
import br.edu.ifpb.ads.exceptions.EstruturaVaziaException;
import br.edu.ifpb.ads.exceptions.PosicaoInvalidaException;
import br.edu.ifpb.ads.util.TListaDuplamenteEncadeada;

/**
 * Classe cliente teste, com interface via linha de comando para execução de
 * testes
 * 
 * @author franck
 *
 */
public class ClienteTeste {

	public static void menu() {
		System.out.println(" ");
		System.out.println(" [1] - Add no final");
		System.out.println(" [2] - Add no inicio");
		System.out.println(" [3] - Add em uma poisição");
		System.out.println(" [4] - Remover do final");
		System.out.println(" [5] - Remover do Inicio");
		System.out.println(" [6] - Remover de uma posicao");
		System.out.println(" [7] - Remover por Elemento");
		System.out.println(" [8] - Existencia Elemento");
		System.out.println(" [9] - Elemento da Posição");
		System.out.println(" [10] - Posição do Elemento");
		System.out.println(" [11] - Tamanho Lista");
		System.out.println(" [0] - Visualizar lista");
		System.out.println(" [ ] - Qualquer outro nº p/ parar");
	}

	public static void main(String[] args) {

		TListaDuplamenteEncadeada<Integer> lista = new TListaDuplamenteEncadeada<>();
		Scanner leitor = new Scanner(System.in);

		boolean opcSair = true;

		do {
			menu();

			try {
				switch (leitor.nextInt()) {
				case 0:
					System.out.println(lista.toString());
					break;
				case 1:
					System.out.println("Valor>>");
					lista.inserirNoFinal(leitor.nextInt());
					break;
				case 2:
					System.out.println("Valor>>");
					lista.inserirNoInicio(leitor.nextInt());
					break;
				case 3:
					System.out.println("Valor>>");
					int valor = leitor.nextInt();
					System.out.println("Posicao>>");
					lista.inserirNumaPosicao(valor, leitor.nextInt());
					break;
				case 4:
					System.out.println("Elemento removido: " + lista.removerDoFinal());
					break;
				case 5:
					System.out.println("Elemento removido: " + lista.removerDoInicio());
					break;
				case 6:
					System.out.println("Posicao>>");
					System.out.println("Elemento removido: " + lista.removerDaPosicao(leitor.nextInt()));
					break;
				case 7:
					System.out.println("Elemento>>");
					System.out.println("Elemento removido: " + lista.removerElemento(leitor.nextInt()));
					break;
				case 8:
					System.out.println("Elemento>>");
					if (lista.existe(leitor.nextInt()))
						System.out.println("Elemento Existe na lista!");
					else
						System.out.println("Elemento não existe na lista");
					break;
				case 9:
					System.out.println("Posição>>");
					System.out.println("Elemento: " + lista.elementoDaPosicao(leitor.nextInt()));
					break;
				case 10:
					System.out.println("Elemento>>");
					System.out.println("Posição: " + lista.posicaoDoElemento(leitor.nextInt()));
					break;
				case 11:
					System.out.println(lista.getTamanho());
					break;
				default:
					System.exit(0);
					break;
				}
			} catch (EstruturaVaziaException | PosicaoInvalidaException | ElementoInexistenteException e) {
				System.err.println(e.getMessage());
			}
		} while (opcSair);
	}
}