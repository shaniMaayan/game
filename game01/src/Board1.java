import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board1 extends JPanel implements ActionListener, KeyListener

{

	private static Timer timer;
    
    public static player p;
    
    public Board1() {
    	setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(ServerGame.WIDTH_WIN, ServerGame.HIGHT_WIN));
    	
        addKeyListener(this);
        p = new player(0, 0);

        timer = new Timer(10, this);
        timer.start();
    }
    

	//----------------------------------------------------------------------------------------
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintstart(g);
        paintBack(g);
        paintPlayers(g);    
    }
    
    private void paintBack(Graphics g) {
		for (Obstacle i : ServerGame.getListObs()) {
			g.drawImage(i.getImg(), i.getX()*(ServerGame.WIDTH_WIN / ServerGame.xSQR), i.getY()*(ServerGame.HIGHT_WIN / ServerGame.ySQR), this); 
		}  
	}


	public void paintstart(Graphics g) {
		for(int i=0;i<ServerGame.WIDTH_WIN;i+=ServerGame.WIDTH_WIN/ServerGame.xSQR) {
			g.drawLine(i, 0, i, ServerGame.HIGHT_WIN);

		}
		for(int i=0;i<ServerGame.HIGHT_WIN;i+=ServerGame.HIGHT_WIN/ServerGame.ySQR) {
			g.drawLine(0, i, ServerGame.WIDTH_WIN, i);

		}
	}
    
	public void paintPlayers(Graphics g) {
		for (int i =0;i<1;i++)
			g.drawImage(p.getImg(), (int)p.getXPlace(), (int)p.getYPlace(), this); 
    }
//----------------------------------------------------------------------------------------

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i =0;i<1;i++)
			{
			p.moving();
			}
			repaint();
    }
	
	
	@Override
	public void keyPressed(KeyEvent e) {

		char key = e.getKeyChar();
		System.out.println(key);
		switch (key) 
		{
		case 'a':
			p.nextMove(direction.left);
			break;
		case 'd':
			p.nextMove(direction.right);
			break;
		case 's':
			p.nextMove(direction.down);
			break;
		case 'w':
			p.nextMove(direction.up);
			break;
		
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub


	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub


	}
}