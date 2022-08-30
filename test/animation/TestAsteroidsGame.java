package animation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests the following methods in AsteroidsGame: checkLevelChange,
 * checkRespawn, and checkAsteroids.
 */
class TestAsteroidsGame {
    
    private AsteroidsGame game = new AsteroidsGame();

    @BeforeEach
    void setUp() throws Exception {
        game.restart();
    }

    @Test
    void testLevelChange() {
        // Tests that the game's difficulty changes once all
        // asteroids are destroyed
        
        // get existing asteroids from game
        List<Asteroid> asteroids = game.getAsteroids();
        
        // destroy all asteroids
        for (Asteroid asteroid : asteroids) {
            asteroid.destroy();
        }

        game.checkLevelChange();
        
        // Level should be increased by .5 since all asteroids are destroyed
        assertEquals(0.5, game.getLevel());
        
    }
    
    @Test
    void testCheckRespawnFalse() {
        // Tests if method checkRespawn changes canRespawn
        // boolean to false when an Asteroid intersects the respawn
        // point.
        
        // get existing asteroids from game
        List<Asteroid> asteroids = game.getAsteroids();
        
        // Create asteroid on respawn point
        Asteroid intersectingAsteroid = new Asteroid(game, 1, 450, 450);
        
        // Add intersecting asteroid to game's asteroids list
        asteroids.add(intersectingAsteroid);
        
        // Update game's asteroids list
        game.setAsteroids(asteroids);
        
        game.checkRespawn();
        
        // canRespawn variable should be false since there is an 
        // asteroid on the respawn point
        assertEquals(false, game.getCanRespawn());
    }
    
    @Test
    void testCheckRespawnTrue() {
        // Tests if method checkRespawn changes canRespawn
        // boolean to true and reinitializes ship afterwards
        
        // Change these variables from their default values
        // to the values they would be when running checkRespawn
        game.setIsDestroyed(true);
        game.setCanRespawn(false);
        
        
        game.checkRespawn();
        
        // After checkRespawn is called and there are no
        // intersecting asteroids, canRespawn should equal true
        // and Ship isDestroyed should be false
        assertEquals(true, game.getCanRespawn());
        assertEquals(false, game.getIsDestroyed());
    }
    
    @Test
    void testShipCollision() {
        // Tests checkAsteroids method for appropriate
        // variable changes after Asteroid has collided with Ship.
        
        // get existing asteroids from game
        List<Asteroid> asteroids = game.getAsteroids();
        
        // Create asteroid on default ship location
        Asteroid intersectingAsteroid = new Asteroid(game, 1, 450, 450);
        
        // Add intersecting asteroid to game's asteroids list
        asteroids.add(intersectingAsteroid);
        
        // Update game's asteroids list
        game.setAsteroids(asteroids);
        
        game.checkAsteroids();
        
        // Lives should be decreased by 1
        assertEquals(2, game.getLives());
        // Ship isDestroyed should be true
        assertEquals(true, game.getIsDestroyed());
        // canRespawn should be false
        assertEquals(false, game.getCanRespawn());
    }
    
    @Test
    void testEndGame() {
        // Tests checkAsteroids method for apprpriate
        // variable changes after all lives are lost
        
        // Set lives to 1
        game.setLives(1);
        
        // get existing asteroids from game
        List<Asteroid> asteroids = game.getAsteroids();
        
        // Create asteroid on default ship location
        Asteroid intersectingAsteroid = new Asteroid(game, 1, 450, 450);
        
        // Add intersecting asteroid to game's asteroids list
        asteroids.add(intersectingAsteroid);
        
        // Update game's asteroids list
        game.setAsteroids(asteroids);
        
        game.checkAsteroids();
        
        // Animation should stop once lives have reached 0
        assertEquals(false, game.getMoving());
        
    }
    
    @Test
    void testShotCollision() {
        // Tests checkAsteroids method for appropriate
        // variable changes once Shot intersects an asteroid
        
        // Create shot in same location as Asteroid
        Shot shot = new Shot(game, 300, 300, 45, 45);
        game.setShot(shot);

        game.setShooting(true);
        
        // get existing asteroids from game
        List<Asteroid> asteroids = game.getAsteroids();
        
        // Create asteroid on same location as Shot
        Asteroid intersectingAsteroid = new Asteroid(game, 1, 300, 300);
        
        // Add intersecting asteroid to game's asteroids list
        asteroids.add(intersectingAsteroid);
        
        // Update game's asteroids list
        game.setAsteroids(asteroids);
        
        game.checkAsteroids();
        
        // Shooting should be set to false
        assertEquals(false, game.getShooting());
        // Score should increase by 100
        assertEquals(100, game.getScore());
        
    }

}
