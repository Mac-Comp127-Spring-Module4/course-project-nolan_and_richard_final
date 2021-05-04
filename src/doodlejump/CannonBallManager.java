package doodlejump;

import edu.macalester.graphics.CanvasWindow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class CannonBallManager {
    private CanvasWindow canvas;
    private List<CannonBall> cannonBalls;
    private Random rand = new Random();

    public CannonBallManager(CanvasWindow canvas) {
        cannonBalls = new ArrayList<>();
        this.canvas = canvas;
    }


    /**
     * 
     */
    public void createCannonBalls() {
        int numBalls = rand.nextInt(3)+1;
        double y = 400;
        double speed = 40;
        double angle = 0;
        
        for (int i = 0; i < numBalls; i++) {
            if (rand.nextBoolean()){
                y += rand.nextInt(60);
            }
            else {
                y -= rand.nextInt(30);
            }
            if (rand.nextBoolean()){
                speed += rand.nextInt(15);
            }
            else {
                speed -= rand.nextInt(10);
            }
            if (rand.nextBoolean()){
                angle += rand.nextInt(10);
            }
            else {
                angle -= rand.nextInt(10);
            }

            CannonBall cannonBall = new CannonBall(0, y, speed, angle, DoodleJumpGame.CANVAS_WIDTH, DoodleJumpGame.CANVAS_HEIGHT);
            cannonBall.addToCanvas(canvas);
            cannonBalls.add(cannonBall);
        }
    }

    /**
     * 
     * @param dt
     */
    public void updatePostition(double dt){
        for (Iterator<CannonBall> iterator = cannonBalls.iterator(); iterator.hasNext();) {
            CannonBall cb = iterator.next();
            if (cb.updatePosition(dt)) {
                cb.updatePosition(dt);
            }
            else {
                cb.removeFromCanvas(canvas);
                iterator.remove();
            }
        }
        if (cannonBalls.isEmpty()){
            createCannonBalls();
        }
    }

    /**
     * 
     * @param doodle
     * @return
     */
    public boolean testHit(Doodle doodle) {
        for (Iterator<CannonBall> iterator = cannonBalls.iterator(); iterator.hasNext();) {
            CannonBall cb = iterator.next();
            if (cb.intersects(doodle)) {
                return true;
            }
        }
        return false;
    }
}
