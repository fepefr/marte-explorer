package explore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControladorSondas {

	public static final char MOVE = 'M';
	public static final char RIGHT = 'R';
	public static final char LEFT = 'L';

	public static void main(String[] args) throws IOException {
		List<String[]> inputLines = readInput();

		Malha malha = buildMalha(inputLines);

		for (int line = 1, idSonda = 0; line < inputLines.size(); line = line + 2, idSonda++) {
			Sonda sonda = null;
			try {
				Posicao posicao = buildPosicao(inputLines, line);
				sonda = new Sonda(idSonda, posicao);
				malha.addSonda(sonda);
				String comandos = buildComandos(inputLines, line);
				comandarSonda(sonda, comandos);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println("Erro ao movimentar sonda id: " + sonda.getId() + " além dos limites da malhar");
			}
		}
		malha.printCurrentSondasPositionOrdered();
	}

	public static String buildComandos(List<String[]> inputLines, int line) {
		return String.join("",inputLines.get(line+1));
	}

	public static Posicao buildPosicao(List<String[]> inputLines, int line) {
		Integer x = Integer.parseInt(inputLines.get(line)[0]);
		Integer y = Integer.parseInt(inputLines.get(line)[1]);
		Direction direction = Direction.valueOf(inputLines.get(line)[2]);
		Posicao posicao = new Posicao(x, y, direction);
		return posicao;
	}

	public static Malha buildMalha(List<String[]> inputLines) {
		Integer larguraMalha = Integer.parseInt(inputLines.get(0)[0]);
		Integer alturaMalha = Integer.parseInt(inputLines.get(0)[1]);
	    Malha malha = new Malha(larguraMalha, alturaMalha);
		return malha;
	}

	public static void comandarSonda(Sonda sonda, String comandos) {
		for(int i = 0, n = comandos.length() ; i < n ; i++) { 
		    char comando = comandos.charAt(i); 
		    switch (comando) {
			case LEFT:
				sonda.direcionarEsquerda();				
				break;
			case RIGHT:
				sonda.direcionarDireita();		
				break;
			case MOVE:
				sonda.mover();		
				break;
			}
		}
	}

	public static List<String[]> readInput() throws IOException, FileNotFoundException {
		File file = new File("src/input.txt");
		List<String[]> inputLines = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        // Read the lines from the input file
	        String line;
	        while((line = reader.readLine()) != null){
	            inputLines.add(line.split(" "));
	        }
	    }catch(Exception e) {
	    	System.err.println("Erro ao ler arquivo.");
	    	System.exit(-1);
		}
		return inputLines;
	}

	public static String readOutput() {
		File file = new File("src/output.txt");
		StringBuilder result = new StringBuilder();
		List<String> lines = new ArrayList<String>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        // Read the lines from the input file
	    	String line;
	        while((line = reader.readLine()) != null){
	            lines.add(line);
	        }
	        int i = 0;
	        for (String item : lines) {
	        	result.append(item);
	        	i++;
				if(i<lines.size())
					result.append(System.lineSeparator());
			}
	    }catch(Exception e) {
	    	System.err.println("Erro ao ler arquivo.");
	    	System.exit(-1);
		}
		return result.toString();
	}
	
}