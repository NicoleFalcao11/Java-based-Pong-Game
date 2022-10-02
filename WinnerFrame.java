import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class WinnerFrame extends JFrame {

	private static final int frameWidth = 1000;
	private static final int frameHeight = (int)(frameWidth * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(frameWidth ,frameHeight );
	public static GamePanel game;
    String winner;
	

	WinnerFrame(String win) {
		final JLabel panel = new JLabel();
		winner = win;
		System.out.println(winner);
		this.setTitle("GAME OVER"+" "+winner+" "+"WINS");
		this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setSize(frameWidth, frameHeight);

		Timer.stop();
		double seconds = Timer.getTimeSec();
        this.setVisible(true);
		this.add(panel);
        repaint();
	}
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.black);
		Font font = new Font("TimesRoman",Font.BOLD,30);
		g.setFont(font);
		g.drawString("PLAYER 1 WINS",200,225);
	}
}