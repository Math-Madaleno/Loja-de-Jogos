package com.leituraescrita;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.*;
public class ArquivoTextoLeitura {
	private Scanner entrada;
	private int quantLinhasArquivo;
	public void abrirArquivo (String nomeArquivo) {
		try {
			entrada = new Scanner(new File(nomeArquivo));
			LineNumberReader lnr = new LineNumberReader(new FileReader(nomeArquivo));
			lnr.skip(Long.MAX_VALUE);
			this.quantLinhasArquivo = lnr.getLineNumber();
		}
		catch (FileNotFoundException exception) {
			System.out.println("Arquivo não Localizado");
		}
		catch (IOException exception) {
			System.out.println("Erro do tipo : " + exception.getMessage());
		}
		catch (Exception exception) {
			System.out.println("Erro do tipo : " + exception.getMessage());
		}
	}
	
	public boolean isNotOver () {
		return entrada.hasNext();
	}
	public int getQuantLinhas () {
		return this.quantLinhasArquivo;
	}
	public String lerArquivo () {
		String txt = "";
		txt = entrada.nextLine();
		return txt;
	}
	public void fecharArquivo () {
		try {
			entrada.close();
		}
		catch (Exception exception) {
			System.out.println("Erro do tipo : " + exception.getMessage());
		}
	}
}
