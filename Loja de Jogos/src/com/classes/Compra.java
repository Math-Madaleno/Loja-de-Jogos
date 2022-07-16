package com.classes;

public class Compra {
	private Jogo jogoComprado;
	private Cliente comprador;
	private String dataCompra;
	private double avaliacao;
	
	public Compra (Cliente comprador , Jogo jogoComprado, String dataCompra , double avaliacao) {
		this.comprador = comprador;
		this.jogoComprado = jogoComprado;
		this.dataCompra = dataCompra;
		this.avaliacao = avaliacao;
	}

	public Jogo getJogoComprado() {
		return jogoComprado;
	}

	public void setJogoComprado(Jogo jogoComprado) {
		this.jogoComprado = jogoComprado;
	}

	public Cliente getComprador() {
		return comprador;
	}

	public void setComprador(Cliente comprador) {
		this.comprador = comprador;
	}

	public String getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}

	public double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(double avaliacao) {
		this.avaliacao = avaliacao;
	}
	public String toString () {
		return "Comprador : " + this.comprador.getNickname() + " - " +  "Jogo : " + this.jogoComprado.getIdJogo() + " - " + "Data de Compra : " + this.dataCompra + " - " + " Avaliação : " + this.avaliacao;
	}

}
