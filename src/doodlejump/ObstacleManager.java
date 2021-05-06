package doodlejump;

import edu.macalester.graphics.CanvasWindow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 * The obstacleManager controls all of the obstacle objects,
 * namely their creation, removal, movement, and checking for intersections
 * with the doodle.
 */
public class ObstacleManager {
    private CanvasWindow canvas;
    private List<Obstacle> obstacles;
    private Random rand = new Random();

    public ObstacleManager(CanvasWindow canvas) {
        obstacles = new ArrayList<>();
        this.canvas = canvas;
    }


    /**
     * Creates a random amount of obstacles from 1-3 with varying speeds,
     * varying angles, and varying start points. Adds them to the canvas and 
     * list of obstacles.
     */
    public void createObstacles() {
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

            Obstacle obstacle = new Obstacle(0, y, speed, angle, DoodleJumpGame.CANVAS_WIDTH, DoodleJumpGame.CANVAS_HEIGHT);
            obstacle.addToCanvas(canvas);
            obstacles.add(obstacle);
        }
    }

    /**
     * Updates all of the obstacle's positions if they are within
     * the canvas width. Removes them if they are outside the canvas.
     * Calls to create new obstacles if all are off the screen.
     * @param dt a time step
     * 
     * Acknowledgements: This method was created with help from the following website:
     * https://stackoverflow.com/questions/8104692/how-to-avoid-java-util-concurrentmodificationexception-when-iterating-through-an
     */
    public void updatePostition(double dt){
        for (Iterator<Obstacle> iterator = obstacles.iterator(); iterator.hasNext();) {
            Obstacle cb = iterator.next();
            if (cb.updatePosition(dt)) { //if within canvas
                cb.updatePosition(dt);
            }
            else {
                cb.removeFromCanvas(canvas);
                iterator.remove();
            }
        }
        if (obstacles.isEmpty()){  //if there are no more obstacles, create new ones
            createObstacles();
        }
    }

    /**
     * Tests to see if any of the obstacles intersect with the doodle.
     * @param doodle
     * @return true if they intersect
     * 
     * Acknowledgements: This method was created with help from the following website:
     * https://stackoverflow.com/questions/8104692/how-to-avoid-java-util-concurrentmodificationexception-when-iterating-through-an
     */
    public boolean testHit(Doodle doodle) {
        for (Iterator<Obstacle> iterator = obstacles.iterator(); iterator.hasNext();) {
            Obstacle cb = iterator.next();
            if (cb.intersects(doodle, canvas)) {
                return true;
            }
        }
        return false;
    }
}
