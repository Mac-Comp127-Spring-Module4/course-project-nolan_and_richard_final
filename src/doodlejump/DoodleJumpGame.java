package doodlejump;

import edu.macalester.graphics.CanvasWindow;


/**
 * 
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
     * 
     */
    public void setupGame() {
        createDoodle(CANVAS_WIDTH/2.0, CANVAS_HEIGHT/2.0);
        
        canvas.draw();
        canvas.pause(3000);
    }

    /**
     * 
     */
    public void runGame() {
        canvas.onMouseMove(doodle::moveDoodle);

        canvas.animate(() -> {
            doodle.updatePosition(0.05);
        });
    }

    /**
     * 
     */
    private void createDoodle(double leftX, double topY) {
        doodle = new Doodle(leftX, topY);
        doodle.addToCanvas(canvas);
    }
}
