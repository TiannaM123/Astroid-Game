package animation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import animation.demo.AnimationDemo;
import animation.demo.AnimationStub;

/**
 * Tests Asteroid object's nextFrame and score methods.
 */
class TestAsteroid {
    private AbstractAnimation demo = new GameStub();
    private Asteroid test=new Asteroid(demo);

    @BeforeEach
    void setUp() throws Exception {
        //sets position to 100,100
        test.setX(100);
        test.setY(100);
        //set movement to 1,1
        test.setXmove(1);
        test.setYmove(1);
    }

    
    
   //test to see if correct x function is done for direction
    @Test
    void testNextFrameXMove() {
        //x should increase by x_move if true
        test.setXDir(true);
        test.nextFrame();
        assertEquals(101, test.getX());
        //x should decrease by x_move if false
        test.setXDir(false);
        test.nextFrame();
        assertEquals(100, test.getX());
    }
    
   //test to see if correct y function is done for direction
    @Test
    void testNextFrameYMove() {
      //y should increase by y_move if true
        test.setYDir(true);
        test.nextFrame();
        assertEquals(101, test.getY());
      //y should decrease by y_move if false
        test.setYDir(false);
        test.nextFrame();
        assertEquals(100, test.getY());
    }
  //test for scroll back on screen
    @Test
    void testNextFrameEdge() {
        //reach max edges when increasing
        test.setXDir(true);
        test.setYDir(true);
        //set x at max edge
        test.setX(1001-test.getWidth());
        test.nextFrame();
        assertEquals(1, test.getX());
        //set y at max edge
        test.setY(1001-test.getHeight());
        test.nextFrame();
        assertEquals(1, test.getY());
        //set x and y at max edge
        test.setX(1001-test.getWidth());
        test.setY(1001-test.getHeight());
        test.nextFrame();
        assertEquals(1, test.getX());
        assertEquals(1, test.getY());
        
        //reach min edges when decreasing
        test.setXDir(false);
        test.setYDir(false);
        //set x at min edge
        test.setX(-1);
        test.nextFrame();
        assertEquals(999-test.getWidth(), test.getX());
        //set y at min edge
        test.setY(-1);
        test.nextFrame();
        assertEquals(999-test.getHeight(), test.getY());
        //set x and y at min edge
        test.setX(-1);
        test.setY(-1);
        test.nextFrame();
        assertEquals(999-test.getWidth(), test.getX());
        assertEquals(999-test.getHeight(), test.getY());
    }
    //test to see if correct score is returned for set size
    @Test
    void testScore() {
        //small is worth 100
        test.setSize(1);
        assertEquals(100, test.score());
        //medium is worth 50
        test.setSize(2);
        assertEquals(50, test.score());
        //large is worth 20
        test.setSize(3);
        assertEquals(20, test.score());
    }


    

    
   
}
