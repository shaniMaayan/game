import java.awt.Image;

import javax.swing.ImageIcon;

public class Obstacle extends Square{
	private Image img;
	private boolean alive;

	public Obstacle(int x, int y, Image img) {
		super(x, y);
		this.img = img;
		alive = true;
	}
	
	public boolean isAlive() {
		return alive;
	}

	public Obstacle(int x, int y) {
		super(x, y);
		ImageIcon iih = new ImageIcon("dot.png");
		this.img = iih.getImage();
		
	}

	public Image getImg() {
		return img;
	}

@Override
public String toString() {
	// TODO Auto-generated method stub
	return super.getX()+", "+ super.getY() + "sqr";
	}
}
