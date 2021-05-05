package doodlejump;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.MouseMotionEvent;


/**
 * 
 */
public class Doodle {
    private Rectangle doodleShape;

    public static final double DOODLE_WIDTH = 25;
    public static final double GRAVITY = -25;

    private double leftX, topY;
    private double yVelo = -75;


    public Doodle(double leftX, double topY) {
        this.leftX = leftX;
        this.topY = topY;

        doodleShape = new Rectangle(leftX, topY, DOODLE_WIDTH, DOODLE_WIDTH);
        doodleShape.setFillColor(Color.GREEN);
    }


    /**
    * Adds the doodle's shape to the given canvas.
    * @param canvas
    */
    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(doodleShape);
    }

    /**
    * Removes the doodle's shape from the given canvas. 
    * @param canvas
    */
    public void removeFromCanvas(CanvasWindow canvas) {
        canvas.remove(doodleShape);
    }

    /**
     * Gets the doodle's left X location
     * @return double of doodle's left X location
     */
    public double getLeftX() {
        return leftX;
    }

    /**
     * Gets the doodle's top Y location
     * @return double of doodle's top Y location
     */
    public double getTopY() {
        return topY;
    }

    /**
     * Get's the doodle's y velocity
     * @return double of doodle's y velocity
     */
    public double getYVelo() {
        return yVelo;
    }


    /**
     * Resets the y velocity of the doodle
     */
    public void changeYVelo() {
        yVelo = -130;
    }

    /**
     * Update the doodle's position if it is in bounds
     * @return true if the doodle is in within the window
     */
    public boolean updatePosition(double dt) {
        double newY;
        newY = topY + (yVelo * dt);
        if(newY <= DoodleJumpGame.CANVAS_HEIGHT){  // if doodle doesn't touch the bottom of the window
            topY = newY;
            yVelo -= (GRAVITY * dt);
            doodleShape.setPosition(leftX, topY);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Moves the doodle in accordance to the mouse location. Makes sure the doodle
     * stays within the width of the canvas.
     * @param mouseCursor
     */
    public void moveDoodle(MouseMotionEvent mouseCursor) {
        if (mouseCursor.getPosition().getX() > doodleShape.getX()){
            doodleShape.setPosition(mouseCursor.getPosition().getX(), topY);
            leftX = mouseCursor.getPosition().getX();
        }
        if (mouseCursor.getPosition().getX() < doodleShape.getX()){
            doodleShape.setPosition(mouseCursor.getPosition().getX(), topY);
            leftX = mouseCursor.getPosition().getX();
        }
        if (mouseCursor.getPosition().getX() <= 0){  //left-bound
            doodleShape.setPosition(0, topY);
            leftX = 0;
        }
        if (mouseCursor.getPosition().getX() >= DoodleJumpGame.CANVAS_WIDTH - DOODLE_WIDTH){  //right-bound
            doodleShape.setPosition(DoodleJumpGame.CANVAS_WIDTH - DOODLE_WIDTH, topY);
            leftX = DoodleJumpGame.CANVAS_WIDTH - DOODLE_WIDTH;
        }
    }
}
