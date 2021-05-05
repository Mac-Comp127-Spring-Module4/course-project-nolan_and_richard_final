package doodlejump;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;


/**
 * 
 */
public class JumpPad {
    private Rectangle jumpPadShape;
    public static final double JUMP_PAD_LENGTH = 60;
    public static final double JUMP_PAD_WIDTH = 7.5;
    private double leftX, rightX, topY, bottomY;

    public JumpPad(double leftX, double topY) {
        this.leftX = leftX;
        rightX = leftX + JUMP_PAD_LENGTH;
        this.topY = topY;
        bottomY = topY + JUMP_PAD_WIDTH;
        
        jumpPadShape = new Rectangle(leftX, topY, JUMP_PAD_LENGTH, JUMP_PAD_WIDTH);
        
        jumpPadShape.setFilled(true);
    }
    

    /**
    * Adds the jump pad's shape to the given canvas.
    * @param canvas
    */
    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(jumpPadShape);
    }

    /**
    * Removes the jump pad's shape from the given canvas.
    * @param canvas
    */
    public void removeFromCanvas(CanvasWindow canvas) {
        canvas.remove(jumpPadShape);
    }

    /**
     * Tests for intersections between the doodle and this jump pad.
     */
    public boolean intersects(Doodle doodle) {
        double x = doodle.getLeftX();
        double y = doodle.getTopY();
        double width = Doodle.DOODLE_WIDTH;

        if ((x + width) >= leftX && x <= rightX && (y + width) >= topY && y <= bottomY && doodle.getYVelo() > 0){  //if doodle is within the jump pad region and falling down
            doodle.changeYVelo();
            return true;
        }
        else {
            return false;
        }
    }
}
