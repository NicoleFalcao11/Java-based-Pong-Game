import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*; //43:55

public class Ball extends Rectangle {

    Random random;
    int xVelocity;
    int yVelocity;
    int initialSpeed = 2; //speed of the ball

    Ball(int x, int y, int width, int height) {
        super(x,y,width,height); //calls constructor of parent class
        random = new Random(); // new ball emerges from random direction 
        int randomXDirection = random.nextInt(2);
        if (randomXDirection == 0)
        randomXDirection--; //ball moves towards left
        setXDirection(randomXDirection*initialSpeed);

        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0)
        randomYDirection--; //ball moves towards left
        setYDirection(randomYDirection*initialSpeed);
}

public void setXDirection(int randomXDirection) {
    xVelocity = randomXDirection;
}

public void setYDirection(int randomYDirection) {
    yVelocity = randomYDirection;
}

public void move() {
    x+= xVelocity;
    y+=yVelocity;
}

public void draw(Graphics g) {
    g.setColor(Color.white);
    g.fillOval(x,y,height,width);

}

}
