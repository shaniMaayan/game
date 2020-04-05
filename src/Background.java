import java.awt.Image;

public class Background {

	private static int tmp[][]= {{1,1},{3,1},{4,1},{5,1},{1,2},{1,3},{3,3},{6,3},{2,4},{6,4},{0,5},{4,5},{6,5},{1,6},{6,6},{1,7},{2,7},{3,7}}; ;
	
	
	public Background() {
		initBack();
		for (int[] i :tmp) {
			addObstacle(i[0], i[1]);
		}
	}
	
	public static void initBack() {
		for (int i = 0;i<Game.xSQR;i++)
			for (int j =0;j<Game.ySQR;j++)
				Game.back[i][j] =null;
		
	}
	

	public static void addObstacle(Obstacle obs) {
		Game.back[obs.getX()][obs.getY()] =obs;

	}
	

	public static void addObstacle(int x, int y, String imgName) {
		Game.back[x][y] = new Obstacle(x, y, imgName);
		
	}
	
	public static void addObstacle(int x, int y) {
		Game.back[x][y] = new Obstacle(x, y);

	}
	
	public static void rmObstacle(Obstacle obs) {
		Game.back[obs.getX()][obs.getY()] =null;
	}
	
	public static void rmObstacle(int x, int y) {
		Game.back[x][y] = null;

	}

	
}
