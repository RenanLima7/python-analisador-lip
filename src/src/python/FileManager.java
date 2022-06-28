package python;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

public class FileManager {

	public static String[] pegarArquivosNoDiretorio(String endereco) {
		File arquivos = new File(endereco);
		return arquivos.list();
	}

	public static void lerArquivosNoDiretorio(String[] arquivos) {
		for (int i = 0; i < arquivos.length; i++) {
			System.out.println(i + 1 + " - " + arquivos[i]);
		}		
	}
	
	public static String pegarCaminhoDoArquivo(String caminho, int index) throws IOException {
		String[] arquivos = pegarArquivosNoDiretorio(caminho);
		String arquivoSelecionado = new File(arquivos[index - 1]).getPath();
		
		if (!caminho.substring(caminho.length()).equals("\\")) {
			caminho += "\\";
		}
		
		return caminho + arquivoSelecionado;
	}
	
	public static void lerArquivoSelecionado (String arquivoSelecionado) {
		
		
		
		try {
			File arquivo = new File(arquivoSelecionado);
			FileReader leitor = new FileReader(arquivo);
			BufferedReader buffer = new BufferedReader(leitor);
			String novoArquivo = "";
			
			while(buffer.ready()){ 
				novoArquivo = novoArquivo + buffer.readLine();
				novoArquivo = novoArquivo + "\n";
			} 
			buffer.close();
			
			System.out.println(novoArquivo);
			System.out.println("===============================================================");
			
			Python pLex = new Python(new StringReader(novoArquivo));
			
			while(true) {
				Token token = pLex.yylex();
				
				if(token == null) {
					break;
				}
				
				if(pLex.lexeme.equals("\t") | pLex.lexeme.equals("   ") | pLex.lexeme.equals("\n")) {
					System.out.println(token);
				} else {
					System.out.println(token + " -> " + pLex.lexeme);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Boolean validarCaminho(String caminho) {
		if (!caminho.substring(caminho.length() - 4, caminho.length()).equals(".txt")) {
			return false;
		}
		
		return true;
	} 
}
