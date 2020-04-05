import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class player extends Square implements ActionListener{
	private double speed;
	
	private static final int yToPlace =(Game.HIGHT_WIN/Game.ySQR);
	private static final int xToPlace =(Game.WIDTH_WIN/Game.xSQR);
	
	
	private direction lastDir;
	private direction toBeDIr;
	 
	private double XPlace;
	private double YPlace;
	
	private int realX;
	private int realY;
	
	
	private String imgName;
	private Image movingImg[] = new Image[17];
	private int movingFlag;
	
	private int boomNum;
	private int boomSize;
	
	private Image dancingImg[] = new Image[9];
	
	public player(int x, int y,String imgName) {
		super(x, y);
		
		this.XPlace = x * xToPlace;
		this.XPlace = y * yToPlace;
		
		realX =x;
		realY =y;
		
		loudImg("a1");

		
	    speed = 5;
		lastDir = null;
		toBeDIr = null;
		
		boomNum =1;
		boomSize =2;
		new Timer(30, this).start();
	}
	
	
	
//----------------------------------------------------------
	//all moving proc:
//----------------------------------------------------------

	public void movRight() {
		XPlace = XPlace  + speed ;
		this.img = movingImg[1+((movingFlag%(4*3))/3)];
		movingFlag++;
	}

	public void movLeft() {
		XPlace = XPlace - speed;
		this.img = movingImg[5+((movingFlag%(4*3))/3)];
		movingFlag++;

	}

	public void movUp() {
	YPlace = YPlace - speed;
	this.img = movingImg[9+((movingFlag%(4*3))/3)];
	movingFlag++;

	}

	public void movDwon() {
	YPlace = YPlace + speed;
	this.img = movingImg[13+((movingFlag%(4*3))/3)];
	movingFlag++;

	}
	
	//------------------------------
	public boolean playerInPlace() 
	{
		for (int i = 0; i<speed;i++)
			if((x*xToPlace + i == (int)XPlace
					&& y*yToPlace == (int)YPlace)
				|| (x*xToPlace == (int)XPlace
					&& y*yToPlace+i == (int)YPlace))
			{
				realX =x;
				realY =y;
				//XPlace = x*(Game.WIDTH_WIN/Game.xSQR);
				//YPlace = y*(Game.HIGHT_WIN/Game.ySQR);
				return true;
			}
		return false;
	}


	public boolean moveAllowed(int x, int y) {
		if((x>=0 && x<Game.xSQR && y>=0 && y<Game.ySQR)) {
			if (Game.back[x][y] != null)
				switch (Game.back[x][y].getType()) {
				case 1:
					return true;
				case 2:
					System.out.println("deaddd");
					return true;
				}
			else	
				return true;
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
			else;
		}

		else
		{	
			if (newMoveAllowed())
			{
				newMov();
			}
		}
	}

	private boolean newMoveAllowed() {
		if(toBeDIr!=null)
			switch(toBeDIr) {
			case right:
				if (moveAllowed(x+1, y))
				{
					Game.sendMsg("movDir:right:"+Game.my_player_num);
					return true;
				}
				toBeDIr=null;
				break;
			case left:
				if (moveAllowed(x-1, y))
				{
					Game.sendMsg("movDir:left:"+Game.my_player_num);
					return true;
				}
				toBeDIr=null;
				break;
				
			case up:
				if (moveAllowed(x, y-1))
				{
					Game.sendMsg("movDir:up:"+Game.my_player_num);
					return true;
				}
				toBeDIr=null;
				break;
		
			case down:
				if (moveAllowed(x, y+1))
				{
					Game.sendMsg("movDir:down:"+Game.my_player_num);
					return true;
				}
				toBeDIr=null;
				break;
			}
		return false;
	}

	public void newMov()
	{
		movingFlag =0;
		lastDir=null;
		if(toBeDIr!=null) {
			switch(toBeDIr) {
			case right:
				lastDir = direction.right;
				x += 1;
				toBeDIr=null;
				movRight();
				break;
			case left:
				lastDir = direction.left;
				x -= 1;
				toBeDIr=null;
				movLeft();
				break;
			case up:		
				lastDir = direction.up;
				y -= 1;
				toBeDIr=null;
				movUp();
				break;
			case down:
				lastDir = direction.down;
				y += 1;
				toBeDIr=null;
				movDwon();
				break;	
			}
		}
	}


//----------------------------------------------------------------------

	public void loudImg(String name) {
		ImageIcon movingArr[] = {new ImageIcon("characters\\"+name+"-idle"+".png"),
							new ImageIcon("characters\\"+name+"-right2"+".png"),
							new ImageIcon("characters\\"+name+"-right1"+".png"),
							new ImageIcon("characters\\"+name+"-right2"+".png"),
							new ImageIcon("characters\\"+name+"-right3"+".png"),
							new ImageIcon("characters\\"+name+"-left2"+".png"),
							new ImageIcon("characters\\"+name+"-left1"+".png"),
							new ImageIcon("characters\\"+name+"-left2"+".png"),
							new ImageIcon("characters\\"+name+"-left3"+".png"),
							new ImageIcon("characters\\"+name+"-back2"+".png"),
							new ImageIcon("characters\\"+name+"-back1"+".png"),
							new ImageIcon("characters\\"+name+"-back2"+".png"),
							new ImageIcon("characters\\"+name+"-back3"+".png"),
							new ImageIcon("characters\\"+name+"-front2"+".png"),
							new ImageIcon("characters\\"+name+"-front1"+".png"),
							new ImageIcon("characters\\"+name+"-front2"+".png"),
							new ImageIcon("characters\\"+name+"-front3"+".png")};
		
		ImageIcon dancingArr[] = {new ImageIcon("characters\\"+name+"-dance1"+".png"),
				new ImageIcon("characters\\"+name+"-dance2"+".png"),
				new ImageIcon("characters\\"+name+"-dance3"+".png"),
				new ImageIcon("characters\\"+name+"-dance4"+".png"),
				new ImageIcon("characters\\"+name+"-dance5"+".png"),
				new ImageIcon("characters\\"+name+"-dance6"+".png"),
				new ImageIcon("characters\\"+name+"-dance7"+".png"),
				new ImageIcon("characters\\"+name+"-dance8"+".png"),
				new ImageIcon("characters\\"+name+"-dance9"+".png")};
		for(int i = 0; i<17;i++)
			this.movingImg[i] = movingArr[i].getImage();
		for(int i = 0; i<9;i++)
			this.dancingImg[i] = movingArr[i].getImage();
		this.img = movingArr[0].getImage();
		}
	
	
//-----------------------------------------------------------------------
	public direction getToBeDIr() {
	return toBeDIr;
	}

public void setToBeDIr(direction toBeDIr) {
	this.toBeDIr = toBeDIr;
	}

	@Override
	public void setX(int x) {
		this.x = x;
		XPlace = x * xToPlace;
	}
	
	@Override
	public void setY(int y) {
		this.y = y;
		YPlace = y * yToPlace;
	}
	
	public void setXPlace(double xPlace) {
		XPlace = xPlace;
		x = (int)(XPlace / xToPlace);
		//XPlace = x*(clientMain.WIDTH_WIN/clientMain.xSQR);
	}

	public void setYPlace(double yPlace) {
		YPlace = yPlace;
		y = (int)(YPlace /yToPlace);
		//YPlace = y*(clientMain.HIGHT_WIN/clientMain.ySQR);
	}
	
	public int getRealx() {
		return realX;
	}
	
	public int getRealy() {
		return realY;
	}
	public int getXPlace() {
		return (int)XPlace;
	}
	
	public int getYPlace() {
		return (int)YPlace;
	}
	
	public int getBoomNum() {
		return boomNum;
	}

	public void setBoomNum(int boomNum) {
		this.boomNum = boomNum;
	}

	public int getBoomSize() {
		return boomSize;
	}

	public void setBoomSize(int boomSize) {
		this.boomSize = boomSize;
	}

	@Override
	public String toString() {
		return "x:" + x + " Xplace: " + XPlace + " y: " + y + " Yplace: " + YPlace + " inSqr? " + playerInPlace(); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		moving();		
	}

	
}

