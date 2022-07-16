package com.estruturadedados.cliente;
import com.classes.*;
public class TabelaHash {
	private int clientesAdicionados;
	private Cliente clientes[];
	private int tamanhoFinal;
	private int totalDeColisoes;
	private int maxColisoesPorCliente;
	private int colisoesFeitas;
	
	public TabelaHash (int quantClientes) {
		this.clientesAdicionados =0;
		this.tamanhoFinal = quantClientes*3;
		this.clientes = new Cliente[this.tamanhoFinal];
		this.maxColisoesPorCliente =0;
	}
	public void adicionar (String chave, Cliente cliente) {
		int posicao = mapeamentoDeAdicao(chave, 0);
		if (posicao !=-1) {
			clientes[posicao] = cliente;
			this.clientesAdicionados++;
			verificarColisoes();
		}
	}
	private void verificarColisoes() {
		if (colisoesFeitas > maxColisoesPorCliente) {
			maxColisoesPorCliente = colisoesFeitas;
			colisoesFeitas =0;
		}
	}
	
	
	public Cliente retornarCliente (String chave) {
		int posicaoBuscada = mapeamentoDeBusca(chave , 0);
		if(posicaoBuscada != -1) {
			Cliente clienteLocalizado = clientes[posicaoBuscada];
			this.colisoesFeitas = 0;
			return clienteLocalizado;
		}
		else {
			this.colisoesFeitas = 0;
			return null;
		}
		
	}
	public Cliente excluir (String chave) {
		int posicaoExcluida = mapeamentoDeBusca(chave , 0);
		if(posicaoExcluida != -1) {
			Cliente clienteExcluido = clientes[posicaoExcluida];
			clientes[posicaoExcluida] = null;
			this.colisoesFeitas = 0;
			this.clientesAdicionados--;
			return clienteExcluido;
		}
		else {
			this.colisoesFeitas = 0;
			return null;
		}
		
	}
	
	public String mostrarTabela () {
		StringBuilder txt = new StringBuilder();
		for(int i =0; i < tamanhoFinal; ++i) {
			if(clientes[i] != null) {
				txt.append(clientes[i].toString() + "\n");
			}
		}
		return txt.toString();
	}
	public String mostrarFilaDeComprasClientes () {
		StringBuilder txt = new StringBuilder();
		for(int i =0; i < tamanhoFinal; ++i) {
			if(clientes[i] != null) {
				String fila = clientes[i].mostrarListaDeCompras();
				if(fila != null) {
					txt.append(fila);

				}
			}
		}
		return txt.toString();
	}
	
	public boolean veririficarChave (String chave) {
		
		String parteEscrita = chave.substring(0, 4);
		String parteNumerica = chave.substring(4);
		boolean validarEscrita = parteEscrita.matches("[a-zA-Z]*");
		boolean validarNumerico = parteNumerica.matches("[0-9]*");
		if (validarEscrita && validarNumerico) {
			return true;
		}
		else if (!validarEscrita && validarNumerico) {
			System.out.println("4 Primeiras Algarismos não sao Letras");
			return false;
		}
		else if (validarEscrita && ! validarNumerico) {
			System.out.println("Os Ultimos Algarismos não sao numéricos");
			return false;
		}
		else {
			System.out.println("O nick não Obedece as condições : ");
			System.out.println("4 Primeiras Algarismos não sao Letras");
			System.out.println("Os Ultimos Algarismos não sao numéricos");
			return false;
		}
		
	}
	
	private String [] separarChaves (String chave) {
		String primeiraChave = "";
		String segundaChave = "";
		for (int i =0; i < 4; ++i) {
			primeiraChave+= chave.charAt(i);
		}
		for (int i = 4; i < chave.length(); ++i) {
			segundaChave+= chave.charAt(i);
		}
		String vetor[] = new String[2];
		vetor[0] = (primeiraChave);
		vetor[1] = (segundaChave);
		return vetor;
	}
	private long calcularParteEscrita(String chave) {
		long soma =0;
		for(int i =0; i < chave.length(); ++i) {
			long valorPosicaoUnicode = (potenciacao(chave.charAt(i) , i));
			soma+= valorPosicaoUnicode;
		}
		return soma;
	}
	
	private long calcularParteNumerica (String chave) {
		long soma =0;
		String newStr= "";
		for(int i =0; i < chave.length(); ++i) {
			newStr += chave.charAt(i);
		}
		soma = Long.parseLong(newStr);
		return soma;
	}
	
	private long funcaoHashZero (String chave) {
		long soma =0;
		for(int i =0; i < chave.length(); ++i) {
			soma+= potenciacao(chave.charAt(i), i+1); 
		}
		return soma;
	}
	
	private long funcaoHashPrimaria (String chaveEscrita , String chaveNumerica) {
		long soma = calcularParteEscrita(chaveEscrita) - calcularParteNumerica(chaveNumerica);
		return soma;
	}
	
