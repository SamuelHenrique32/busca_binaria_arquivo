package busca_binaria_arquivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	private static final int kOPC_CRIAR_ARQUIVO = 1;
	private static final int kOPC_BUSCA_REGISTRO = 2;
	private static final int kOPC_SAIR = 3;
	private static final String kFILE_NAME = "registros.txt";
	
	private static ArrayList<Integer> lineSizes = new ArrayList();
	
	public static void main(String[] args) {
		
		int opc;
		Scanner sc = new Scanner(System.in);

		while(true) {

			showMenu();
			opc = sc.nextInt();

			switch(opc) {
			
				case kOPC_CRIAR_ARQUIVO:

					try {
						FileWriter writer = new FileWriter(kFILE_NAME);

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

					FileReader input = null;

					try {
						input = new FileReader(kFILE_NAME);

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}

					LineNumberReader lineNumberReader = new LineNumberReader(input);
					
					try {

						while(lineNumberReader.skip(Long.MAX_VALUE) > 0)
						{
	
						}

					} catch (IOException e) {
						e.printStackTrace();
					}

					int nroLinesInFile = lineNumberReader.getLineNumber();
					
					System.out.println("\nO arquivo possui " + nroLinesInFile + " linhas");

					System.out.print("\nInforme um registro a ser buscado: ");
					
					int nroReg = sc.nextInt();

					try {
			            RandomAccessFile randomAccessFile = new RandomAccessFile(kFILE_NAME, "rw");
			            
			            countLineSizes(nroLinesInFile, randomAccessFile);
			            
			            binarySearch(nroReg, nroLinesInFile, randomAccessFile);

			            // System.out.println("\nOffset no Arquivo :" + randomAccessFile.getFilePointer());
			            
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
	
	public static void showMenu() {
		System.out.println("\nOpcoes:");
		System.out.println("1- Criar Arquivo");
		System.out.println("2- Busca de Registro");
		System.out.println("3- Sair");
		System.out.print("Opcao escolhida: ");
	}
	
	public static boolean binarySearch(int nroReg, int nroLinesInFile, RandomAccessFile randomAccessFile) {

		int counter = 0, start = 0, end = nroLinesInFile, half;
        boolean found = false;

        while(start <= end) {

            half = (int)((start + end) / 2);
            
            counter++;

            /*if(vetor[half] == buscado) {
                found = true;
                break;
            }
            else if (vetor[half] < buscado) {
                start = half + 1;
            }
            else { //só pode ser maior
                end = half - 1;
            }*/
        }

        System.out.println("Quantidade de testes realizados: " + counter);

        if(found){
            System.out.println("Encontrou");
        }
        else {
            System.out.println("Não encontrou");
        }
		
		return false;
	}
	
	public static void countLineSizes(int nroLinesInFile, RandomAccessFile randomAccessFile) throws IOException {
		
		randomAccessFile.seek(0);

		for(int i=0 ; i<nroLinesInFile ; i++) {
			
			randomAccessFile.readLine();
			
			lineSizes.add((int)randomAccessFile.getFilePointer());
		}
		
		/*for(int i=0 ; i<nroLinesInFile ; i++) {
			System.out.println(lineSizes.get(i));
		}*/
	}
}