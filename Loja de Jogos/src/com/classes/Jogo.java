package com.classes;
import com.estruturadedados.compra.*;
public class Jogo {
	private int idJogo;
	private String nomeJogo;
	private String plataforma;
	private int anoLancamento;
	private double preco;
	private ListaDeCompras lista;
	private int comprasFeitas;	
	public Jogo(int idJogo , String nomeJogo , String plataforma , int anoLancamento , double preco) {
		this.idJogo = idJogo;
		this.nomeJogo = nomeJogo;
		this.plataforma = plataforma;
		this.anoLancamento= anoLancamento;
		this.preco= preco;
		this.lista = new ListaDeCompras();
		this.comprasFeitas =0;
	}
	public int getIdJogo() {
		return idJogo;
	}
	public void setIdJogo(int idJogo) {
		this.idJogo = idJogo;
	}
	public String getNomeJogo() {
		return nomeJogo;
	}
	public void setNomeJogo(String nomeJogo) {
		this.nomeJogo = nomeJogo;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	public int getAnoLancamento() {
		return anoLancamento;
	}
	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public boolean registrarCompra(Compra compra) {
		lista.enfileirar(compra);
		return true;
	}
	public boolean reiscindirCompra(Cliente usuario) {
		if(lista.verificaExistenciaCliente(usuario)) {
			Compra compraDesfeita =  lista.desfazerCompra(usuario);
			return true;
		}
		return false;
	}
	public int getComprasFeitas () {
		return lista.getComprasRealizadas();
	}
	public double dinheiroAdquirido () {
		return lista.dinheiro();
	}
	public double mediaDeAvaliacao () {
		double media = lista.mostrarMedia();
		return media;
	}
	public String toString() {
		return "Identificador : " + this.idJogo + " - " + "Nome : " + this.nomeJogo + " - " + "Plataforma : " + this.plataforma + " - " + "Ano de Lançamento : " + this.anoLancamento + " - " + "Preço : R$" +  this.preco;
	}
	

}
