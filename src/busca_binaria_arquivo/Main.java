package busca_binaria_arquivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
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
						FileWriter writer = new FileWriter("registros.txt");

						writer.write("1000 Ademar 25 500\n");
						writer.write("1050 Afonso 27 700\n");
						writer.write("1075 Angela 22 600\n");
						writer.write("1100 Antônio 28 850\n");
						writer.write("1300 Carlos 23 750\n");
						writer.write("1350 Cesar 55 900\n");
						writer.write("1400 Claudia 25 800\n");
						writer.write("1440 Cristiano 30 1000\n");
						writer.write("1480 Darci 20 750\n");
						writer.write("1600 Diogo 26 600\n");
						writer.write("1700 Edson 35 500\n");
						writer.write("1800 Eder 26 550\n");
						writer.write("1850 Elias 32 650\n");
						writer.write("1900 Flavio 28 780\n");
						writer.write("1950 Gerson 39 700\n");
						writer.write("1975 Geraldo 34 2500\n");
						writer.write("2000 Helena 42 500\n");

						writer.close();

					    System.out.println("\nDados escritos com sucesso no arquivo");

					} catch (IOException e) {

						System.out.println("\nOcorreu um erro na escrita para o arquivo");

					    e.printStackTrace();
					}

				break;

				case kOPC_BUSCA_REGISTRO:
					System.out.print("\nInforme um registro a ser buscado:");
					
					int nroReg = sc.nextInt();

					try {
			            RandomAccessFile randomAccessFile = new RandomAccessFile("registros.txt", "rw");
			 
			            randomAccessFile.seek(6);

			            System.out.println("\nOffset no Arquivo :" + randomAccessFile.getFilePointer());
			 
			            randomAccessFile.close();
			 
			        } catch (IOException ex) {
			            ex.printStackTrace();
			        }

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