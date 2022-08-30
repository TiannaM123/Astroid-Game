package animation;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import animation.AbstractAnimation;
import animation.AnimatedObject;

/**
 *This class holds the creation, painting, and movement of the ship object
 *this class creates the ship, initializing it at the middle of the screen
 *it also holds the movement controls for when the user presses certain keys
 *the ship can also wrap around the screen
 */
public class Ship extends JPanel implements AnimatedObject {
    Random random = new Random();

    // The animation that this object is part of.
    private AbstractAnimation animation;

    // The shape that is drawn
    private Polygon ship;
    
    // the height of the ship
    private final double SHIP_HEIGHT;
    
    // the width of the ship
    private final double SHIP_WIDTH;

    // The left edge of the shape
    private int x;

    // The top edge of the shape
    private int y;
    
    //The angle the ship will be at when the ship rotates
    private double angle;
    
    //angle at which the ship moves on the x axis
    private double dirX;
    
    //angle at which the ship moves on the y axis
    private double dirY;
    
    //number of pixels the ship travels when going forward
    private int moveAmount = 20;
    
  
    /**
    * Constructs a triangle for the ship
    * @param animation the ship will be apart of
    */
    public Ship (AbstractAnimation animation) {
        this.animation = animation;
        //gets the three points for making the triangle
        ship = new Polygon();
        ship.addPoint(10, 20);
        ship.addPoint(0, -20);
        ship.addPoint(-10, 20);
            
        //initialization of dirX and dirY variables
        dirX = 0;
        dirY = 1;
    
        //holds the place the ship will originally appear in middle of screen
        x = 450;
        y = 450;
        
      //variables used when identifying if ship is out of frame
      SHIP_HEIGHT = ship.getBounds2D().getHeight();
      SHIP_WIDTH = ship.getBounds2D().getWidth();
    }


    /**
     * Draws the triangle
     * @param g the graphics context to draw on
     */
    public void paint(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.draw(getShape());
    }



    /**
     * When the user clicks the up arrow button
     * the ship will move forward
     */
    public void up() {
        x+=getXDirection() * 2;
        y+=getYDirection() * 2;
    }

    /**
     * when the user clicks the right arrow button, it rotates the ship to the right
     *
     */
    public void right() {
        //this gets the angle in which the ship will rotate at
        angle = angle + Math.PI/15;
        //reflects the changes made to the ship
        getShape();
    }

    /**
     * when the user clicks the left arrow button, the ship rotates to the left
     */
    public void left() {
       //this gets the angle in which the ship will rotate at
       angle = angle - Math.PI/15;
       //reflects the changes made to the ship
       getShape();
    }

    /**
     * When the space key is pressed, we call the shoot method to shoot at asteroids
     * @return shot object 
     */
    public Shot space() {
        //we are passing coordinates so that the shot object appears where the ship is
      Shot shot = new Shot(animation, x, y, getXDirection(), getYDirection());
      
      return shot;
    }

    /**
     * When the shift key is pressed, hyper speed is activated
     */
    public void shift() {
        //this generates the random place on the x axis hyperspeed takes the ship
        int ranX = random.nextInt(590);
        //this generates the random place on the y axis hyperspeed takes the ship
        int ranY = random.nextInt(590);
        x = ranX;
        y = ranY;
    }
    

    @Override
    /**
     * Updates the animated object for the next frame of the animation
     * and repaints the window.
     */
    //@Override
    public void nextFrame() {
        getShape();
        checkIfEdge();
        repaint();
        
    }
    
    /**
     * checks to see if ship is over edges of window: wraps around to other
     * side if it is
     */
    private void checkIfEdge() {
        // Check if the right edge of the ship is beyond the right
        // edge of the window. If it is, move it to the left edge
        if (x + SHIP_WIDTH > animation.getWidth()) {
            x = 0;
        }
        // Check if the left edge of the ship is beyond the left
        // edge of the window. If it is, move it to the right edge
        else if (x < 0) {
            x = (int) (animation.getWidth() - SHIP_WIDTH);
        }

        // Check if bottom edge of the ship is below the
        // edge of the window. If it is, put it at the top

        if (y + SHIP_HEIGHT > animation.getHeight()) {
            y = 0;
        }
        // check to see if top edge of ship is above
        // the top of the window. If it is, move to the bottom
        else if (y < 0) {
            y = (int) (animation.getHeight() - SHIP_HEIGHT);
        }
    }
    
      /**
       * Returns the shape after applying the current translation
       * and rotation
       * @return the shape located as we want it to appear
       */
      public Shape getShape() {
          // AffineTransform captures the movement and rotation we
          // want the shape to have
          AffineTransform at1 = new AffineTransform();    
          at1.translate(x, y);
          
          // Rotates the ship by the inputed angle, and sets the direction
          //the ship will move forward in
          at1.rotate(angle);
          double dirX2 = (dirX*Math.cos(angle))-(dirY*Math.sin(angle));
          double dirY2 = (dirX*Math.sin(angle))+ (dirY*Math.cos(angle));
          dirX= dirX2;
          dirY=dirY2;
          //set the new AffineTransform object 
          AffineTransform at2 = at1;
          
          // Create a shape that looks like our triangle, but centered
          // and rotated as specified by the AffineTransform object.
              return at2.createTransformedShape(ship);
              
      

      }
      
      /**
       * Returns the direction of x 
       * @return the angle the x axis moves by
       */
      public double getXDirection(){
        //gives us the angle the ship will move in for the x direction
          dirX = 10* Math.sin(angle);
          return dirX;
      }
      
      /**
       * Returns the direction of y
       * @return the angle the y axis moves by
       */
      public double getYDirection(){
        //gives us the angle the ship will move in for the y direction
          dirX = 10* Math.sin(angle);
          dirY = -10* Math.cos(angle);
          return dirY;
      }
      
      /**
       * Called by game class when ship is hit by asteroid,
       * resets the ship object to disappear on screen
       */
      public void destroy() {
          ship.reset();
      }
     
      /**
       * Returns the where the ship is on the x axis
       * @return the placement of x
       * primarily used for JUnit testing
       */
      public int getX() {
          return x;
      }
      
      /**
       * Returns the where the ship is on the y axis
       * @return the placement of y
       * primarily used for JUnit testing
       */
      public int getY() {
          return y;
      }
      
      /**
       * sets the angle in which the ship rotates to
       * primarily used for JUnit testing
       * @param number to set angle to
       */
      public void setAngle(double number) {
          angle = number;
      }
      
      /**
       * Returns the angle in which the ship rotates to
       * @return the angle the ship is rotated at
       * primarily used for JUnit testing
       */
      public double getAngle() {
          return angle;
      }
      
      /**
       * Sets where the ship is on the x axis
       * Primarily used for testing
       * @param number to set x to
       */
      public void setX(int number) {
          x=number;
      }
      
      /**
       * Sets where the ship is on the y axis
       * Primarily used for testing
       * @param number to set y to
       */
      public void setY(int number) {
          y=number;
      
      }
      /**
       * returns the amount of pixels the ship travels when it moves
       * Primarily used for testing
       * @return moveAmount 
       */
      public int getMoveAmount() {
          return moveAmount;
      }
}
}
