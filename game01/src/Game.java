
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Game extends JFrame {

    public Game() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board1());
               
        setResizable(false);
        pack();
        
        setTitle("Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
 
}