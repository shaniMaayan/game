import java.awt.Image;


import javax.swing.ImageIcon;
import javax.swing.Timer;

public class player extends Square{
	private Image head;
	private double speed;
	
	private direction lastDir;
	private direction toBeDIr;
	 
	private double XPlace;
	private double YPlace;
	
	private double widthMODx;
	private double widthMODy;

	
	public player(int x, int y) {
		super(x, y);
		
		XPlace = x * (ServerGame.WIDTH_WIN/ServerGame.xSQR);
		XPlace = y * (ServerGame.HIGHT_WIN/ServerGame.ySQR);

		ImageIcon iih = new ImageIcon("head.png");
	    head = iih.getImage();
		
	    speed = 3;
		lastDir = null;
		toBeDIr = null;
		System.out.println(ServerGame.WIDTH_WIN/ServerGame.xSQR);
	}
	
//----------------------------------------------------------
	//all moving proc:
//----------------------------------------------------------
	public void nextMove(direction dir) {
		toBeDIr = dir;
		moving();

	}
	public void movRight() {
		XPlace = XPlace + speed;
	}

	public void movLeft() {
		XPlace = XPlace - speed;

	}

	public void movUp() {

	YPlace = YPlace - speed;
	}

public void movDwon() {

	YPlace = YPlace + speed;
}
public boolean playerInPlace() 
{
	for (int i = 0; i<speed;i++)
	if((getX()*(ServerGame.WIDTH_WIN/ServerGame.xSQR) + i == (int)XPlace
			&& getY()*(ServerGame.HIGHT_WIN/ServerGame.ySQR) == (int)YPlace)
			|| (getX()*(ServerGame.WIDTH_WIN/ServerGame.xSQR) == (int)XPlace
					&& getY()*(ServerGame.HIGHT_WIN/ServerGame.ySQR)+i == (int)YPlace))
	{
		XPlace = getX()*(ServerGame.WIDTH_WIN/ServerGame.xSQR);
		YPlace = getY()*(ServerGame.HIGHT_WIN/ServerGame.ySQR);
		return true;
	}
	return false;
}


public boolean moveAllowed(int x, int y) {
	if((x>=0 && x<ServerGame.xSQR && y>=0 && y<ServerGame.ySQR)) {
		return (!(ServerGame.getBack()[x][y].getClass() == Obstacle.class));
	}

	return false;
}
public void moving() {
	if(!playerInPlace()) 
		{
			if (lastDir != null) {
				switch(lastDir) {
				case right:

					movRight();
					break;
				case left:
					movLeft();
					break;
				case up:
					movUp();
					break;
				case down:
					movDwon();
					break;
				}
			}
		}
	else {

		lastDir=null;
		if(toBeDIr!=null) {
			switch(toBeDIr) {
			case right:
				if (moveAllowed(getX()+1, getY())) {
				lastDir = direction.right;
				setX(getX() +1);
				movRight();
				}
				toBeDIr=null;
				break;
			case left:
				if (moveAllowed(getX()-1, getY())) {
				lastDir = direction.left;
				setX(getX() - 1);
				movLeft();
				}
				toBeDIr=null;
				break;
				
			case up:
				if (moveAllowed(getX(), getY()-1)) {
				lastDir = direction.up;
				setY(getY() - 1);
				movUp();
				}
				toBeDIr=null;
				break;
				
			case down:
				if (moveAllowed(getX(), getY()+1)) {
				lastDir = direction.down;
				setY(getY() + 1);
				movDwon();
				}
				toBeDIr=null;
				break;
				
		}
		}
	}
}

//----------------------------------------------------------------------
	public double getXPlace() {
		return XPlace;
	}

	public void setXPlace(double xPlace) {
		XPlace = xPlace;
	}

	public double getYPlace() {
		return YPlace;
	}

	public void setYPlace(double yPlace) {
		YPlace = yPlace;
	}

	public Image getImg() {
		return this.head;
	}
	

	
}

