package com.main;
import com.aplicativo.*;
import java.util.*;
public class Main {

	public static void main(String[] args) {
		Aplicativo app = new Aplicativo();
		Scanner in = new Scanner(System.in);
		app.inicializar();
		int opcao =0;
		while(opcao != 5) {
			System.out.println("1 - Controle de Jogos \n"
							 + "2 - Controle de Clientes \n"
							 + "3 - Loja de Jogos Cliente\n"
							 + "4 - Loja de Jogos Proprietário \n"
							 + "5 - Encerrar Sistema \n");
			opcao = in.nextInt();
			app.menuPrincipal(opcao);
		}
	}

}
