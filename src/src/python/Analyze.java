package python;

import java.io.IOException;
import java.util.Scanner;

public class Analyze {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		Scanner leitor = new Scanner(System.in);
		int opcao = -1;

		while (true && opcao != 0) {
			System.out.println("Digite o caminho do diretorio!");
			System.out.println("Ex:(C:\\Users\\Renan\\git\\python-analisador-lip\\exemplos\\)\n");
			String caminho = leitor.next();
			System.out.println("\n===============================================================");

			try {
				String[] arquivos = FileManager.pegarArquivosNoDiretorio(caminho);
				FileManager.lerArquivosNoDiretorio(arquivos);

				System.out.println("===============================================================");
				System.out.println("Digite o numero do arquivo desejado!");
				System.out.println("\nCaso deseje sair, digite 0!\n");
				opcao = leitor.nextInt();
				System.out.println("===============================================================\n");

				if (opcao == 0) {
					continue;
				} else {
					String arquivoSelecionado = FileManager.pegarCaminhoDoArquivo(caminho, opcao);
					Boolean valid = FileManager.validarCaminho(arquivoSelecionado);

					if (valid) {
						FileManager.lerArquivoSelecionado(arquivoSelecionado);
					} else {
						System.out.println("Arquivo invalido");
					}
				}
			} catch (Exception e) {
				System.out.println("Caminho invalido, por favor tente novamente!");
				System.out.println("===============================================================");
			}
		}
	}
}
