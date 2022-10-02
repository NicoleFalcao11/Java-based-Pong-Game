
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int BALL_DIAMETER =20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    String win;
	  String playerone, playertwo;
    

    public int scorelimit = 2;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;




    GamePanel(){
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }
    public void printWinner() {
		
		System.out.println("WINNER: " + win);
		double seconds = Timer.getTimeSec();
		int value = (int) seconds;
		System.out.println("Game Time: " + value + " seconds.");
		System.out.println("Match Score: " + playerone + ": " + score.player1 + " | " + playertwo + ": " + score.player2);
	
	}
    public void newBall() {
          random = new Random();
          ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);


    }
    public void newPaddles() {
        paddle1 = new Paddle(0, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2) , PADDLE_WIDTH,PADDLE_HEIGHT,1 );
        paddle2 = new Paddle( GAME_WIDTH-PADDLE_WIDTH , (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2) , PADDLE_WIDTH,PADDLE_HEIGHT,2 );
        
    }
    public void paint(Graphics g){
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);

    }
    public void draw(Graphics g){
          paddle1.draw(g);
          paddle2.draw(g);
          ball.draw(g);
          score.draw(g);
    }
    
    public void move() {
          //To smoothen movement of paddles and ball//
          paddle1.move();
          paddle2.move();
          ball.move();
    }
    public void checkcollision() {
           //To stop paddles and ball from moving out of window//
           if(ball.y <=0) {
            ball.setYDirection(-ball.yVelocity);
           }
           if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
           }

           if(ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            //ball.xVelocity++;
            if(ball.yVelocity > 0)
            ball.yVelocity++;
            else
            ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
           }
           if(ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            //ball.xVelocity++;
            if(ball.yVelocity > 0)
            ball.yVelocity++;
            else
            ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
           }

           if(paddle1.y<=0)
            paddle1.y=0;
           if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT) )
             paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT; 
             if(paddle2.y<=0)
            paddle2.y=0;
           if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT) )
             paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT; 

             //creates new ball and paddles
             if(ball.x <=0) {
                score.player2++;
                newPaddles();
                newBall();
                System.out.println("Player 2:"+score.player2);
              }
              if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
                score.player1++;
                newPaddles();
                newBall();
                System.out.println("Player 1:"+score.player1);
                
              }
              if(score.player1 == scorelimit)
              {
                win = "playerone";
                Paddle.stop=true;
                WinnerFrame w = new WinnerFrame(win);
                printWinner();
                

              }
              if(score.player2 == scorelimit)
              {
                win = "playertwo";
                Paddle.stop=true;
                WinnerFrame w = new WinnerFrame(win);
                printWinner();

              }
              




    }
    public void run() {
          //Game loop
          long lastTime = System.nanoTime();
          double amountofTicks = 60.0;
          double ns = 1000000000 / amountofTicks;
          double delta = 0;
          while(true) {
            long now = System.nanoTime();
            delta +=(now - lastTime)/ns;
            lastTime = now;
            if(delta >=1) {
                move();
                checkcollision();
                repaint();
                delta--;
            }
          }
    }
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);

        }
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }

    }
}    

