package com.loja;
import com.classes.*;
import com.estruturadedados.compra.*;
import com.estruturadedados.jogo.*;
public class Loja {
	private Cliente clienteMaisComprou;
	private int totaisComprasFeitas;
	private Jogo jogos[] = new Jogo[10];
	public Loja () {
		this.clienteMaisComprou = new Cliente("" , "");
		this.totaisComprasFeitas =0;
	}
	public boolean registrarCompra (Cliente cliente , Jogo jogo , String dataAtual,  double avaliacaoJogo) {
		Compra newCompra = new Compra(cliente , jogo , dataAtual , avaliacaoJogo);
		if( cliente.comprar(newCompra) && jogo.registrarCompra(newCompra) ) {
			atualizarClienteMaisComprou(cliente);
			return true;
		}
		return false;
	}

	
	public boolean desfazerCompra(Cliente usuario , Jogo jogoDesfeito) {	
		if(usuario.desfazerCompra(jogoDesfeito) && jogoDesfeito.reiscindirCompra(usuario)) {
			return true;
		}
		return false;
	}
	
	private void atualizarClienteMaisComprou (Cliente novoCliente) {
		if(novoCliente.dinheiroGasto() > clienteMaisComprou.dinheiroGasto()) {
			this.clienteMaisComprou = novoCliente;
		}
	}
	public int getTotaisCompraFeitas() {
		return this.totaisComprasFeitas;
	}
	public Cliente clienteMaisComprou () {
		return this.clienteMaisComprou;
	}
	public String jogosMaisVendidos (ListaDeJogos lista) {
		return "Lista dos 10 Jogos mais Vendidos :\n" + lista.mostrarJogosMaisVendidos() ;
	}
}
