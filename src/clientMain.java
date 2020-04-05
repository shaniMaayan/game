import java.awt.EventQueue;

public class clientMain {
	
	private static Thread t1;
	public static void main(String[] args) {
        	
			new Background();
		
			EventQueue.invokeLater(() -> {
        		Game game = new Game();
        		t1 = new Thread(game);
        		t1.start();
        		game.setVisible(true);
        	});
			
    	}
	
	@SuppressWarnings("deprecation")
	public static void stop() {
		t1.stop();
	}

}
