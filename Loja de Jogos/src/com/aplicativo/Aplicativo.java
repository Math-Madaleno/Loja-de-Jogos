package com.aplicativo;
import com.leituraescrita.*;
import com.estruturadedados.cliente.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import com.classes.*;
import com.estruturadedados.jogo.*;
import com.loja.*;
public class Aplicativo {
	ListaDeJogos lista = new ListaDeJogos();
	TabelaHash hash;
	Loja loja = new Loja();
	public void inicializar () {
		ArquivoTextoLeitura leituraJogos = new ArquivoTextoLeitura();
		leituraJogos.abrirArquivo("Istim_Jogos2022.txt");
		int quantJogos =0;
		String entradaJogos = "";
		while (leituraJogos.isNotOver()) {
			quantJogos++;
			entradaJogos = leituraJogos.lerArquivo();
			String dadosJogos[] = entradaJogos.split(";");
			String newDouble = dadosJogos[4].replace(",", ".");
			Jogo jogo = new Jogo(Integer.parseInt(dadosJogos[0]) , dadosJogos[1], dadosJogos[2], Integer.parseInt(dadosJogos[3]), Double.parseDouble(newDouble));
		    lista.adicionarFinal(jogo);
		}
		leituraJogos.fecharArquivo();
		
		ArquivoTextoLeitura leituraClientes = new ArquivoTextoLeitura();
		leituraClientes.abrirArquivo("Istim_Clientes2022.txt");
		int quantClientes= leituraClientes.getQuantLinhas();
		hash = new TabelaHash(quantClientes);
				
		while(leituraClientes.isNotOver()) {
			String	entradaClientes = leituraClientes.lerArquivo();
			String dadosCliente [] = entradaClientes.split(";");
			Cliente cliente = new Cliente(dadosCliente[0] , dadosCliente[1]);
			hash.adicionar(dadosCliente[1], cliente);
		}
		leituraClientes.fecharArquivo();
	
		
		ArquivoTextoLeitura leituraCompras = new ArquivoTextoLeitura();
		leituraCompras.abrirArquivo("Istim_Compras2022.txt");
		while(leituraCompras.isNotOver()){
			String entradaCompras = leituraCompras.lerArquivo();
			String dadosCompra [] = entradaCompras.split(";");
			Cliente comprador = hash.retornarCliente(dadosCompra[0]);
			Jogo jogoComprado = lista.retornarJogo(Integer.parseInt(dadosCompra[1]));
			String dataDeCompra = dadosCompra[2];
			double avaliacao = Double.parseDouble(dadosCompra[3]);
			Compra compra = new Compra(comprador , jogoComprado , dataDeCompra , avaliacao);
			loja.registrarCompra(comprador , jogoComprado , dataDeCompra , avaliacao);
		}
	}

	
	public void menuPrincipal(int opcao) {
		Scanner entradaNumerica = new Scanner(System.in);
		switch (opcao) {
		case 1:
			String menuJogos ="1 - Adicionar Jogo \n"
							+ "2 - Buscar Jogo \n"
							+ "3 - Excluir Jogo \n"
							+ "4 - Mostrar Lista (Ordem de Inserção)\n"
							+ "5 - Mostrar Lista por Plataforma \n"
							+ "6 - Mostrar Lista (Ordem de Lançamento)\n"
							+ "7 - Encerrar Sistema de Jogos\n";
			int opcaoJogo =0;
			while(opcaoJogo !=7) {
				System.out.println(menuJogos);
				opcaoJogo = entradaNumerica.nextInt();
				sistemaDeJogos(opcaoJogo);
			}
		break;
		case 2:
			String menuClientes = "1 - Adicionar Cliente \n"
								+ "2 - Buscar Cliente \n"
								+ "3 - Excluir Cliente \n"
								+ "4 - Mostrar Lista de Clientes\n"
								+ "5 - Encerrar Sistema De Clientes";
			int opcaoCliente = 0;
			while(opcaoCliente != 5) {
				System.out.println(menuClientes);
				opcaoCliente = entradaNumerica.nextInt();
				sistemaDeClientes(opcaoCliente);
			}
			
		break;
		case 3:
			sistemaDeLoginCliente();
		break;
		case 4 :
			String menuLojaProprietario = "1 - Mostar Cliente que Mais Comprou (Dinheiro Gasto) \n"
										+ "2 - Mostrar a media de Avaliação de um Jogo \n"
										+ "3 - Mostrar Lista dos 10 Jogos mais vendidos (Quantidade) \n"
										+ "4 - Encerrar Sistema \n";
			int opcaoProprietario =0;
			
			while (opcaoProprietario != 4) {
				System.out.println(menuLojaProprietario);
				opcaoProprietario = entradaNumerica.nextInt();
				lojaProprietario(opcaoProprietario);
			}
			
		break;
		case 5:
			ArquivoTextoEscrita escritaJogos = new ArquivoTextoEscrita();
			escritaJogos.criarArquivo("novaBaseJogos.txt");
			escritaJogos.escrever(lista.mostrarLista());
			escritaJogos.fecharArquivo();
			ArquivoTextoEscrita escritaClientes = new ArquivoTextoEscrita();
			escritaClientes.criarArquivo("novaBaseClientes.txt");
			escritaClientes.escrever(hash.mostrarTabela());
			escritaClientes.fecharArquivo();
			ArquivoTextoEscrita escritaCompras = new ArquivoTextoEscrita();
			escritaCompras.criarArquivo("novaBaseCompras.txt");
			escritaCompras.escrever(hash.mostrarFilaDeComprasClientes());
			escritaCompras.fecharArquivo();
			System.out.println("Sistema Encerrado \n" + "Novos Arquivos Foram Criados");

		break;
		default:
			System.out.println("Opção Inválida");
		break;
		}
	}
	private void sistemaDeJogos (int opcao) {
		Scanner entradaNumerica = new Scanner(System.in);
		Scanner entradaTextual  = new Scanner(System.in);
		switch (opcao) {
		case 1:
			String vetor[] = {"Identificador : " , "Nome do Jogo : " , "Plataforma : ", "Ano de Lançamento : " , "Preço do Jogo : "};
			String valoresJogo = "";
			for(int i =0; i < vetor.length; ++i) {
				System.out.print(vetor[i]);
				valoresJogo += entradaTextual.nextLine() + ";";
			}
			String dadosJogos [] = valoresJogo.split(";");
			Jogo newJogo = new Jogo(Integer.parseInt(dadosJogos[0]) , dadosJogos[1], dadosJogos[2], Integer.parseInt(dadosJogos[3]), Double.parseDouble(dadosJogos[4]));
			lista.adicionarFinal(newJogo);
			System.out.println("Jogo Adicionado");
		break;
		case 2:
			System.out.println("Qual o Identificador do Jogo a ser Buscado : ");
			int parametroBusca = entradaNumerica.nextInt();
			Jogo jogoBuscado = lista.retornarJogo(parametroBusca);
			if(jogoBuscado != null) {
				System.out.println("Jogo Encontrado : ");
				System.out.println(jogoBuscado.toString());
			}
			else {
				System.out.println("Jogo Inexistente na Lista");
			}
		break;
		case 3:
			System.out.println("Qual o Identificador do Jogo a ser Excluído : ");
			parametroBusca = entradaNumerica.nextInt();
			Jogo jogoExcluido = lista.excluir(parametroBusca);
			if(jogoExcluido != null) {
				System.out.println("Jogo Excluido : ");
				System.out.println(jogoExcluido.toString());
			}
			else {
				System.out.println("Jogo Inexistente na Lista");
			}

		break;
		case 4 :
			System.out.println("Lista de Jogos por Ordem de Inserção :");
			System.out.println(lista.mostrarLista());
		break;
		case 5:
			System.out.println("Qual plataforma deseja filtrar os jogos a serem mostrados ? ");
			String plataformaBuscada = entradaTextual.nextLine();
			System.out.println(lista.mostrarListaPlataforma(plataformaBuscada));
			break;
		
		case 6 :
			System.out.println("Lista de Jogos por Ordem de Lançamento : ");
			System.out.println(lista.mostrarListaOrdenada());
		break;
		case 7 :
			System.out.println("Sistema de Jogos Finalizado");
		break;
		}
	}
	
