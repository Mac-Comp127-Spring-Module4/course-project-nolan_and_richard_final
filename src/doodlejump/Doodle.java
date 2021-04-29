package doodlejump;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.MouseMotionEvent;

public class Doodle {
    private Rectangle doodleShape;

    public static final double DOODLE_WIDTH = 25;
    public static final double GRAVITY = -9.8;

    private double leftX, rightX, topY, bottomY;
    private double yVelo;


    public Doodle(double leftX, double topY) {
        this.leftX = leftX;
        rightX = leftX + DOODLE_WIDTH;
        this.topY = topY;
        bottomY = topY + DOODLE_WIDTH;


        doodleShape = new Rectangle(leftX, topY, DOODLE_WIDTH, DOODLE_WIDTH);

        doodleShape.setFilled(true);
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
     * Update the doodle's position if it is in bounds
     * @return true if the ball is in within the window
     */
    public boolean updatePosition(double dt) {
        double newY;
        newY = topY + (yVelo * dt);
        if(newY <= DoodleJumpGame.CANVAS_HEIGHT && newY >= 0){  //if doodle will still be in the window
            topY = newY;
            doodleShape.setPosition(leftX, topY);
            yVelo -= (GRAVITY * dt);
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
            rightX = mouseCursor.getPosition().getX() + DOODLE_WIDTH;
        }
        if (mouseCursor.getPosition().getX() < doodleShape.getX()){
            doodleShape.setPosition(mouseCursor.getPosition().getX(), topY);
            leftX = mouseCursor.getPosition().getX();
            rightX = mouseCursor.getPosition().getX() + DOODLE_WIDTH;
        }
        if (mouseCursor.getPosition().getX() <= 0){  //left-bound
            doodleShape.setPosition(0, topY);
            leftX = 0;
            rightX = DOODLE_WIDTH;
        }
        if (mouseCursor.getPosition().getX() >= DoodleJumpGame.CANVAS_WIDTH - DOODLE_WIDTH){  //right-bound
            doodleShape.setPosition(DoodleJumpGame.CANVAS_WIDTH - DOODLE_WIDTH, topY);
            leftX = DoodleJumpGame.CANVAS_WIDTH - DOODLE_WIDTH;
            rightX = DoodleJumpGame.CANVAS_WIDTH;
        }
    }
}
