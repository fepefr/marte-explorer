package explore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sonda {

	public static void main(String []args) throws IOException {
	    
	    List<String[]> inputLines = readInput();
	    
		String[][] malha = createMalha(inputLines);
		
		for (int l = 1; l < inputLines.size(); l=l+2) {
		
			String[] posSonda = inputLines.get(l);
			int x = Integer.parseInt(posSonda[0]);
			int y = Integer.parseInt(posSonda[1]);
			malha[x][y] = posSonda[2];
			
			String comandos = String.join("",inputLines.get(l+1));
			for(int i = 0, n = comandos.length() ; i < n ; i++) { 
			    char c = comandos.charAt(i); 
			    switch (c) {
				case 'L':
					if ("N".equals(malha[x][y])) {
						malha[x][y] = "W";
					}else if ("W".equals(malha[x][y])) {
						malha[x][y] = "S";
					}else if ("S".equals(malha[x][y])) {
						malha[x][y] = "E";
					}else if ("E".equals(malha[x][y])) {
						malha[x][y] = "N";
					}				
					break;
				case 'R':
					if ("N".equals(malha[x][y])) {
						malha[x][y] = "E";
					}else if ("E".equals(malha[x][y])) {
						malha[x][y] = "S";
					}else if ("S".equals(malha[x][y])) {
						malha[x][y] = "W";
					}else if ("W".equals(malha[x][y])) {
						malha[x][y] = "N";
					}		
					break;
				case 'M':
					if ("N".equals(malha[x][y])) {
						malha[x][y] = null;
						malha[x][++y] = "N";
					}else if ("E".equals(malha[x][y])) {
						malha[x][y] = null;
						malha[++x][y] = "E";
					}else if ("S".equals(malha[x][y])) {
						malha[x][y] = null;
						malha[x][--y] = "S";
					}else if ("W".equals(malha[x][y])) {
						malha[x][y] = null;
						malha[--x][y] = "W";
					}		
					break;
				}
			}
			System.out.println("" + x + " " + y + " " + malha[x][y]);
		}
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