	private void sistemaDeClientes (int opcao) {
		Scanner in = new Scanner(System.in);
		switch (opcao) {
		case 1:
			System.out.println("Qual o nome do Cliente a ser Adicionado ? ");
			String nomeClienteAdicionado = in.nextLine();
			System.out.println("Qual o nick do Cliente a ser Adicionado ? ");
			String nickClienteAdicionado = in.nextLine();
			if(hash.veririficarChave(nickClienteAdicionado)) {
				Cliente cliente = new Cliente(nomeClienteAdicionado , nickClienteAdicionado);
				hash.adicionar(nickClienteAdicionado, cliente);
				System.out.println("Cliente Adicionado");
			}
			else {
				System.out.println("Nickname do Usuário é Inválido");
			}
		
		break;
		case 2:
			System.out.println("Qual o nome do Cliente a ser Localizado ? ");
			String nickCLienteLocalizado = in.nextLine();
			if(hash.veririficarChave(nickCLienteLocalizado)) {
				Cliente clienteLocalizado = hash.retornarCliente(nickCLienteLocalizado);
				if(clienteLocalizado != null) {
					System.out.println("Cliente Localizado : \n" + clienteLocalizado.toString());
				}
				else {
					System.out.println("Cliente Solicitado não existe");
				}
			}
			else {
				System.out.println("Chave Digitada Inválida");
			}
	
		break;
		case 3:
			System.out.println("Qual o nome do Cliente a ser Excluido ? ");
			String nickCLienteExcluido = in.nextLine();
			Cliente clienteExcluido = hash.excluir(nickCLienteExcluido);
			if(clienteExcluido != null) {
				System.out.println("Cliente Excluido : \n" + clienteExcluido.toString());
			}
			else {
				System.out.println("Cliente Solicitado não existe");
			}
		break;
		case 4:
			System.out.println("Lista de Clientes : ");
			System.out.println(hash.mostrarTabela());
			System.out.println("Clientes Adicionados : " + hash.getClientesAdicionados());
			System.out.println("Numero Totais de Colisões : " + hash.getTotalDeColisoes());
			System.out.println("Numero Máximo de Colisões Por Cliente : " + hash.getColisoesMaxCliente());
			
		break;
		case 5 :
			System.out.println("Sistema de Clientes Finalizados");
		break;
		default :
			System.out.println("Opção Inválida");
		break;
		}
	}
	private void sistemaDeLoginCliente () {
		Scanner entradaNumerica = new Scanner(System.in);
		Scanner entradaTextual = new Scanner(System.in);
		System.out.println("Digite o Nickname para realizar o Login");
		String nickEntrada = entradaTextual.nextLine();
		if(hash.veririficarChave(nickEntrada)) {
			Cliente usuario = hash.retornarCliente(nickEntrada);
			String menuLojaCliente ="1 - Realizar Compra \n"
					+ "2 - Desfazer Compra \n"
					+ "3 - Mostrar Lista de Compras \n"
					+ "4 - Mudar Avaliação de um Jogo \n"
					+ "5 - Visualizar Total Gasto \n"
					+ "6 - Pesquisar Jogos por Plataforma\n"
					+ "7 - Pesquisar Jogos por Nome \n"
					+ "8 - Fazer Logoff\n";
			int resposta =0;
			if(usuario != null) {	
				while(resposta != 8) {
					System.out.println("O que " +  usuario.getNickname() + " deseja fazer ?");
					System.out.println(menuLojaCliente);
					resposta = entradaNumerica.nextInt();
					lojaCliente(usuario , resposta);
				}
				
			}
			else {
				System.out.println("Usuário Inexistente na Tabela");
			}
		}
		else {
			System.out.println("Chave Digitada Inválida");
		}

	}
	
