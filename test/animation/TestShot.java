package animation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests a Shot object's nextFrame method.
 */
class TestShot {
    
    private AbstractAnimation demo = new GameStub();
    
    private static final int X_POSITION = 450;
    private static final int Y_POSITION = 450;
    
    private static final double X_DIRECTION = 8.5;
    private static final double Y_DIRECTION = -5.2;
    
    private Shot shot = new Shot(demo, X_POSITION, Y_POSITION, X_DIRECTION, Y_DIRECTION);


    @Test
    void testNextFrame() {
        // Tests nextFrame method for
        // correct changes in x and y movement
        
        int correctX = (int) (X_POSITION + (shot.getSpeed() * X_DIRECTION));
        int correctY = (int) (Y_POSITION + (shot.getSpeed() * Y_DIRECTION));
        
        shot.nextFrame();
        
        assertEquals(correctX, shot.getX());
        assertEquals(correctY, shot.getY());
    }

}
