import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*; 

public class Paddle extends Rectangle {

    int id; // player 1 or 2
    int yVelocity; //speed of the paddle 
    int speed=10;
    public static boolean stop = false;

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {

        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT); //calls constructor of the parent class
        this.id=id; //player 1 or 2

    }

    public void keyPressed(KeyEvent e) {

        switch (id) {

            case 1: // player 1
            if(e.getKeyCode()==KeyEvent.VK_W) {
                setYDirection(-speed); //W moves paddle 1 upwards by 10 pixels
                move();
            }
            if(e.getKeyCode()==KeyEvent.VK_S) {
                setYDirection(speed); //S moves paddle 1 downwards by 10 pixels
                move();
            }
            break;

            case 2:
            if(e.getKeyCode()==KeyEvent.VK_UP) {
                setYDirection(-speed); //UP moves paddle 2 upwards by 10 pixels
                move();
            }
            if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                setYDirection(speed); //DOWN moves paddle 2 downwards by 10 pixels
                move();
            }
            break;
        }
    }

    public void keyReleased(KeyEvent e) {

        switch (id) {

            case 1:
            if(e.getKeyCode()==KeyEvent.VK_W) {
                setYDirection(0); // 0 makes paddle stop moving
                move();
            }
            if(e.getKeyCode()==KeyEvent.VK_S) {
                setYDirection(0);  // 0 makes paddle stop moving
                move();
            }
            break;

            case 2:
            if(e.getKeyCode()==KeyEvent.VK_UP) {
                setYDirection(0); // 0 makes paddle stop moving
                move();
            }
            if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                setYDirection(0); // 0 makes paddle stop moving
                move();
            }
            break;
        }

    }

    public void setYDirection(int yDirection) {

        yVelocity=yDirection; 

    }

    public void move() {

        y= y + yVelocity; 
    }

    public void draw(Graphics g) {

        if(id==1)
        g.setColor(Color.blue);
        else
        g.setColor(Color.red);

        g.fillRect(x,y,width,height);

    }
    
}
