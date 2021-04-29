package doodlejump;

import edu.macalester.graphics.CanvasWindow;


/**
 * 
 */
public class DoodleJumpGame {
   public static final int CANVAS_WIDTH = 800;
   public static final int CANVAS_HEIGHT = 800;
   
   private final CanvasWindow canvas;


   public DoodleJumpGame() {
       canvas = new CanvasWindow("Doodle Jump Remastered", CANVAS_WIDTH, CANVAS_HEIGHT);
   }


    public static void main(String[] args) {
       DoodleJumpGame game = new DoodleJumpGame();
       // game.runGame();
    }


    /**
     * 
     */
    public void setupGame() {

    }

    /**
     * 
     */
    public void runGame() {

    }

    /**
     * 
     */
    private void createDoodle() {

    }
}
