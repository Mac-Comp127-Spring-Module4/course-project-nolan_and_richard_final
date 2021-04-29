package doodlejump;

import edu.macalester.graphics.CanvasWindow;


/**
 * The game of "Doodle Jump Remastered". Creates the canvas window, adds the supporting 
 * elements (doodle, jump pads) to the window, and then animates/runs the game until the 
 * doodle hits the bottom of the screen and the user loses. 
 */
public class DoodleJumpGame {
   public static final int CANVAS_WIDTH = 800;
   public static final int CANVAS_HEIGHT = 800;
   
   private final CanvasWindow canvas;
   private Doodle doodle;
   private JumpPadManager jumpPadManager;

   private int lives = 1;


    public DoodleJumpGame() {
       canvas = new CanvasWindow("Doodle Jump Remastered", CANVAS_WIDTH, CANVAS_HEIGHT);

       setupGame();
    }


    public static void main(String[] args) {
       DoodleJumpGame game = new DoodleJumpGame();
       game.runGame();
    }


    /**
     * Sets up the initial state of the game by creating a doodle and initial
     * jump pads. Pauses for 3 seconds.
     */
    public void setupGame() {
        createDoodle(CANVAS_WIDTH/2.0, CANVAS_HEIGHT/2.0);
        
        canvas.draw();
        canvas.pause(3000);
    }

    /**
     * Handles the animaton of the doodle and jump pads. Exits the game if the
     * doodle hits the bottom of the canvas window (loss).
     */
    public void runGame() {
        canvas.onMouseMove(doodle::moveDoodle);

        canvas.animate(() -> {
            doodle.updatePosition(0.05);

            if (! doodle.updatePosition(0.05)) {
                lives -= 1;
                if (lives == 0) {
                    System.exit(0);
                }
            }
        });
    }

    /**
     * Creates a doodle.
     * @param leftX The left X location of where the doodle will be placed
     * @param topY The top Y location of where the doodle will be placed
     */
    private void createDoodle(double leftX, double topY) {
        doodle = new Doodle(leftX, topY);
        doodle.addToCanvas(canvas);
    }
}
