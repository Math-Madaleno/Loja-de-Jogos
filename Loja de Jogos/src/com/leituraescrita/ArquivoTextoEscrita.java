package com.leituraescrita;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class ArquivoTextoEscrita {
	private BufferedWriter saida;
	
	public void criarArquivo (String nomeArquivo) {
		try {
			saida = new BufferedWriter(new FileWriter(nomeArquivo));
		}
		catch (FileNotFoundException exception) {
			System.out.println("Arquivo não aberto ou localizado");
		}
		catch (Exception exception) {
			System.out.println("Erro do tipo : " + exception.getMessage());
		}
	}
	public void escrever (String entrada) {
		try {
			saida.write(entrada);
		}
		catch (FileNotFoundException e) {
			System.out.println("Erro do tipo : " + e.getLocalizedMessage()+ "\n Arquivo não Encontrado");
		}
		catch (IOException e) {
			System.out.println("Erro do tipo : " + e.getLocalizedMessage());
		}
		catch (Exception e) {
			System.out.println("Erro do tipo : " + e.getLocalizedMessage());

		}
	}
	public void fecharArquivo () {
		try {
			saida.close();
		}
		catch (FileNotFoundException exception) {
			System.out.println("Arquivo não aberto ou localizado");
		}
		catch (Exception exception) {
			System.out.println("Erro do tipo : " + exception.getMessage());
		}
	}
}
