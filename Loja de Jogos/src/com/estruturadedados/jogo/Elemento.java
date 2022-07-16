package com.estruturadedados.jogo;
import com.classes.*;
public class Elemento {
	private Elemento anterior;
	private Elemento proximo;
	private Jogo valor;
	public Elemento (Jogo newJogo) {
		this.valor = newJogo;
	}
	public Elemento getAnterior() {
		return anterior;
	}
	public void setAnterior(Elemento anterior) {
		this.anterior = anterior;
	}
	public Elemento getProximo() {
		return proximo;
	}
	public void setProximo(Elemento proximo) {
		this.proximo = proximo;
	}
	public Jogo getValor() {
		return valor;
	}
	public void setValor(Jogo valor) {
		this.valor = valor;
	}
	
}
