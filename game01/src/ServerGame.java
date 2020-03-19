import java.awt.EventQueue;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class ServerGame {
	

	public static final int WIDTH_WIN = 900;
	public static final int HIGHT_WIN = 750;
	
	public static final int ySQR = 8;
	public static final int xSQR = 7;
	
	
	private static Square back[][] = new Square[xSQR][ySQR];
	private static List<Obstacle> listObs;
	private static int tmp[][]= {{1,1},{3,1},{4,1},{5,1},{1,2},{1,3},{3,3},{6,3},{2,4},{6,4},{0,5},{4,5},{6,5},{1,6},{6,6},{1,7},{2,7},{3,7}}; ;
	private static int obsNum;
	
	public static void main(String[] args) {
        	
			listObs = new ArrayList<Obstacle>();
			obsNum =0;
			
			initBack();
			
			for (int[] i :tmp) {
				addObstacle(i[0], i[1]);
			}
			
        	EventQueue.invokeLater(() -> {
        		JFrame ex = new Game();
        		ex.setVisible(true);
        	});
    	}
	
	
	public static void initBack() {
		for (int i = 0;i<xSQR;i++)
			for (int j =0;j<ySQR;j++)
				back[i][j] = new Square(i, j);
		
	}
	
	public static void addObstacle(Obstacle obs) {
		back[obs.getX()][obs.getY()] = obs;
		listObs.add(obs);

	}
	
	public static void addObstacle(int x, int y, Image img) {
		back[x][y] = new Obstacle(x, y, img);
		listObs.add(new Obstacle(x, y, img));

	}
	
	public static void addObstacle(int x, int y) {
		back[x][y] = new Obstacle(x, y);
		listObs.add(new Obstacle(x, y));

	}
	
	public static List<Obstacle> getListObs() {
		return listObs;
	}
	

	public static Square[][] getBack() {
		return back;
	}


	public static void rmObstacle(Obstacle obs) {
		back[obs.getX()][obs.getY()] = new Square(obs.getX(), obs.getY());
		listObs.remove(obs);
	}
	
	public static void rmObstacle(int x, int y) {
		back[x][y] = new Square(x, y);
		listObs.remove(back[x][y]);

	}
	
	public static String listToString() {
		String str ="";
		for (Obstacle i : listObs) {
			str += i.getX() + ", " + i.getY();
		}
		return str;
	}
}
