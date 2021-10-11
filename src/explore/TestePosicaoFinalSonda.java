package explore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestePosicaoFinalSonda {

	@Test
	void test() {
	    Malha malha = new Malha(5, 5);
		
	    Sonda sonda1 = new Sonda(1, new Posicao(1, 2, Direction.N));
		malha.addSonda(sonda1);
		String comandos = "LMLMLMLMM";
		ControladorSondas.comandarSonda(sonda1, comandos);
		Posicao posicaoEsperada = new Posicao(1, 3, Direction.N);
		assertEquals(sonda1.getPosicao(), posicaoEsperada);
		assertEquals(sonda1.getPosicao().toString(), "1 3 N");
		
	    Sonda sonda2 = new Sonda(2, new Posicao(3, 3, Direction.E));
		malha.addSonda(sonda2);
		comandos = "MMRMMRMRRM";
		ControladorSondas.comandarSonda(sonda2, comandos);
		posicaoEsperada = new Posicao(5, 1, Direction.E);
		assertEquals(sonda2.getPosicao(), posicaoEsperada);
		assertEquals(sonda2.getPosicao().toString(), "5 1 E");
		
		String currentSondasPosition = malha.getCurrentSondasPosition();
		String posicoesEsperadas = "1 3 N" +System.lineSeparator()+"5 1 E";
		
		assertEquals(currentSondasPosition, posicoesEsperadas);
		
	}
	
	@Test
	void testEntradaSaidaViaArquivo() throws FileNotFoundException, IOException {
		List<String[]> inputLines = ControladorSondas.readInput();
		Malha malha = ControladorSondas.buildMalha(inputLines);

		for (int line = 1, idSonda = 0; line < inputLines.size(); line = line + 2, idSonda++) {
			Sonda sonda = null;
			try {
				Posicao posicao = ControladorSondas.buildPosicao(inputLines, line);
				sonda = new Sonda(idSonda, posicao);
				malha.addSonda(sonda);
				String comandos = ControladorSondas.buildComandos(inputLines, line);
				ControladorSondas.comandarSonda(sonda, comandos);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println("Erro ao movimentar sonda id: " + sonda.getId() + " além dos limites da malhar");
			}
		}
		String currentSondasPosition = malha.getCurrentSondasPosition();
		String posicoesEsperadas = ControladorSondas.readOutput();
		assertEquals(currentSondasPosition, posicoesEsperadas);
	}

}
