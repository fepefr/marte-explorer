package explore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Malha   {
	
	private Map<Integer, Sonda>[][] malha;
	
	@SuppressWarnings("unchecked")
	public Malha(Integer largura, Integer altura) {
		this.setMalha(new HashMap[largura + 1][altura + 1]);
	}

	public Map<Integer, Sonda>[][] getMalha() {
		return malha;
	}

	public void setMalha(Map<Integer, Sonda>[][] malha) {
		this.malha = malha;
	}

	public Sonda removerSonda(Sonda sonda) {
		return malha[sonda.getPosicao().getX()][sonda.getPosicao().getY()].remove(sonda.getId());
	}
	
	public void addSonda(Sonda sonda) {
		Map<Integer, Sonda> positionVector = (Map<Integer, Sonda>) malha[sonda.getPosicao().getX()][sonda.getPosicao().getY()];
		addSonda(sonda, positionVector);
		sonda.setMalha(this);
	}
	
	public void addSonda(Sonda sonda, Map<Integer, Sonda> positionVector) {
		if(positionVector==null) {
			positionVector = new HashMap<Integer, Sonda>();
			positionVector.put(sonda.getId(), sonda);
			this.malha[sonda.getPosicao().getX()][sonda.getPosicao().getY()] = positionVector;
		}else {
			positionVector.put(sonda.getId(), sonda);
		}
	}

	public void printCurrentSondasPosition() {
		TreeMap<Integer, Sonda> sorted = new TreeMap<>();
		for (int i = 0; i < this.malha.length; i++) {
			for (int j = 0; j < this.malha[i].length; j++) {
				if (this.malha[i][j] != null) {
					sorted.putAll((Map<? extends Integer, ? extends Sonda>) this.malha[i][j]);
				}
			}
		}
		for (Map.Entry<Integer, Sonda> sonda : sorted.entrySet()) {
			System.out.println(sonda.getValue().getPosicao().getX() + " " + sonda.getValue().getPosicao().getY() + " "
					+ sonda.getValue().getPosicao().getDirection());
		}
	}
}