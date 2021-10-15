package explore.entity;

import java.util.Map;
import java.util.Objects;

public class Sonda  {
	private Integer id;
	private Posicao posicao;
	private Malha malha;

	public Sonda(Integer idSonda, Posicao posSonda) {
		this.id = idSonda;
		this.posicao = posSonda;
	}
	
	public void direcionarDireita() {
		if (Direction.N.equals(this.posicao.getDirection())) {
			this.posicao.setDirection(Direction.E);
		} else if (Direction.E.equals(this.posicao.getDirection())) {
			this.posicao.setDirection(Direction.S);
		} else if (Direction.S.equals(this.posicao.getDirection())) {
			this.posicao.setDirection(Direction.W);
		} else if (Direction.W.equals(this.posicao.getDirection())) {
			this.posicao.setDirection(Direction.N);
		}
	}

	public void direcionarEsquerda() {
		if (Direction.N.equals(this.posicao.getDirection())) {
			this.posicao.setDirection(Direction.W);
		}else if (Direction.W.equals(this.posicao.getDirection())) {
			this.posicao.setDirection(Direction.S);
		}else if (Direction.S.equals(this.posicao.getDirection())) {
			this.posicao.setDirection(Direction.E);
		}else if (Direction.E.equals(this.posicao.getDirection())) {
			this.posicao.setDirection(Direction.N);
		}
	}
	
	public void mover() {
		malha.removerSonda(this);
		malha.addSonda(this, getNextPosition());
	}
	
	private Map<Integer, Sonda> getNextPosition() {
		Map<Integer, Sonda> sondas;
		switch (this.posicao.getDirection()) {
		case N: {
			sondas = (Map<Integer, Sonda>) malha.getMalha()[posicao.getX()][posicao.incrY()];
			break;
		}
		case E: {
			sondas = (Map<Integer, Sonda>) malha.getMalha()[posicao.incrX()][posicao.getY()];
			break;
		}
		case S: {
			sondas = (Map<Integer, Sonda>) malha.getMalha()[posicao.getX()][posicao.decrY()];
			break;
		}
		case W: {
			sondas = (Map<Integer, Sonda>) malha.getMalha()[posicao.decrX()][posicao.getY()];
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + posicao.getDirection());
		}
		return sondas;
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

	public Malha getMalha() {
		return malha;
	}

	public void setMalha(Malha malha) {
		this.malha = malha;
	}

	@Override
	public String toString() {
		return "Sonda [id=" + id + ", posicao=" + posicao + ", malha=" + malha + "]";
	}
}
