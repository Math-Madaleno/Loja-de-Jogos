package com.estruturadedados.jogo;
import com.classes.*;
import com.ordenacao.*;
public class ListaDeJogos {
	private Elemento primeiro;
	private Elemento ultimo;
	private int quantJogos;
	public ListaDeJogos () {
		ultimo = new Elemento(null);
		primeiro = ultimo;
		this.quantJogos =0;
	}
	public boolean isEmpty () {
		return primeiro == ultimo;
	}
	public void adicionarFinal (Jogo newJogo) {
		
		Elemento novoElemento = new Elemento(newJogo);
		novoElemento.setAnterior(ultimo);
		novoElemento.setProximo(null);
		ultimo.setProximo(novoElemento);
		ultimo = novoElemento;
		this.quantJogos++;
		
	}
	public void adicionarInicio (Jogo newJogo) {
		Elemento novoElemento = new Elemento(newJogo);
		novoElemento.setProximo(primeiro.getProximo());
		novoElemento.setAnterior(primeiro);
		primeiro.setProximo(novoElemento);
		this.quantJogos++;
	}
	
	public Jogo excluir (int idJogo) {
		Elemento elementoEncontrado = buscarElemento(idJogo);
		if(elementoEncontrado != null) {
			Jogo jogoExcluido = elementoEncontrado.getValor();
			if(elementoEncontrado != ultimo) {
				Elemento elementoAnterior = elementoEncontrado.getAnterior();
				Elemento proximoElemento = elementoEncontrado.getProximo();
				elementoAnterior.setProximo(proximoElemento);
				proximoElemento.setAnterior(elementoAnterior);
				elementoEncontrado.setAnterior(null);
				elementoEncontrado.setProximo(null);
				elementoEncontrado.setValor(null); 
			}
			else {
				Elemento elementoAnterior = elementoEncontrado.getAnterior();
				elementoAnterior.setProximo(null);
				ultimo = elementoAnterior;
				elementoEncontrado.setAnterior(null);
				elementoEncontrado.setProximo(null);
				elementoEncontrado.setValor(null); 
			}
			this.quantJogos--;
			return jogoExcluido;
		}
		return null;
	}
	
	public Jogo retornarJogo(int idJogo) {
		Elemento elementoEncontrado = buscarElemento(idJogo);
		if(elementoEncontrado != null) {
			Jogo jogoEncontrado = elementoEncontrado.getValor();
			return jogoEncontrado;
		}
		return null;
	}

	private Elemento buscarElemento (int idJogo) {
		Elemento aux = primeiro.getProximo();
		while (aux != null) {
			if(aux.getValor().getIdJogo() == idJogo) {
				return aux;
			}
			else {
				aux = aux.getProximo();
			}
		}
		return null;
	}
	
	public String mostrarLista () {
		StringBuilder txt = new StringBuilder();
		Elemento aux = primeiro.getProximo();
		while(aux != null) {
			txt.append(aux.getValor().toString() + "\n");
			aux = aux.getProximo();
		}
		return txt.toString();
	} 
	
	public String mostrarListaPlataforma (String plataforma) {
		StringBuilder txt = new StringBuilder();
		Elemento aux = primeiro.getProximo();
		String newPlataforma = plataforma.toUpperCase();
		while(aux != null) {
			String plataformaJogo = aux.getValor().getPlataforma().toUpperCase();
			if(plataformaJogo.equals(newPlataforma)) {
				txt.append(aux.getValor().toString()+ "\n");
			}
			aux = aux.getProximo();
		}
		return txt.toString();
	}
	public String mostrarListaPorNome(String query) {
		StringBuilder txt = new StringBuilder();
		Elemento aux = primeiro.getProximo();
		while (aux != null) {
			if(aux.getValor().getNomeJogo().equals(query)) {
				txt.append(aux.getValor().toString());
			}
			aux = aux.getProximo();
		}
		return txt.toString();
	}
	public String mostrarListaOrdenada () {
		StringBuilder txt = new StringBuilder();
		Jogo jogos [] = new Jogo[quantJogos];
		Elemento aux = primeiro.getProximo();
		int posicao =0;
		while(aux != null) {
			jogos[posicao] = aux.getValor();
			posicao ++;
			aux = aux.getProximo();
		}
		Ordenacao ordenacao = new Ordenacao();
		 jogos = ordenacao.quickSortAnoLancamento(jogos, 0 , jogos.length-1);
		for(int i =0; i < jogos.length; ++i) {
			txt.append(jogos[i].toString() + "\n");
		}
		return txt.toString();
	}
	public String mostrarJogosMaisVendidos () {
		StringBuilder txt = new StringBuilder();
		Jogo jogos [] = new Jogo[quantJogos];
		Elemento aux = primeiro.getProximo();
		int posicao =0;
		while(aux != null) {
			jogos[posicao] = aux.getValor();
			posicao ++;
			aux = aux.getProximo();
		}
		Ordenacao ordenacao = new Ordenacao();
		jogos = ordenacao.quickSortVendas(jogos, 0, jogos.length-1);
		for(int i =0; i < 10; ++i) {
			txt.append(i + " - " + jogos[i].toString());
			txt.append("\n Número de Vendas : " + jogos[i].getComprasFeitas());
			txt.append("\n-------------------------------------------------------------------------------------- \n");
		}
		return txt.toString();
	}
	
}
