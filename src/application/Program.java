package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Produto;

/*
 * c:\\temp\\vendas\\produtos.csv
 */

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o caminho do arquivo: ");		
		String strPath = sc.nextLine();
		
		List<Produto> produtos = new ArrayList<>();
		
		
		try(BufferedReader br = new BufferedReader(new FileReader(strPath))) {
			String linha = br.readLine();
			
			while( linha != null) {
				String[] produto = linha.split(",");
				String nome = produto[0];
				double preco = Double.parseDouble(produto[1]);
				int quantidade = Integer.parseInt(produto[2]);
				Produto p = new Produto(nome, preco, quantidade);
				produtos.add(p);
				linha = br.readLine();
			}
			
		}
		catch(IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		File path = new File(strPath);
		String caminho = path.getParent() + "\\summary.csv";
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(caminho, true))) {
			for(Produto p : produtos) {
				double total = p.getPreco() * p.getQuantidade();
				bw.write(p.getNome());
				bw.write(", ");
				bw.write("R$ " + total);
				bw.newLine();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
		
			
			sc.close();
		}
		
	}

}
