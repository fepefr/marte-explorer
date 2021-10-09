package explore;

import java.util.Objects;

public class Sonda implements Comparable<Sonda> {
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

	@Override
	public int compareTo(Sonda sonda) {
		 return (int)(this.id - sonda.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, posicao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sonda other = (Sonda) obj;
		return Objects.equals(id, other.id) && Objects.equals(posicao, other.posicao);
	}
}
