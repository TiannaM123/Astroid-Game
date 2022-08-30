package animation;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestShip {
    private AbstractAnimation demo = new GameStub();
    private Ship testShip = new Ship(demo);
    int shipHeight= 40;
    int shipWidth= 40;
    int moveAmount = testShip.getMoveAmount();
    
    // Test to see if the up arrow correctly moves the ship
    @Test
    void testNextFrameUp() {
        //asserts that when the ship is created, it's coordinates are in the right position
        assertEquals(450,testShip.getX());
        assertEquals(450,testShip.getY());
        testShip.up();
        //shows that the ship appropriately reflects where it moves in the game
        assertEquals(450,testShip.getX());
        assertEquals(430,testShip.getY());
    }
    
 // Test to see if the up arrow correctly moves the ship multiple times
    @Test
    void testNextFrameUpTwo() {
        //males sure that the ship correctly moves forward with multiple moves up
        assertEquals(450,testShip.getX());
        assertEquals(450,testShip.getY());
        testShip.up();
        testShip.up();
        testShip.up();
        assertEquals(450,testShip.getX());
        assertEquals(390,testShip.getY());
    }
    
    // Test to make sure the ship correctly rotates to the left
    @Test
    void testNextFrameUpLeft() {
        //gets the original angle the ship starts at
        double angle = testShip.getAngle();
        testShip.left();
        //asserts the ship rotates the right amount to the left
        assertEquals(angle+=angle - Math.PI/15,testShip.getAngle());

    }
    
 // Test to make sure the ship correctly rotates to the left
    @Test
    void testNextFrameRight() {
      //gets the original angle the ship starts at
        double angle = testShip.getAngle();
        testShip.right();
      //asserts the ship rotates the right amount to the right
        assertEquals(angle+=angle + Math.PI/15,testShip.getAngle());
    }
    
    // Test to make sure the ship is able to be randomly relocated
    @Test
    void testShift() {
       //uses boolean to make sure the ship randomly relocates at 
       //a place the ship doesn't originally start at
       boolean sameCoordinates = false;
       testShip.shift();
       if(testShip.getX() == 450 && testShip.getY() == 450) {
           sameCoordinates = true;
       }
       assertEquals(false, sameCoordinates);
    }
    
    // Test to make sure the ship wraps around the screen when it gets too close to
    //the edge of the top of the window, and appears at bottom of screen
    @Test
    void testCheckIfEdgeOne() {
        //we are setting the y to the edge of the top window, which is the y at the min edge
        testShip.setY(-moveAmount);
        //make the ship move forward beyond the edge of window
        testShip.up();
        testShip.nextFrame();
        //check that ship has appeared at bottom edge of window
        assertEquals(demo.getWidth()-shipHeight, testShip.getY());
        //makes sure the ship continues to go towards top of window
        testShip.up();
        testShip.nextFrame();
        assertEquals(demo.getWidth()-shipHeight-moveAmount, testShip.getY());
        
    }
    
    // Test to make sure the ship wraps around the screen when it gets too close to
    //the edge of the bottom of the window, and appears at top of screen
    @Test
    void testCheckIfEdgeTwo() {
        //we are setting the y to the edge of the bottom window, which is the y at the max edge
        testShip.setY(demo.getHeight()-shipHeight);
        //rotates the ship so that it is facing the bottom of the window
        testShip.setAngle(Math.PI);
        //make the ship move forward beyond the edge of window
        testShip.up();
        testShip.nextFrame();
        //check that ship has appeared at top edge of window
        assertEquals(0, testShip.getY());
        //makes sure the ship continues to go towards bottom of window
        testShip.up();
        testShip.nextFrame();
        assertEquals(moveAmount, testShip.getY());
        
    }
    // Test to make sure the ship wraps around the screen when it gets too close to the edge
    //of the right side of the window
    @Test
    void testCheckIfEdgeThree() {
        //set angle to a number that will make ship face right side of window
        testShip.setAngle(Math.PI/2);
        //set ship close to right edge of window
        testShip.setX(demo.getHeight()-moveAmount);
        //this gets the ship the most left of the window, so that y equals zero
        testShip.up();
        testShip.nextFrame();
        //makes sure the ship continues to go towards right of window
        testShip.up();
        testShip.nextFrame();
        assertEquals(moveAmount, testShip.getX());   
    }
     //Tests to make sure the ship wraps around the screen when it gets too close to the edge
    //of the left of the window
    @Test
    void testCheckIfEdgeFour() {
        //set angle to a number that will make ship face left side of window
        testShip.setAngle(Math.PI/-2);
        //set ship close to left edge of window
        testShip.setX(moveAmount);
        //this gets the ship at the most right of the window
        testShip.up();
        testShip.nextFrame();
        //makes sure the ship continues to go towards right of window
        testShip.up();
        testShip.nextFrame();
        assertEquals(demo.getWidth()-moveAmount, testShip.getX());         

    }
   
}