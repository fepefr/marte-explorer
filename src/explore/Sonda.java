package explore;

public class Sonda {
	private Integer id;
	private Posicao posicao;

	public Sonda(Integer idSonda, Posicao posSonda) {
		this.id = idSonda;
		this.posicao = posSonda;
	}

	public Posicao getPosicao() {
		return posicao;
	}

	public void setPosicao(Posicao posicao) {
		this.posicao = posicao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