	private long funcaoHashSecundaria (String chaveEscrita , String chaveNumerica) {
		long soma = calcularParteEscrita(chaveEscrita) + calcularParteNumerica(chaveNumerica);
		return soma;
	}
	
	private long funcaoHashTerciaria(String chave) {
		long soma =0;
		for(int i =0; i < chave.length(); ++i) {
			soma += chave.charAt(i) * (i+1);
		}
		return soma;
	}

	private long funcaoHashQuartenaria (String chave) {
		long soma=0;
	
		for (int i =0; i < chave.length(); ++i) {
			soma += potenciacao(chave.charAt(i), i);
		}
		return soma;
	}

	private long funcaoHashQuintenaria (String chave) {
		long soma =2;
		for(int i =0; i < chave.length(); ++i) {
			soma*= chave.charAt(i);
			soma-= chave.charAt(i);
		}
		return soma;
	}
	private long funcaoHashSexta (String chave) {
		long soma=1;
		for(int i =0; i < chave.length(); ++i) {
			soma*= chave.charAt(i);
		}
		return soma;
	}
	
	private long funcaoHashSetima(String chave) {
		long soma =0 ;	
		for(int i =0; i <chave.length(); ++i) {
			soma += potenciacao(chave.charAt(i), i+1);
		}	
		return soma;
		
	}
	
	private long funcaoHashOitava(String chave) {
		long soma =1;
		for(int i =0; i < chave.length(); ++i) {
			soma*= chave.charAt(i);
			soma+= chave.charAt(i);
		}
		return soma;
	}
	

