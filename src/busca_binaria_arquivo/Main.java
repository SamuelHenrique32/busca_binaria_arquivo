package busca_binaria_arquivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	private static final int kOPC_CRIAR_ARQUIVO = 1;
	private static final int kOPC_BUSCA_REGISTRO = 2;
	private static final int kOPC_SAIR = 3;

	public static void main(String[] args) {
		
		int opc;
		Scanner sc = new Scanner(System.in);

		while(true) {

			showMenu();
			opc = sc.nextInt();

			switch(opc) {
			
				case kOPC_CRIAR_ARQUIVO:
					try {
						File file = new File("registros.txt");
					    
						if (file.createNewFile()) {
							System.out.println("\nArquivo Criado: " + file.getName());
							
							try {
								FileWriter writer = new FileWriter("filename.txt");

								writer.write("Files in Java might be tricky, but it is fun enough!");

								writer.close();

							    System.out.println("\nDados escritos com sucesso no arquivo");

							} catch (IOException e) {

								System.out.println("\nOcorreu um erro na escrita para o arquivo");

							    e.printStackTrace();
							}

					    }
						else {
					        System.out.println("\nArquivo ja existe");
					    }

					} catch (IOException e) {

						System.out.println("\nOcorreu um erro na criacao do arquivo");
						
					    e.printStackTrace();
					}

				break;

				case kOPC_BUSCA_REGISTRO:
										
				break;

				case kOPC_SAIR:
					System.out.println("\nAplicacao Encerrada");
					System.exit(0);
				break;

				default:
					System.out.println("\nEscolha uma opcao valida\n");
				break;
	       }			
		}
	}
	
	public static void showMenu(){
		System.out.println("\nOpcoes:");
		System.out.println("1- Criar Arquivo");
		System.out.println("2- Busca de Registro");
		System.out.println("3- Sair");
		System.out.print("Opcao escolhida: ");
	}
}