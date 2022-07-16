package com.estruturadedados.compra;
import com.classes.*;

public class ListaDeCompras {
	private Elemento primeiro;
	private Elemento ultimo;
	private int comprasRealizadas;
	
	public ListaDeCompras () {
		this.primeiro = new Elemento(null);
		ultimo = primeiro;
		this.comprasRealizadas =0;
	}
	public boolean isEmpty() {
		return primeiro == ultimo;
	}
	public void enfileirar (Compra compra) {
		Elemento novoElemento = new Elemento(compra);
		ultimo.setProximo(novoElemento);
		novoElemento.setAnterior(ultimo);
		ultimo = novoElemento;
		this.comprasRealizadas++;
	}
	public boolean verificaExistenciaJogo (Jogo jogo) {
		return buscarElemento(jogo) != null;
	}
	public boolean verificaExistenciaCliente(Cliente cliente) {
		return buscarElemento(cliente) != null;
	}
	public Compra retornarCompra (Jogo jogoBuscado) {
		Elemento elementoEncontrado = buscarElemento(jogoBuscado);
		if(elementoEncontrado != null) {
			Compra compraEncontrada = elementoEncontrado.getCompra();
			return compraEncontrada;
		}
		return null;
	}
	public Compra desfazerCompra (Jogo jogoDesfeito) {
		if(!isEmpty()) {
			Elemento elementoExcluido = buscarElemento(jogoDesfeito);
			if(elementoExcluido != null) {
				if(elementoExcluido != ultimo) {
					Elemento elementoAnterior = elementoExcluido.getAnterior();
					Elemento proximoElemento = elementoExcluido.getProximo();
					elementoAnterior.setProximo(proximoElemento);
					proximoElemento.setAnterior(elementoAnterior);
					elementoExcluido.setAnterior(null);
					elementoExcluido.setProximo(null);
				}
				else {
					Elemento elementoAnterior = elementoExcluido.getAnterior();
					elementoAnterior.setProximo(null);
					ultimo = elementoAnterior;
					elementoExcluido.setAnterior(null);
					elementoExcluido.setProximo(null);			
				}
				Compra compraDesfeita = elementoExcluido.getCompra();
				this.comprasRealizadas --;
				return compraDesfeita;
			}
		}
		return null;
	}
	public Compra desfazerCompra (Cliente cliente) {
		if(!isEmpty()) {
			Elemento elementoExcluido = buscarElemento(cliente);
			if(elementoExcluido != null) {
				if(elementoExcluido != ultimo) {
					Elemento elementoAnterior = elementoExcluido.getAnterior();
					Elemento proximoElemento = elementoExcluido.getProximo();
					elementoAnterior.setProximo(proximoElemento);
					proximoElemento.setAnterior(elementoAnterior);
					elementoExcluido.setAnterior(null);
					elementoExcluido.setProximo(null);
				}
				else {
					Elemento elementoAnterior = elementoExcluido.getAnterior();
					elementoAnterior.setProximo(null);
					ultimo = elementoAnterior;
					elementoExcluido.setAnterior(null);
					elementoExcluido.setProximo(null);
				}
				Compra compraDesfeita = elementoExcluido.getCompra();
				this.comprasRealizadas --;
				return compraDesfeita;
			}
		}
		return null;
	}
	
	public String mostrarFila() {
		StringBuilder txt = new StringBuilder();
		Elemento aux = primeiro.getProximo();
		while(aux != null) {
			txt.append(aux.getCompra().toString() + "\n");
			aux = aux.getProximo();
		}
		return txt.toString();
	}
	public double mostrarMedia() {
		double soma =0;
		Elemento aux = primeiro.getProximo();
		while(aux != null) {
			soma+= aux.getCompra().getAvaliacao();
			aux = aux.getProximo();
		}
		double media = soma/this.comprasRealizadas;
		return media;
	}
	private Elemento buscarElemento (Jogo jogo) {
		Elemento aux = primeiro.getProximo();
		while(aux != null){
			if(aux.getCompra().getJogoComprado() == jogo) {
				return aux;
			}
			aux = aux.getProximo();
		}
		return null;
	}
	private Elemento buscarElemento (Cliente cliente) {
		Elemento aux = primeiro.getProximo();
		while(aux != null){
			if(aux.getCompra().getComprador() == cliente) {
				return aux;
			}
			aux = aux.getProximo();
		}
		return null;
	}
	public int getComprasRealizadas () {
		return this.comprasRealizadas;
	}
	
	public double dinheiro () {
		double soma =0;
		Elemento aux = primeiro.getProximo();
		while(aux != null) {
			soma += aux.getCompra().getJogoComprado().getPreco();
			aux = aux.getProximo();
		}
		return soma;
	}
	
}
