package explore;

import java.io.IOException;
import java.util.List;

import explore.entity.Malha;
import explore.entity.Posicao;
import explore.entity.Sonda;

public class ControladorSondas {

	public static final char MOVE = 'M';
	public static final char RIGHT = 'R';
	public static final char LEFT = 'L';

	public static void main(String[] args) throws IOException {
		List<String[]> inputLines = ControladorSondaUtil.readInput();

		Malha malha = ControladorSondaUtil.buildMalha(inputLines);

		for (int line = 1, idSonda = 0; line < inputLines.size(); line = line + 2, idSonda++) {
			Sonda sonda = null;
			try {
				Posicao posicao = ControladorSondaUtil.buildPosicao(inputLines, line);
				sonda = new Sonda(idSonda, posicao);
				malha.addSonda(sonda);
				String comandos = ControladorSondaUtil.buildComandos(inputLines, line);
				comandarSonda(sonda, comandos);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println("Erro ao movimentar sonda id: " + sonda.getId() + " além dos limites da malhar");
			}
		}
		malha.printCurrentSondasPositionOrdered();
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
}