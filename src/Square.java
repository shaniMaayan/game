import java.awt.Image;

public  abstract class Square {
	protected int x;
	protected int y;
	protected Image img;
	protected boolean isPassable;
	
	public Square(int x, int y) {
		this.x = x;
		this.y = y;
		isPassable = false;
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
	
	public void setY(int y) {
		this.y = y;
	}
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
	public boolean isPassable() {
		return isPassable;
	}
	public void setPassable(boolean isPassable) {
		this.isPassable = isPassable;
	}
	
}
