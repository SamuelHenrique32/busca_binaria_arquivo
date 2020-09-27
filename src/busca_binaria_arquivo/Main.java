package busca_binaria_arquivo;

import java.io.*;
import java.util.Scanner;

public class Main {
	
	private static final int kOPC_CRIAR_ARQUIVO = 1;
	private static final int kOPC_BUSCA_REGISTRO = 2;
	private static final int kOPC_SAIR = 3;
	private static final int kTAMANHO_REGISTRO = 50;
	private static final String kFILE_NAME = "registros.txt";
	private static final String kFILE_NAME_OUTPUT = "registros_binario";
	
	public static void main(String[] args) throws IOException {
		
		int opc, linesQuantity = 0, bytesQtd = 0;
		boolean fileCreated = false;

		Scanner sc = new Scanner(System.in);

		while(true) {

			showMenu();
			opc = sc.nextInt();

			switch(opc) {
			
				case kOPC_CRIAR_ARQUIVO:
					
					linesQuantity = createFile();

					bytesQtd = (linesQuantity*kTAMANHO_REGISTRO);
					
					System.out.println("\nQuantidade de bytes no arquivo binario criado: " + bytesQtd);
					
					fileCreated = true;

				break;

				case kOPC_BUSCA_REGISTRO:
					
					if(!fileCreated) {

						linesQuantity = createFile();

						bytesQtd = (linesQuantity*kTAMANHO_REGISTRO);
						
						System.out.println("\nQuantidade de bytes no arquivo binario criado: " + bytesQtd);
						
						fileCreated = true;
					}

					System.out.print("\nInforme um registro a ser buscado: ");
					
					int nroReg = sc.nextInt();
					
					System.out.println("\nProcurando o registro com chave " + nroReg + "\n");

					try {
			            RandomAccessFile randomAccessFile = new RandomAccessFile(kFILE_NAME_OUTPUT, "rw");
			            
			            System.out.println("Tamanho do arquivo binario para leitura: " + randomAccessFile.length() + "\n");
			            
			            binarySearch(nroReg, linesQuantity, randomAccessFile);

			            randomAccessFile.close();
			 
			        } catch (IOException ex) {
			            ex.printStackTrace();
			        }

				break;

				case kOPC_SAIR:

					System.out.println("\nAplicacao Encerrada");
					
					sc.close();

					System.exit(0);

				break;

				default:

					System.out.println("\nEscolha uma opcao valida\n");

				break;
	       }			
		}
	}

	private static int createFile() throws IOException {

		int linesQuantity = 0;
		String line;
		
		File file = new File(kFILE_NAME);
		BufferedReader br = new BufferedReader(new FileReader(file));
		OutputStream outputStream = new FileOutputStream(kFILE_NAME_OUTPUT);

		System.out.println("\nO arquivo " + kFILE_NAME + " sera lido para gerar o arquivo binario");

		try {

			while((line = br.readLine()) != null) {
				
				linesQuantity++;
				
				StringBuilder strBuilder = new StringBuilder(line);

				// Complete line length to match kTAMANHO_REGISTRO
				for(int i=line.length() ; i<kTAMANHO_REGISTRO ; i++) {
					strBuilder.append(" ");
				}
				
				outputStream.write(strBuilder.toString().getBytes());
				
				outputStream.flush();
			}
			
		} catch (FileNotFoundException e1) {

			System.out.println("Nao foi possivel abrir o arquivo para leitura\n");

			System.exit(0);

		}
		
		br.close();
		
		outputStream.close();
		
		System.out.println("\nDados escritos com sucesso no arquivo binario");

		return linesQuantity;
	}
	
	public static void showMenu() {
		System.out.println("\nOpcoes:");
		System.out.println("1- Criar Arquivo");
		System.out.println("2- Busca de Registro");
		System.out.println("3- Sair");
		System.out.print("Opcao escolhida: ");
	}
	
	public static boolean binarySearch(int nroReg, int nroLinesInFile, RandomAccessFile randomAccessFile) throws IOException {

		int counter = 0, start = 1, end = nroLinesInFile, half = -1, key = -1;

        boolean found = false;

        String currentLine;
		String[] currentLineSplitted;
		
		 char[] charReadLine = new char[kTAMANHO_REGISTRO];

        while(start <= end) {

            half = (int)((start + end) / 2);
            
            counter++;
            
            System.out.println("Procurando na linha " + half);
            
            if(half == 1) {
            	randomAccessFile.seek(0);
            }
            else {
            	randomAccessFile.seek((half*kTAMANHO_REGISTRO)-(kTAMANHO_REGISTRO));	
            }
            
            for(int i=0 ; i<kTAMANHO_REGISTRO ; i++) {
            	charReadLine[i] = (char)randomAccessFile.readByte();
            }
            
            currentLine = new String(charReadLine);
            
            System.out.println("Linha lida: " + currentLine);
            
            currentLineSplitted = currentLine.split(" ");
            
            key = Integer.parseInt(currentLineSplitted[0]);
            
            System.out.println("Comparando chave " + nroReg+ " com " + key + "\n");
            
            if(key == nroReg) {
                found = true;

                break;
            }
            else if(key < nroReg) {
                start = half + 1;
            }
            else {
                end = half - 1;
            }
        }

        System.out.println("Quantidade de testes realizados: " + counter);

        if(found){
            System.out.println("Encontrou na linha " + half);
        }
        else {
            System.out.println("Nao encontrou");
        }
		
		return false;
	}
}