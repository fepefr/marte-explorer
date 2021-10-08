package explore;

public class Sonda {
	private int id;
	private Posicao posicao;

	public Sonda(int idSonda, Posicao posSonda) {
		this.id = idSonda;
		this.posicao = posSonda;
	}

	public Posicao getPosicao() {
		return posicao;
	}

	public void setPosicao(Posicao posicao) {
		this.posicao = posicao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
