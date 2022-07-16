package com.classes;
import com.estruturadedados.compra.*;
import java.util.*;
public class Cliente {
	private String nome;
	private String nickname;
	private ListaDeCompras lista;
	private double dinheiroGasto;
	public Cliente (String nome, String nickname) {
		this.nome = nome;
		this.nickname = nickname;
		this.lista = new ListaDeCompras();
		this.dinheiroGasto = 0;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public boolean comprar (Compra compra) {
		Jogo jogo = compra.getJogoComprado();
		lista.enfileirar(compra);
		return true;
		
	}
	public boolean desfazerCompra(Jogo jogoDesfeito) {
		if(lista.verificaExistenciaJogo(jogoDesfeito)) {
			Compra compraDesfeita = lista.desfazerCompra(jogoDesfeito);
			return true;
		}
		return false;
	}
	public boolean mudarAvaliacao (Jogo jogoASerAlterado) {
		
		Scanner in = new Scanner(System.in);
		Compra compraRealizada = lista.retornarCompra(jogoASerAlterado);
		if(compraRealizada != null) {
			System.out.println("Qual será a nova avaliação do Jogo : " + jogoASerAlterado.getNomeJogo());
			double novaAvaliacao = in.nextDouble();
			if(novaAvaliacao >0 && novaAvaliacao <= 5) {
				compraRealizada.setAvaliacao(novaAvaliacao);
				return true;
			}
			else {
				System.out.println("A nota de Avaliação deve ficar entre 0 e 5");
				mudarAvaliacao(jogoASerAlterado);
			}
		}
		else {
			System.out.println("Jogo Solicitado não Existe na Lista de Compras");
		}
		return false;

	}
	public String mostrarListaDeCompras () {
		if(!lista.isEmpty()) {
			return lista.mostrarFila();
		}
		else {
			return null;
		}
	}
	
	public boolean listaDeComprasVazia () {
		return lista.isEmpty();
	}
	public int comprasFeitas() {
		int comprasFeitas = lista.getComprasRealizadas();
		return comprasFeitas;
	}
	public double dinheiroGasto () {
		return lista.dinheiro();
	}
	
	public String toString () {
		return "Nome : " + this.nome + " - " + "Nickname : " + this.nickname;
	}
}
