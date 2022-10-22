import java.awt.Color;
import java.awt.*;
import javax.swing.*;


public class Winner  {

	private static final int GAME_WIDTH = 1000;;
	private static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension( GAME_WIDTH,GAME_HEIGHT);
    String winner;
	private static JDialog d;
	

	Winner(String win) {

        JFrame f = new JFrame();
		d = new JDialog(f , "PONG GAME",true);
		d.setLayout(new FlowLayout());
		d.add(new JLabel("GAME OVER"));
		d.add (new JLabel(" "+win+" "+"WINS THE GAME"));
		d.setSize( GAME_WIDTH,GAME_HEIGHT);
		d.setLocationRelativeTo(null);
		d.setVisible(true);
		d.setLocation(GAME_WIDTH,GAME_HEIGHT);
		d.setResizable(false);
		d.setFocusable(true);
		d.setBackground(Color.white);
		d.setBackground(Color.white);
        d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		d.pack();
		Timer.stop();
		double seconds = Timer.getTimeSec();


		
        
	
        

	}
	
}
