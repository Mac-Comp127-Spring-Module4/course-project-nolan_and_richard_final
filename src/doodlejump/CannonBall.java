package doodlejump;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;

import java.awt.Color;



public class CannonBall {
    public static final double BALL_GRAVITY = 0;
    public static final double BALL_RADIUS = 10;
    private double centerX, centerY, initialSpeed, xVelo, yVelo, initialAngle;
    private double maxX, maxY;
    private double newX, newY;
    private Ellipse ballShape;

    public CannonBall(
            double centerX,
            double centerY,
            double initialSpeed,
            double initialAngle,
            double maxX,
            double maxY) {

        this.centerX = centerX;
        this.centerY = centerY;
        this.initialSpeed = initialSpeed;
        this.initialAngle = initialAngle;
        this.maxX = maxX;
        this.maxY = maxY;
                
        double initialAngleRadians = Math.toRadians(this.initialAngle);
        xVelo = this.initialSpeed * Math.cos(initialAngleRadians);   // initial x velocity
        yVelo = this.initialSpeed * -1 * Math.sin(initialAngleRadians);  // initial y velocity
        
        ballShape = new Ellipse(centerX, centerY, BALL_RADIUS * 2, BALL_RADIUS * 2);  //ellipse set top and left most points
        ballShape.setFilled(true);
        ballShape.setFillColor(new Color(255, 0, 0));
    }

    /**
     * Get's the cannonball's center X location
     * @return double of cannonball's center X location
     */
    public double getCenterX() {
        return centerX;
    }

    /**
     * Get's the cannonball's center Y location
     * @return double of cannonball's center Y location
     */
    public double getCenterY() {
        return centerY;
    }

    /**
     * Update the cannon ball's position if it is in bounds
     * @return true if the ball is in within the canvas
     */
    public boolean updatePosition(double dt) {
        newX = centerX + (xVelo * dt);
        newY = centerY + (yVelo * dt);
        if((newX <= maxX && newX >= 0) && (newY <= maxY && newY >= 0)){  //if cannonball will still be in the window
            centerX = newX;
            centerY = newY;
            ballShape.setCenter(centerX, centerY);
            yVelo -= (BALL_GRAVITY * dt);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Tests for intersections between the doodle and this cannonball.
     */
    public boolean intersects(Doodle doodle) {
        //test for intersection/ end game if true
        double x = doodle.getLeftX();
        double y = doodle.getTopY();
        double width = Doodle.DOODLE_WIDTH;

        if ((x + width) >= (centerX - BALL_RADIUS) && x <= (centerX + BALL_RADIUS) && (y + width) >= (centerX - BALL_RADIUS) && y <= (centerX + BALL_RADIUS)){  //if doodle is within the cannon ball region
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Adds the cannonball's shape to the given canvas.
     */
    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(ballShape);
    }

    /**
     * Removes the cannonball's shape from the given canvas.
     */
    public void removeFromCanvas(CanvasWindow canvas) {
        canvas.remove(ballShape);
    }
}