	private void lojaCliente (Cliente usuario, int opcao) {
		Scanner entradaNumerica = new Scanner(System.in);
		Scanner entradaTextual = new Scanner(System.in);
		switch (opcao) {
		case 1 : 
			System.out.println("Qual o Identificador do Jogo que deseja comprar ? ");
			int identificador = entradaNumerica.nextInt();
			Jogo jogoComprado = lista.retornarJogo(identificador);
			if(jogoComprado != null) {
				System.out.println("Qual a avaliação que deseja dar para o Jogo ? \n" + "Avaliação tem que ficar entre 0 e 5");
				int avaliacao = entradaNumerica.nextInt();		
				Calendar calendario = Calendar.getInstance();
				String dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(calendario.getTime());
				if(avaliacao >=0 && avaliacao <=5) {
					if(loja.registrarCompra(usuario , jogoComprado , dataAtual , avaliacao)) {
						System.out.println("Compra Realizada com Sucesso");
					}
					else {
						System.out.println("Compra não Realizada");
					}
				}
				else {
					System.out.println("Avaliação que foi dada não cumpre os parâmetros : " + avaliacao);
				}

			}
			else {
				System.out.println("Jogo Solicitado Inexistente");
			}
		break;
		case 2 :
			if(!usuario.listaDeComprasVazia()) {
				System.out.println("Qual o identificador do Jogo que deseja desfazer a compra ?");
				identificador = entradaNumerica.nextInt();
				Jogo jogoDesfeito = lista.retornarJogo(identificador);
				if(jogoDesfeito != null) {
					if (loja.desfazerCompra(usuario, jogoDesfeito)) {
						System.out.println("Compra desfeita");
					}
					else {
						System.out.println("Jogo solicitado não existe na Lista de Compras do Usuário");
					}
				}
				else {
					System.out.println("Jogo Solicitado Inexistente");
				}
			}
			else {
					System.out.println(usuario.getNickname() + " não possui compras");
			}
		break;
		case 3:
			String listaDeCompras = usuario.mostrarListaDeCompras();
			if(listaDeCompras != null) {
				System.out.println("Lista de Compras de " + usuario.getNickname() + " : ");
				System.out.println(usuario.mostrarListaDeCompras());

			}
			else {
				System.out.println(usuario.getNickname() + " não possui compras");
			}
		break;
		case 4 :
			if(!usuario.listaDeComprasVazia()) {
				System.out.println("Qual o Identificador do jogo que deseja alterar a avaliação ? ");
				identificador = entradaNumerica.nextInt();
				Jogo jogoAlterado = lista.retornarJogo(identificador);
				if(jogoAlterado != null) {
					if(usuario.mudarAvaliacao(jogoAlterado)) {
						System.out.println("Nota de Avaliação Alterada");
					}
					else {
						System.out.println("Nota de Avaliação não Alterada");
					}
					
				}
				else {
					System.out.println("Jogo Solicitado Inexistente");
				}
			}
			else {
				System.out.println(usuario.getNickname() + " não possui nenhuma Compra");
			}
			
		break;
		case 5:
			System.out.println("O Total gasto por " + usuario.getNickname() + " foi :" + " R$ " + usuario.dinheiroGasto() + "\n");
		break;
		case 6 :
			System.out.println("Qual plataforma deseja filtrar os jogos ? ");
			String plataformaBuscada = entradaTextual.nextLine();
			System.out.println(lista.mostrarListaPlataforma(plataformaBuscada));
		break;
		case 7 :
			System.out.println("Quais jogos deseja buscar por nome ? ");
			String nomeBuscado = entradaTextual.nextLine();
			System.out.println(lista.mostrarListaPorNome(nomeBuscado));
		break;
		case 8 :
			System.out.println("Usuário " + usuario.getNickname() + " deslogado");
		break;
		default:
			System.out.println("Opção Solicitada Inválida");
		break;
		}
	}
	private void lojaProprietario (int opcao) {
		Scanner entradaNumerica = new Scanner(System.in);
		switch (opcao) {
		case 1:
			Cliente clienteMaisComprou = loja.clienteMaisComprou();
			System.out.println("O Cliente que mais Gastou na Loja é o " + clienteMaisComprou.getNickname() + "\n" + clienteMaisComprou.getNickname() + " gastou R$" + clienteMaisComprou.dinheiroGasto());
		break;
		case 2:
			System.out.println("Qual o identificador do jogo que deseja ver a média de Avaliação ? ");
			int identificador = entradaNumerica.nextInt();
			Jogo jogoIdentificado = lista.retornarJogo(identificador);
			if(jogoIdentificado != null) {
				System.out.println("A média de Avaliação do Jogo " + jogoIdentificado.getNomeJogo() + " é : " + jogoIdentificado.mediaDeAvaliacao());
			}
			else {
				System.out.println("Jogo Solicitado Inexistente");
			}
		break;
		case 3 :
			System.out.println(loja.jogosMaisVendidos(lista));
		break;
		case 4 :
			System.out.println("Sistema de Loja do Proprietário Encerrado");
		break;
		default:
			System.out.println("Opção Selecionada Inválida");
		break;
		}
	}
}
