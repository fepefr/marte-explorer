package explore;

public class Posicao {
	private Integer x;
	private Integer y;
	private Direction direction;
	
	public Posicao(Integer x, Integer y, Direction direction) {
		super();
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	
	public Integer incrY() {
		return ++this.y;
	}
	
	public Integer incrX() {
		return ++this.x;
	}
	
	public Integer decrY() {
		return --this.y;
	}
	
	public Integer decrX() {
		return --this.x;
	}
	
	public void setY(Integer y) {
		this.y = y;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	} 
}
