package doodlejump;

import edu.macalester.graphics.CanvasWindow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 * The cannonBallManager controls all of the cannonBall objects,
 * namely their creation, removal, movement, and checking for intersections
 * with the doodle.
 */
public class CannonBallManager {
    private CanvasWindow canvas;
    private List<CannonBall> cannonBalls;
    private Random rand = new Random();

    public CannonBallManager(CanvasWindow canvas) {
        cannonBalls = new ArrayList<>();
        this.canvas = canvas;
    }


    /**
     * Creates a random amount of cannon balls from 1-3 with varying speeds,
     * varying angles, and varying start points. Adds them to the canvas and 
     * list of cannon balls.
     */
    public void createCannonBalls() {
        int numBalls = rand.nextInt(3)+1;
        double y = 350;
        double speed = 40;
        double angle = 0;
        
        for (int i = 0; i < numBalls; i++) {
            if (rand.nextBoolean()){
                y += rand.nextInt(25);
            }
            else {
                y -= rand.nextInt(25);
            }
            if (rand.nextBoolean()){
                speed += rand.nextInt(15);
            }
            else {
                speed -= rand.nextInt(15);
            }
            if (rand.nextBoolean()){
                angle += rand.nextInt(5);
            }
            else {
                angle -= rand.nextInt(5);
            }

            CannonBall cannonBall = new CannonBall(0, y, speed, angle, DoodleJumpGame.CANVAS_WIDTH, DoodleJumpGame.CANVAS_HEIGHT);
            cannonBall.addToCanvas(canvas);
            cannonBalls.add(cannonBall);
        }
    }

    /**
     * Updates all of the cannon ball's positions if they are within
     * the canvas width. Removes them if they are outside the canvas.
     * Calls to create new cannon balls if all are off the screen.
     * @param dt a time step
     * 
     * Acknowledgements: This method was created with help from the following website:
     * https://stackoverflow.com/questions/8104692/how-to-avoid-java-util-concurrentmodificationexception-when-iterating-through-an
     */
    public void updatePostition(double dt){
        for (Iterator<CannonBall> iterator = cannonBalls.iterator(); iterator.hasNext();) {
            CannonBall cb = iterator.next();
            if (cb.updatePosition(dt)) { //if within canvas
                cb.updatePosition(dt);
            }
            else {
                cb.removeFromCanvas(canvas);
                iterator.remove();
            }
        }
        if (cannonBalls.isEmpty()){  //if there are no more cannon balls, create new ones
            createCannonBalls();
        }
    }

    /**
     * Tests to see if any of the cannon balls intersect with the doodle.
     * @param doodle
     * @return true if they intersect
     * 
     * Acknowledgements: This method was created with help from the following website:
     * https://stackoverflow.com/questions/8104692/how-to-avoid-java-util-concurrentmodificationexception-when-iterating-through-an
     */
    public boolean testHit(Doodle doodle) {
        for (Iterator<CannonBall> iterator = cannonBalls.iterator(); iterator.hasNext();) {
            CannonBall cb = iterator.next();
            if (cb.intersects(doodle, canvas)) {
                return true;
            }
        }
        return false;
    }
}
