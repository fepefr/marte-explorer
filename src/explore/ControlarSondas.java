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
	    
		String[][] malha = createMalha(inputLines);
		
		for (int l = 1; l < inputLines.size(); l=l+2) {
			Posicao posSonda = new Posicao(inputLines.get(l));
			posicionarSonda(posSonda, malha);
			String comandos = String.join("",inputLines.get(l+1));
			moverSonda(posSonda, malha, comandos);
			System.out.println(posSonda.getX() + " " + posSonda.getY() + " " + malha[posSonda.getX()][posSonda.getY()]);
		}
	}

	private static void moverSonda(Posicao posSonda, String[][] malha, String comandos) {
		for(int i = 0, n = comandos.length() ; i < n ; i++) { 
		    char comando = comandos.charAt(i); 
		    switch (comando) {
			case 'L':
				direcionarSondaEsquerda(posSonda, malha);				
				break;
			case 'R':
				direcionarSondaDireita(posSonda, malha);		
				break;
			case 'M':
				moverSonda(posSonda, malha);		
				break;
			}
		}
	}

	private static void direcionarSondaDireita(Posicao posSonda, String[][] malha) {
		if ("N".equals(malha[posSonda.getX()][posSonda.getY()])) {
			malha[posSonda.getX()][posSonda.getY()] = "E";
		}else if ("E".equals(malha[posSonda.getX()][posSonda.getY()])) {
			malha[posSonda.getX()][posSonda.getY()] = "S";
		}else if ("S".equals(malha[posSonda.getX()][posSonda.getY()])) {
			malha[posSonda.getX()][posSonda.getY()] = "W";
		}else if ("W".equals(malha[posSonda.getX()][posSonda.getY()])) {
			malha[posSonda.getX()][posSonda.getY()] = "N";
		}
	}

	private static void direcionarSondaEsquerda(Posicao posSonda, String[][] malha) {
		if ("N".equals(malha[posSonda.getX()][posSonda.getY()])) {
			malha[posSonda.getX()][posSonda.getY()] = "W";
		}else if ("W".equals(malha[posSonda.getX()][posSonda.getY()])) {
			malha[posSonda.getX()][posSonda.getY()] = "S";
		}else if ("S".equals(malha[posSonda.getX()][posSonda.getY()])) {
			malha[posSonda.getX()][posSonda.getY()] = "E";
		}else if ("E".equals(malha[posSonda.getX()][posSonda.getY()])) {
			malha[posSonda.getX()][posSonda.getY()] = "N";
		}
	}

	private static void moverSonda(Posicao posSonda, String[][] malha) {
		if ("N".equals(malha[posSonda.getX()][posSonda.getY()])) {
			malha[posSonda.getX()][posSonda.getY()] = null;
			malha[posSonda.getX()][posSonda.incrY()] = "N";
		}else if ("E".equals(malha[posSonda.getX()][posSonda.getY()])) {
			malha[posSonda.getX()][posSonda.getY()] = null;
			malha[posSonda.incrX()][posSonda.getY()] = "E";
		}else if ("S".equals(malha[posSonda.getX()][posSonda.getY()])) {
			malha[posSonda.getX()][posSonda.getY()] = null;
			malha[posSonda.getX()][posSonda.decrY()] = "S";
		}else if ("W".equals(malha[posSonda.getX()][posSonda.getY()])) {
			malha[posSonda.getX()][posSonda.getY()] = null;
			malha[posSonda.decrX()][posSonda.getY()] = "W";
		}
	}

	private static void posicionarSonda(Posicao posSonda, String[][] malha) {
		malha[posSonda.getX()][posSonda.getY()] = posSonda.getDirection();
		
	}

	private static String[][] createMalha(List<String[]> inputLines) {
		int larguraMalha = Integer.parseInt(inputLines.get(0)[0]);
		int alturaMalha = Integer.parseInt(inputLines.get(0)[1]);
		String[][] malha = new String[larguraMalha+1][alturaMalha+1];
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