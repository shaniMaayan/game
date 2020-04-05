import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.management.timer.TimerMBean;
import javax.swing.ImageIcon;

public class Boom{
    
    private int x;
    private int y;
    
    private double timeToBoom;
    private double timeToburn;
    
    private List<Obstacle> obsList;
    
    public Boom(int x, int y) {
    	obsList = new ArrayList<Obstacle>();
    	Obstacle tmp = new Obstacle(x, y, "characters\\boom.png");
    	obsList.add(tmp);
    	System.out.println("try add");
    	Background.addObstacle(tmp);
    	System.out.println("added ");
    	System.out.println(Game.players[Game.my_player_num].getBoomSize());   	
    	this.x =x;
    	this.y=y;
    	timeToBoom =2;
    	timeToburn =1;
    	new Timer().schedule(new TimerTask() {
			
    		Obstacle tmp;
    		private void boomSplash() {
    			
    			boomSplashRight();
    			boomSplashLeft();
    			boomSplashDown();
    			boomSplashUp();
    		
    		}
    		
    		private void boomSplashUp() {
				
			for (int i = 1; i<Game.players[Game.my_player_num].getBoomSize()+1; i++)
				{
				if((y - i) >0 )
				{				
					if (Game.back[x][y-i] == null)
						{
						tmp = new Obstacle(x, y-i, "characters\\boomSplash.png",2);
						//2 means boom splash
						obsList.add(tmp);
						Background.addObstacle(tmp);
						checkDead(x,y);
						}
					else
						break;
					}
				else
					break;
				
				}				
			}

			private void boomSplashDown() {
				for (int i = 1; i<Game.players[Game.my_player_num].getBoomSize()+1; i++)
				{
				if((y + i) < Game.xSQR)
				{				
					if (Game.back[x][y+i] == null)
						{
						tmp = new Obstacle(x, y+i, "characters\\boomSplash.png",2);
						//2 means boom splash
						obsList.add(tmp);
						Background.addObstacle(tmp);
						checkDead(x,y);
						}
					else
						break;
					}
				else
					break;
				
				}
				
			}


			private void boomSplashLeft() {
				for (int i = 1; i<Game.players[Game.my_player_num].getBoomSize()+1; i++)
				{
				if((x - i) > 0)
				{				
					if (Game.back[x-i][y] == null)
						{
						tmp = new Obstacle(x-i, y, "characters\\boomSplash.png",2);
						//2 means boom splash
						obsList.add(tmp);
						Background.addObstacle(tmp);
						checkDead(x,y);
						}
					else
						break;
					}
				else
					break;
				
				}
				
			}


			private void boomSplashRight() {
				for (int i = 1; i<Game.players[Game.my_player_num].getBoomSize()+1; i++)
				{
				if((x + i) < Game.xSQR)
				{				
					if (Game.back[x+i][y] == null)
						{
						tmp = new Obstacle(x+i, y, "characters\\boomSplash.png",2);
						//2 means boom splash
						obsList.add(tmp);
						Background.addObstacle(tmp);
						checkDead(x,y);
						}
					else
						break;
					}
				else
					break;
				
				}
			}


			@Override
			public void run() {
				//when boom
				
				boomSplash();
				new Timer().schedule(new TimerTask() {
					@Override
					public void run() {
						// after boom
						for (Obstacle  o:obsList)
							Background.rmObstacle(o);
						this.cancel();
					}
		    	},(long) (timeToburn*1000));
								this.cancel();
								
			}
    	},(long) (timeToburn*1000));
    }
    
    
    public static void checkDead(int x, int y) {
		for (player p : Game.players)
			if(p.getRealx() == x && p.getRealy() == y)
				System.out.println("player is dead");
		}
    }
