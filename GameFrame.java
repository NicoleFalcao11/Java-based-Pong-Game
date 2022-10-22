
import java.awt.Color;

import javax.swing.JFrame;

public class GameFrame extends JFrame{

	GamePanel panel;
	
	GameFrame(){
		panel = new GamePanel();//instantiating instance into game frame constructor   
		this.add(panel);
		this.setTitle("Welcome to the Pong Game");//for title	
		this.setResizable(false);//So that the user cannot resize the window
		this.setBackground(Color.black);//to set the background colour of the game to black	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//For exiting the game on pressing the X button
		this.pack();//It will fit around the panel easily
		this.setVisible(true);//to see the game screen
		this.setLocationRelativeTo(null);//for making the window to appear in the middle of the screen
	}
}
