package explore;

public class Posicao {
	int x;
	int y;
	String direction;
	
	public Posicao(int x, int y, String direction) {
		super();
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	public Posicao(String[] posSonda) {
		this.x = Integer.parseInt(posSonda[0]);
		this.y = Integer.parseInt(posSonda[1]);
		this.direction = posSonda[2];
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	
	public int incrY() {
		return ++this.y;
	}
	
	public int incrX() {
		return ++this.x;
	}
	
	public int decrY() {
		return --this.y;
	}
	
	public int decrX() {
		return --this.x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	} 
}
