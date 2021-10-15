package explore.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class Malha   {
	
	private Map<Integer, Sonda>[][] sondas;
	
	@SuppressWarnings("unchecked")
	public Malha(Integer largura, Integer altura) {
		this.setMalha(new HashMap[largura + 1][altura + 1]);
	}

	public Map<Integer, Sonda>[][] getMalha() {
		return sondas;
	}

	public void setMalha(Map<Integer, Sonda>[][] malha) {
		this.sondas = malha;
	}

	public Sonda removerSonda(Sonda sonda) {
		return sondas[sonda.getPosicao().getX()][sonda.getPosicao().getY()].remove(sonda.getId());
	}
	
	public void addSonda(Sonda sonda) {
		Map<Integer, Sonda> positionVector = (Map<Integer, Sonda>) sondas[sonda.getPosicao().getX()][sonda.getPosicao().getY()];
		addSonda(sonda, positionVector);
		sonda.setMalha(this);
	}
	
	public void addSonda(Sonda sonda, Map<Integer, Sonda> positionVector) {
		if(positionVector==null) {
			positionVector = new HashMap<Integer, Sonda>();
			positionVector.put(sonda.getId(), sonda);
			this.sondas[sonda.getPosicao().getX()][sonda.getPosicao().getY()] = positionVector;
		}else {
			positionVector.put(sonda.getId(), sonda);
		}
	}
	
	public void printCurrentSondasPosition() {
		for (int i = 0; i < this.sondas.length; i++) {
			for (int j = 0; j < this.sondas[i].length; j++) {
				if (this.sondas[i][j] != null) {
					for (Map.Entry<Integer, Sonda> sonda : this.sondas[i][j].entrySet()) {
						System.out.println(sonda.getValue().getPosicao().toString());
					}
				}
			}
		}
	}

	public void printCurrentSondasPositionOrdered() {
		Map<Integer, Sonda> sorted = currentSondasPositionOrdered();
		for (Map.Entry<Integer, Sonda> sonda : sorted.entrySet()) {
			System.out.println(sonda.getValue().getPosicao().toString());
		}
	}

	public String getCurrentSondasPosition() {
		Map<Integer, Sonda> sorted = currentSondasPositionOrdered();
		StringBuilder result = new StringBuilder();
		Set<Entry<Integer, Sonda>> vetor = sorted.entrySet();
		int i = 0;
		for (Map.Entry<Integer, Sonda> sonda : vetor) {
			result.append(sonda.getValue().getPosicao().toString());
			i++;
			if(i<vetor.size())
				result.append(System.lineSeparator());
		}
		return result.toString();
	}
	
	public Map<Integer, Sonda> currentSondasPositionOrdered() {
		Map<Integer, Sonda> sorted = new TreeMap<>();
		for (int i = 0; i < this.sondas.length; i++) {
			for (int j = 0; j < this.sondas[i].length; j++) {
				if (this.sondas[i][j] != null) {
					sorted.putAll((Map<? extends Integer, ? extends Sonda>) this.sondas[i][j]);
				}
			}
		}
		return sorted;
	}
}