import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
    static final int GAME_WIDTH = 1000;//The static keyword means the value is the same for every instance of the class. The final keyword means once the variable is assigned a value it can never be changed. The combination of static final in Java is how to create a constant value.
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);//Dimension class is a part of Java AWT. It contains the height and width of a component in an integer as well as double precision. The use of Dimension class is that many functions of Java AWT and Swing return dimension object.
    static final int BALL_DIAMETER =20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    static final int MAX_SCORE = 10;
	static boolean gameRun = true;
    //objects of the respective classes 
    Thread gameThread;//thread class
    Image image;// Image superclass from java.awt used for Image representation 
    Graphics graphics;
    Random random;//Random class to generate random location for ball to move in
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;



    GamePanel(){// default constructor of the class
        newPaddles();//methods in gamepanel class that will be executed by the thread
        newBall();
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);//The focusable flag indicates whether a component can gain the focus if it is requested to do so.
        this.addKeyListener(new AL());//From KeyListener class , a keyborad event will be generated,adding keylistener to notify key events
        this.setPreferredSize(SCREEN_SIZE);//Will set JPanel size as given above

        gameThread = new Thread(this); 
        //this will call run()method
        //Here the gamethread is an object of the class thread and is implementing the Runnable interface and 
        // we are passing it as a parameter to the constructor of our gamepanel class.
    
        gameThread.start();//Thread starts executing following methods
    }
    public void newBall() {
          random = new Random();//Instantiating object of Random  and ball class 
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
    public void draw(Graphics g)//g is the object of the Graphics class
    {
          paddle1.draw(g);//to create / draw a paddle on the screen , it is calling the method draw From the paddle class
          paddle2.draw(g);
          ball.draw(g);
          score.draw(g);
    }
    public void move() {
          //To smoothen movement of paddles and ball//
          paddle1.move();//To smoothen movement of the paddle , move is the method called from paddle class
          paddle2.move();
          ball.move();
    }
    public void checkcollision() {
           //To stop paddles and ball from moving out of window OR to ensure that ball, paddles are colliding with the boundaries//
           if(ball.y <=0) //y is a parameter from the rectangle class that denotes the y coordinate of the JPanel
           {
            ball.setYDirection(-ball.yVelocity);//setYDirection is a method called  from Ball class
           }
           if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
           }

           if(ball.intersects(paddle1))//if ball touches the paddle
            {
            ball.xVelocity = Math.abs(ball.xVelocity);//absolute value of velocity of ball in x direction is passed
            //ball.xVelocity++;
            if(ball.yVelocity > 0)//if ball is in motion
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

           if(paddle1.y<=0)//if paddle is moving out of JPanel along y direction in upper direction  , assigning it coordinate value y=0 
            paddle1.y=0;
           if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT) )//if paddle is moving along the y direction but lower side,assign it 
           //coordinate value equal to JPanel height
           //similarly for paddle 2
             paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT; 
             if(paddle2.y<=0)
            paddle2.y=0;
           if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT) )
             paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT; 

             //creates new ball and assigns scorepoints
             if(ball.x <=0) {
                score.player2++;//increments value of variable player1 from score class
                newPaddles();//new ball and paddle emerges for that player
                newBall();
                System.out.println("Player 2:"+score.player2);
              }
              if(ball.x >= GAME_WIDTH-BALL_DIAMETER)//if ball moves out pf the JPanel along x direction
               {
                score.player1++;
                newPaddles();
                newBall();
                System.out.println("Player 1:"+score.player1);
              }



    }
    public void run() //implementing run method from Runnable instance,this is the part of the code that will be executed and
    //methods defined above will be called accordingly
    {
          //Game loop
          long lastTime = System.nanoTime();// this method returns the current value of the  available system timer, in nanoseconds. 
          //The value returned represents nanoseconds since the game has begun
          double amountofTicks = 60.0;//1 min in seconds
          double ns = 1000000000 / amountofTicks;//
          double delta = 0;
          while(true) {
            long now = System.nanoTime();//time at the moment
            delta +=(now - lastTime)/ns;
            lastTime = now;
            if(delta >=1 ) {
                move();//calling methods defined above 
                checkcollision();
                repaint();
                delta--;
            }
          }
    }
    public class AL extends KeyAdapter//KeyAdapter is an abstract class i.e its obj cannot be created directly,but can be used 
    //only by inheritance(by extending),it has methods responsible for start/stop action (KeyPressed/KeyReleased) of the paddles if certain 
    //keyboard keys are pressed/releaed
    {
        public void keyPressed(KeyEvent e)//keypressed method starts action if a specified key is pressed from keyboard,in this case 
        //when key W,S or up/down arrow is pressed by user paddle's will move
        {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);

        }
        public void keyReleased(KeyEvent e)// KeyReleaed method denotes that the paddles will stop moving if key is released 
         {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }

    }
    
    public void player2() 
{ 
if(ball.x <=0) {
    score.player2++;
if(score.player2 < MAX_SCORE)
{
    System.out.println("Player 2 Wins");
    newPaddles();
    newBall();
}
else
{
    gameRun = false;
}
  }

}


public void player1() 
{ 
if(ball.x <=0) {
    score.player1++;
if(score.player1 < MAX_SCORE)
{
    System.out.println("Player 1 Wins");
    newPaddles();
    newBall();
}
else
{
    gameRun = false;
}
  }

}

}

  
