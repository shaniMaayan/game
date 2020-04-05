import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements KeyListener, ActionListener

{
    private player thisPlayer;
    
    public Board() {
    	
    	setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(Game.WIDTH_WIN, Game.HIGHT_WIN));
        addKeyListener(this);
        //repaint();
    }
    
    public void start() {
    	new Timer(33, this).start();
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
    	for (int i = 0;i<Game.xSQR;i++)
			for (int j =0;j<Game.ySQR;j++) {
				if (Game.back[i][j] != null)
					g.drawImage(Game.back[i][j].getImg(), 
							Game.back[i][j].getX()* (Game.WIDTH_WIN/Game.xSQR), 
							Game.back[i][j].getY()* (Game.HIGHT_WIN/Game.ySQR), this);
			}
	}


	public void paintstart(Graphics g) {
		for(int i=0;i<Game.WIDTH_WIN;i+=Game.WIDTH_WIN/Game.xSQR) {
			g.drawLine(i, 0, i, Game.HIGHT_WIN);

		}
		for(int i=0;i<Game.HIGHT_WIN;i+=Game.HIGHT_WIN/Game.ySQR) {
			g.drawLine(0, i, Game.WIDTH_WIN, i);

		}
	}
    
	public void paintPlayers(Graphics g) {
		try {
			for (player p:Game.players)
				g.drawImage(p.getImg(), (int)p.getXPlace(), (int)p.getYPlace(), this); 
		} catch (Exception e) {
			// TODO: handle exception
		}
		
    }
//----------------------------------------------------------------------------------------
	
	@Override
	public void keyPressed(KeyEvent e) {
		char key = e.getKeyChar();
		switch (key) 
		{
		case 'a':
			thisPlayer.setToBeDIr(direction.left);
			break;
		case 'd':
			thisPlayer.setToBeDIr(direction.right);
			break;
		case 's':
			thisPlayer.setToBeDIr(direction.down);
			break;
		case 'w':
			thisPlayer.setToBeDIr(direction.up);
			break;
		case KeyEvent.VK_ENTER:
			new Boom(this.thisPlayer.getRealx(),this.thisPlayer.getRealy());
			break;
		case KeyEvent.VK_SPACE:
			new Boom(this.thisPlayer.getRealx(),this.thisPlayer.getRealy());
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}

	public player getThisPlayer() {
		return thisPlayer;
	}

	public void setThisPlayer(player thisPlayer) {
		this.thisPlayer = thisPlayer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		requestFocus();
	}

}