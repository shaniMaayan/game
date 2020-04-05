
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;

public class Game extends JFrame implements Runnable{
	// static class in charge of the game and controls the board
	//Communicate with the server
	
	//---------------------------------
	public Scanner scn;
	//---------------------------------
	public  Socket s;
	public  DataInputStream dis;
	public static  DataOutputStream dos;

    //---------------------------------
	public static final int WIDTH_WIN = 900;
	public static final int HIGHT_WIN = 750;
	
	public static final int ySQR = 12;
	public static final int xSQR = 15;
    //---------------------------------
	public static int num_of_players;
    public static player players[];
    public static Board b1;
    public static int my_player_num;
    //---------------------------------
    public String ip;
    public int port;
    //---------------------------------
    public static Obstacle back[][] = new Obstacle[xSQR][ySQR];
    //---------------------------------

	public Game() {
        ip = "10.100.102.14";
        port = 888;
        b1 = new Board();
        setResizable(false);
        setTitle("Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(b1);
        pack();
        this.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				b1.requestFocus();
			}
		});
        
        this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				sendMsg("Exit");
				//Handle exit
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}
		});;
    }

    public void startGame() {
        b1.start();
    }
    
    
    public static void sendMsg(String outMsg) {
    	try {
			dos.writeUTF(outMsg);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @Override
	public void run() {
    	try
        { 
            scn = new Scanner(System.in); 
 
            s = new Socket(ip, port); 
            // obtaining input and out streams 
            DataInputStream dis = new DataInputStream(s.getInputStream()); 
            dos = new DataOutputStream(s.getOutputStream()); 
      
            // the following loop performs the exchange of 
            // information between client and client handler 
            while (true)  
            { 	
                handleData(dis.readUTF()); 
            } 
                         
        }catch(Exception e){ 
            e.printStackTrace(); 
        }
	}
    
    private void initplayers(int num_of_players) {
    	Game.num_of_players = num_of_players;
    	players = new player[num_of_players];
    	for (int i =0; i<num_of_players;i++) { 
    		players[i] = new player(0,0,"a1");
    	}
    }
    
    private void setPlayer(int i, int x, int y, String ImgName) {
    	players[i].setX(x);
    	players[i].setY(y);
    	players[i].loudImg(ImgName);
	}
    
    public void handleData(String msg) {
    	if(msg.equals("Exit"))
    	{
    			clientMain.stop();
    			scn.close(); 
	            try {
					dis.close();
					dos.close();
					s.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
    	}
    	System.out.println(msg);
    	String splitMsg[] = msg.split(":");
    	switch (splitMsg[0]) {
		case ("numOfPlayers"):
			initplayers(Integer.parseInt(splitMsg[1]));
			break;
		case ("setPlayer"):
			setPlayer(Integer.parseInt(splitMsg[1]),Integer.parseInt(splitMsg[2]),Integer.parseInt(splitMsg[3]),splitMsg[4]);
			break;
		case("setYourPlayer"):
			my_player_num = Integer.parseInt(splitMsg[1]);
			b1.setThisPlayer(players[my_player_num]);
			break;
		case("start"):
			startGame();
			break;
		case("movDir"):
			setMove(splitMsg[1],Integer.parseInt(splitMsg[2]));
			break;
		case("addObstacle"):
			break;
		case("rmObstacle"):
			break;
		case("deadPlayer"):
			break;
		default:
			System.out.println(" i dont know " + msg);
			break;
		}
    }
    
	private void setMove(String dir, int playerNUm) {
		// TODO Auto-generated method stub
		switch (dir) {
		case("right"):
			players[playerNUm].setToBeDIr(direction.right);
			players[playerNUm].newMov();
			break;
		case("left"):
			players[playerNUm].setToBeDIr(direction.left);
			players[playerNUm].newMov();
			break;
		case("up"):
			players[playerNUm].setToBeDIr(direction.up);
			players[playerNUm].newMov();
			break;
		case("down"):
			players[playerNUm].setToBeDIr(direction.down);
			players[playerNUm].newMov();
			break;
		}
	}

	
	public static String GameString() {
		String msg = "playernum: "+ my_player_num +"-  ";
		for (player p : players) {
			msg += p.toString();
		}
		return msg;
	}
}