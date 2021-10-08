package explore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControlarSondas {

	public static void main(String []args) throws IOException {
	    List<String[]> inputLines = readInput();
	    
	    Sonda[][] malha = createMalha(inputLines);
		
		for (int l = 1, i = 0; l < inputLines.size(); l=l+2, i++) {
			Sonda sonda = createSonda(inputLines, l, i);
			posicionarSonda(sonda, malha);
			String comandos = String.join("",inputLines.get(l+1));
			controlarSonda(sonda, malha, comandos);
			System.out.println(sonda.getPosicao().getX() + " " + sonda.getPosicao().getY() + " " + sonda.getPosicao().getDirection());
		}
	}

	private static Sonda createSonda(List<String[]> inputLines, int l, int i) {
		Posicao posSonda = new Posicao(inputLines.get(l));
		int idSonda = i;
		Sonda sonda = new Sonda(idSonda, posSonda);
		return sonda;
	}

	private static void controlarSonda(Sonda sonda, Sonda[][] malha, String comandos) {
		for(int i = 0, n = comandos.length() ; i < n ; i++) { 
		    char comando = comandos.charAt(i); 
		    switch (comando) {
			case 'L':
				direcionarSondaEsquerda(sonda);				
				break;
			case 'R':
				direcionarSondaDireita(sonda);		
				break;
			case 'M':
				moverSonda(sonda, malha);		
				break;
			}
		}
	}

	private static void direcionarSondaDireita(Sonda sonda) {
		if (Direction.N.equals(sonda.getPosicao().getDirection())) {
			sonda.getPosicao().setDirection(Direction.E);
		}else if (Direction.E.equals(sonda.getPosicao().getDirection())) {
			sonda.getPosicao().setDirection(Direction.S);
		}else if (Direction.S.equals(sonda.getPosicao().getDirection())) {
			sonda.getPosicao().setDirection(Direction.W);
		}else if (Direction.W.equals(sonda.getPosicao().getDirection())) {
			sonda.getPosicao().setDirection(Direction.N);
		}
	}

	private static void direcionarSondaEsquerda(Sonda sonda) {
		if (Direction.N.equals(sonda.getPosicao().getDirection())) {
			sonda.getPosicao().setDirection(Direction.W);
		}else if (Direction.W.equals(sonda.getPosicao().getDirection())) {
			sonda.getPosicao().setDirection(Direction.S);
		}else if (Direction.S.equals(sonda.getPosicao().getDirection())) {
			sonda.getPosicao().setDirection(Direction.E);
		}else if (Direction.E.equals(sonda.getPosicao().getDirection())) {
			sonda.getPosicao().setDirection(Direction.N);
		}
	}

	private static void moverSonda(Sonda sonda, Sonda[][] malha) {
		malha[sonda.getPosicao().getX()][sonda.getPosicao().getY()] = null;
		if (Direction.N.equals(sonda.getPosicao().getDirection())) {
			malha[sonda.getPosicao().getX()][sonda.getPosicao().incrY()] = sonda;
		}else if (Direction.E.equals(sonda.getPosicao().getDirection())) {
			malha[sonda.getPosicao().incrX()][sonda.getPosicao().getY()] = sonda;
		}else if (Direction.S.equals(sonda.getPosicao().getDirection())) {
			malha[sonda.getPosicao().getX()][sonda.getPosicao().decrY()] = sonda;
		}else if (Direction.W.equals(sonda.getPosicao().getDirection())) {
			malha[sonda.getPosicao().decrX()][sonda.getPosicao().getY()] = sonda;
		}
	}

	private static void posicionarSonda(Sonda sonda, Sonda[][] malha) {
		malha[sonda.getPosicao().getX()][sonda.getPosicao().getY()] = sonda;
		
	}

	private static Sonda[][] createMalha(List<String[]> inputLines) {
		int larguraMalha = Integer.parseInt(inputLines.get(0)[0]);
		int alturaMalha = Integer.parseInt(inputLines.get(0)[1]);
		Sonda[][] malha = new Sonda[larguraMalha+1][alturaMalha+1];
		return malha;
	}

	private static List<String[]> readInput() throws IOException, FileNotFoundException {
		File file = new File("src/input.txt");
		List<String[]> inputLines = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        // Read the lines from the input file
	        String line;
	        while((line = reader.readLine()) != null){
	            inputLines.add(line.split(" "));
	        }
	    }
		return inputLines;
	}
}