	private long potenciacao(int base, int expoente) {
		if(expoente == 0) {
			return 1;
		}
		else {
			return base*potenciacao(base, --expoente);
		}
	}
	private int mapeamentoDeAdicao(String chave , int colisoes) {
		String chaves [] = separarChaves(chave);
		long posicoes[] = new long[12];
		 long posicaoZero 	   =  (funcaoHashZero(chave) %tamanhoFinal)  +colisoes;
		 long primeiraPosicao  = (funcaoHashPrimaria(chaves[0] , chaves[1]) %tamanhoFinal) + colisoes;
		 long segundaPosicao   = (funcaoHashSecundaria(chaves[0] , chaves[1]) %tamanhoFinal) + colisoes;
		 long terceiraPosicao  = (funcaoHashTerciaria(chave)%tamanhoFinal) + colisoes;
		 long quartaPosicao    = (funcaoHashQuartenaria(chave)%tamanhoFinal) + colisoes;
		 long quintaPosicao    = (funcaoHashQuintenaria(chave)%tamanhoFinal )+ colisoes;
		 long sextaPosicao     = (funcaoHashSexta(chave)%tamanhoFinal) + colisoes;
		 long setimaPosicao    = (funcaoHashSetima(chave)%tamanhoFinal) + colisoes;
		 long oitavaPosicao    = (funcaoHashOitava(chave) %tamanhoFinal) + colisoes;
		 long nonaPosicao      = ( ( ( (posicaoZero  * primeiraPosicao)%tamanhoFinal) + ((segundaPosicao * terceiraPosicao)%tamanhoFinal) + ((quartaPosicao * quintaPosicao)%tamanhoFinal) + ((sextaPosicao + setimaPosicao) %tamanhoFinal) + ((setimaPosicao * oitavaPosicao)%tamanhoFinal) ) % tamanhoFinal) + colisoes;
		 long decimaPosicao    = ( ( ( (posicaoZero * oitavaPosicao)%tamanhoFinal) + ((primeiraPosicao * oitavaPosicao) %tamanhoFinal) + ((segundaPosicao * oitavaPosicao) %tamanhoFinal) + ((terceiraPosicao * oitavaPosicao)%tamanhoFinal) + ((quartaPosicao * oitavaPosicao) %tamanhoFinal) + ((quintaPosicao * oitavaPosicao)%tamanhoFinal) + ((sextaPosicao * oitavaPosicao)) + ((setimaPosicao *oitavaPosicao)%tamanhoFinal) ) %tamanhoFinal) + colisoes;
		 long decimaPrimeiraPosicao = ( ( posicaoZero + primeiraPosicao + segundaPosicao + terceiraPosicao + quartaPosicao + quintaPosicao + sextaPosicao + setimaPosicao  + oitavaPosicao) %tamanhoFinal) + colisoes;
		 posicoes[0] = posicaoZero;
		 posicoes[1] = primeiraPosicao;
		 posicoes[2] = segundaPosicao;
		 posicoes[3] = terceiraPosicao;
		 posicoes[4] = quartaPosicao;
		 posicoes[5] = quintaPosicao;
		 posicoes[6] = sextaPosicao;
		 posicoes[7] = setimaPosicao;
		 posicoes[8] = oitavaPosicao;
		 posicoes[9] = nonaPosicao;
		 posicoes[10]= decimaPosicao;
		 posicoes[11] = decimaPrimeiraPosicao;
		
		int posicaoSelecionada=-1;
		boolean condition = false;
		for(int i =0; i < posicoes.length; ++i) {
			if(clientes[(int)posicoes[i]] == null) {
				posicaoSelecionada = (int)posicoes[i];
				condition = true;
				break;
			}
		}
		if(condition) {
			return posicaoSelecionada;
		}
		else {
			this.totalDeColisoes++;
			this.colisoesFeitas++;
			return mapeamentoDeAdicao(chave , colisoes + 1);
		}
		
	}
	private int mapeamentoDeBusca (String chave , int colisoes) {
		String chaves [] = separarChaves(chave);
		long posicoes[] = new long[12];
		 long posicaoZero 	   =  (funcaoHashZero(chave) %tamanhoFinal)  +colisoes;
		 long primeiraPosicao  = (funcaoHashPrimaria(chaves[0] , chaves[1]) %tamanhoFinal) + colisoes;
		 long segundaPosicao   = (funcaoHashSecundaria(chaves[0] , chaves[1]) %tamanhoFinal) + colisoes;
		 long terceiraPosicao  = (funcaoHashTerciaria(chave)%tamanhoFinal) + colisoes;
		 long quartaPosicao    = (funcaoHashQuartenaria(chave)%tamanhoFinal) + colisoes;
		 long quintaPosicao    = (funcaoHashQuintenaria(chave)%tamanhoFinal )+ colisoes;
		 long sextaPosicao     = (funcaoHashSexta(chave)%tamanhoFinal) + colisoes;
		 long setimaPosicao    = (funcaoHashSetima(chave)%tamanhoFinal) + colisoes;
		 long oitavaPosicao    = (funcaoHashOitava(chave) %tamanhoFinal) + colisoes;
		 long nonaPosicao      = ( ( ( (posicaoZero  * primeiraPosicao)%tamanhoFinal) + ((segundaPosicao * terceiraPosicao)%tamanhoFinal) + ((quartaPosicao * quintaPosicao)%tamanhoFinal) + ((sextaPosicao + setimaPosicao) %tamanhoFinal) + ((setimaPosicao * oitavaPosicao)%tamanhoFinal) ) % tamanhoFinal) + colisoes;
		 long decimaPosicao    = ( ( ( (posicaoZero * oitavaPosicao)%tamanhoFinal) + ((primeiraPosicao * oitavaPosicao) %tamanhoFinal) + ((segundaPosicao * oitavaPosicao) %tamanhoFinal) + ((terceiraPosicao * oitavaPosicao)%tamanhoFinal) + ((quartaPosicao * oitavaPosicao) %tamanhoFinal) + ((quintaPosicao * oitavaPosicao)%tamanhoFinal) + ((sextaPosicao * oitavaPosicao)) + ((setimaPosicao *oitavaPosicao)%tamanhoFinal) ) %tamanhoFinal) + colisoes;
		 long decimaPrimeiraPosicao = ( ( posicaoZero + primeiraPosicao + segundaPosicao + terceiraPosicao + quartaPosicao + quintaPosicao + sextaPosicao + setimaPosicao  + oitavaPosicao) %tamanhoFinal) + colisoes;
		 posicoes[0] = posicaoZero;
		 posicoes[1] = primeiraPosicao;
		 posicoes[2] = segundaPosicao;
		 posicoes[3] = terceiraPosicao;
		 posicoes[4] = quartaPosicao;
		 posicoes[5] = quintaPosicao;
		 posicoes[6] = sextaPosicao;
		 posicoes[7] = setimaPosicao;
		 posicoes[8] = oitavaPosicao;
		 posicoes[9] = nonaPosicao;
		 posicoes[10]= decimaPosicao;
		 posicoes[11] = decimaPrimeiraPosicao;
		int posicaoSelecionada=-1;
		boolean condition = false;
		if(this.colisoesFeitas <= this.maxColisoesPorCliente) {
			for(int i =0; i < posicoes.length; ++i) {
				if(clientes[(int)posicoes[i]] != null) {
					if(clientes[(int)posicoes[i]].getNickname().equals(chave)){
						posicaoSelecionada = (int)posicoes[i];
						condition = true;
						break;
					}
					
				}
			}
			if(condition) {
				return posicaoSelecionada;
			}
			else {
				this.totalDeColisoes++;
				this.colisoesFeitas++;
				return mapeamentoDeBusca(chave ,  colisoes +1);
			}
		}
		else {
			return -1;
		}
	
	
	}
	
	public int getTotalDeColisoes() {
		return this.totalDeColisoes;
	}
	public int getColisoesMaxCliente () {
		return this.maxColisoesPorCliente;
	}
	public int getClientesAdicionados () {
		return this.clientesAdicionados;
	}
	
}
