package com.ordenacao;
import com.classes.*;
public class Ordenacao {
	public Jogo [] quickSortAnoLancamento (Jogo jogos[] , int inicio , int fim) {
		int part;
		if(inicio < fim) {
			part = particaoAnoLancamento(jogos , inicio, fim);
			quickSortAnoLancamento(jogos ,  inicio , part -1);
			quickSortAnoLancamento(jogos , part +1 , fim);
		}
		return jogos;
	}
	
	private int particaoAnoLancamento (Jogo jogos[] , int inicio , int fim) {
		Jogo pivo = jogos[fim];
		int part = inicio -1;
		
		for(int i = inicio; i < fim; ++i) {
			if(jogos[i].getAnoLancamento() < pivo.getAnoLancamento()) {
				part = part +1;
				trocarAnoLancamento(jogos , i , part);
			}
		}
		part ++;
		trocarAnoLancamento(jogos , part , fim);
		return (part);
	}
	
	private void trocarAnoLancamento (Jogo jogos[] , int i , int part) {
		Jogo aux = jogos[i];
		jogos[i] = jogos[part];
		jogos[part] = aux;
	}

	
	public Jogo [] quickSortVendas (Jogo jogos[] , int inicio , int fim) {
		int part;
		if(inicio < fim) {
			part = particaoDinheiro(jogos , inicio, fim);
			quickSortVendas(jogos ,  inicio , part -1);
			quickSortVendas(jogos , part +1 , fim);
		}
		return jogos;
	}
	
	private int particaoDinheiro (Jogo jogos[] , int inicio , int fim) {
		Jogo pivo = jogos[fim];
		int part = inicio -1;
		
		for(int i = inicio; i < fim; ++i) {
			if(jogos[i].getComprasFeitas() > pivo.getComprasFeitas()) {
				part = part +1;
				trocarDinheiro(jogos , i , part);
			}
			else if (jogos[i].getComprasFeitas() == pivo.getComprasFeitas()) {
				if(jogos[i].dinheiroAdquirido() > pivo.dinheiroAdquirido()) {
					part = part +1;
					trocarDinheiro(jogos , i , part);
				}
			}
		}
		
		part ++;
		trocarDinheiro(jogos , part , fim);
		return (part);
	}
	
	private void trocarDinheiro (Jogo jogos[] , int i , int part) {
		Jogo aux = jogos[i];
		jogos[i] = jogos[part];
		jogos[part] = aux;
	}
}
