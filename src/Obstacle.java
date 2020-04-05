import java.awt.Image;

import javax.swing.ImageIcon;

public class Obstacle extends Square{
	private Image img;
	private int type; // 0 for not passable, 1 for passable , 2 for a boomsplash you wiil die


	public Obstacle(int x, int y) {
		super(x, y);
		ImageIcon iih = new ImageIcon("characters\\dot.png");
		this.img = iih.getImage();
		this.type = 0;

	}

	public Obstacle(int x, int y, String string) {
		super(x, y);
		ImageIcon iih = new ImageIcon(string);
		this.img = iih.getImage();
		this.type = 0;
	}
	
	public Obstacle(int x, int y, String string, int type) {
		super(x, y);
		ImageIcon iih = new ImageIcon(string);
		this.img = iih.getImage();
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
