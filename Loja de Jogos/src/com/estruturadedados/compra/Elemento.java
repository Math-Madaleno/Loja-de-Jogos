package com.estruturadedados.compra;
import com.classes.*;
public class Elemento {
	private Elemento anterior;
	private Elemento proximo;
	private Compra compra;
	
	public Elemento (Compra compra) {
		this.anterior = null;
		this.proximo = null;
		this.compra = compra;
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

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	
}
