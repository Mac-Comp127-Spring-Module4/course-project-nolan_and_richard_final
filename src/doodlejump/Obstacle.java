package doodlejump;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;

import java.awt.Color;


/**
 * The obstacle class creates a obstacle object that
 * the doodle must avoid to not lose. The obstacle is in the
 * shape of an ellipse and is red. 
 * 
 * Acknowledgements: Borrowed from cannonBall.java Nolan Meyer, HW2
 */
public class Obstacle {
    public static final double BALL_RADIUS = 10;
    private double centerX, centerY, initialSpeed, xVelo, yVelo, initialAngle;
    private double maxX, maxY;
    private double newX, newY;
    private Ellipse ballShape;

    public Obstacle(
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
        xVelo = this.initialSpeed * Math.cos(initialAngleRadians);   //initial x velocity
        yVelo = this.initialSpeed * -1 * Math.sin(initialAngleRadians);  //initial y velocity
        
        ballShape = new Ellipse(centerX, centerY, BALL_RADIUS * 2, BALL_RADIUS * 2);  //ellipse set top and left most points
        ballShape.setFilled(true);
        ballShape.setFillColor(new Color(255, 0, 0));
    }

    /**
     * Get's the obstacle's center X location
     * @return double of obstacle's center X location
     */
    public double getCenterX() {
        return centerX;
    }

    /**
     * Get's the obstacle's center Y location
     * @return double of obstacle's center Y location
     */
    public double getCenterY() {
        return centerY;
    }

    /**
     * Update the obstacle's position if it is in bounds
     * @return true if the obstacle is in within the canvas
     */
    public boolean updatePosition(double dt) {
        newX = centerX + (xVelo * dt);
        newY = centerY + (yVelo * dt);
        if((newX <= maxX && newX >= 0) && (newY <= maxY && newY >= 0)){  //if obstacle will still be in the window
            centerX = newX;
            centerY = newY;
            ballShape.setCenter(centerX, centerY);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Tests for intersections between the doodle and this obstacle.
     */
    public boolean intersects(Doodle doodle, CanvasWindow canvas) {
        double x = this.getCenterX();
        double y = this.getCenterY();
        double radius = Obstacle.BALL_RADIUS;
        
        if ((x + radius) >= doodle.getLeftX() && (x - radius) <= (doodle.getLeftX() + Doodle.DOODLE_WIDTH) && (y + radius) >= doodle.getTopY() && (y - radius) <= (doodle.getTopY() + Doodle.DOODLE_WIDTH)) {  //if ball is within the bounds of the doodle
            if((canvas.getElementAt(x + radius + 0.05, y) != null) || (canvas.getElementAt(x - radius - 0.05, y) != null)){  //right or left side of ball test
                return true;
            }
            if((canvas.getElementAt(x, y + radius + 0.05) != null) || (canvas.getElementAt(x, y - radius - 0.05) != null)){  //top or bottom side of ball test
                return true;
            }
            if((canvas.getElementAt(x + radius + 0.05, y - radius - 0.05) != null) || (canvas.getElementAt(x - radius - 0.05, y - radius - 0.05) != null) || 
            (canvas.getElementAt(x + radius + 0.05, y + radius + 0.05) != null) || (canvas.getElementAt(x - radius - 0.05, y + radius + 0.05) != null)) {  //upper right, upper left, bottom right, bottom left corner of ball test
                return true;
            }
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Adds the obstacle's shape to the given canvas.
     */
    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(ballShape);
    }

    /**
     * Removes the obstacle's shape from the given canvas.
     */
    public void removeFromCanvas(CanvasWindow canvas) {
        canvas.remove(ballShape);
    }
}
