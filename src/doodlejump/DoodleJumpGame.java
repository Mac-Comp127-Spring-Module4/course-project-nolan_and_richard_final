package doodlejump;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;

import java.awt.Color;


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
   private CannonBall cannonBall;
   private GraphicsText score;
   private int scoreCount = 0;

   private int lives = 1;


    public DoodleJumpGame() {
       canvas = new CanvasWindow("Doodle Jump Remastered", CANVAS_WIDTH, CANVAS_HEIGHT);
       jumpPadManager = new JumpPadManager(canvas);

       setupGame();
    }


    public static void main(String[] args) {
       DoodleJumpGame game = new DoodleJumpGame();
       game.runGame();
    }


    /**
     * Sets up the initial state of the game by creating a doodle, initial
     * jump pads, score counter, and background. Pauses for 3 seconds.
     */
    public void setupGame() {
        createDoodle(CANVAS_WIDTH/2.0, CANVAS_HEIGHT/2.0);
        jumpPadManager.createJumpPads();

        score = new GraphicsText();
        score.setFont(FontStyle.BOLD, 20);
        score.setCenter(10, 20);
        score.setText("Score: " + scoreCount);
        canvas.add(score);

        canvas.setBackground(new Color(255, 255, 207));

        cannonBall = new CannonBall(10, 600, 50, 45, CANVAS_WIDTH, CANVAS_HEIGHT);
        cannonBall.addToCanvas(canvas);
        
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
            if (jumpPadManager.testHit(doodle)){
                scoreCount++;
                score.setText("Score: " + scoreCount);
            }
            doodle.updatePosition(0.05);
            cannonBall.updatePosition(0.05);

            if (! doodle.updatePosition(0.05)) {
                lives -= 1;
                if (lives == 0) {
                    System.out.println("Game Over. Your score was: " + scoreCount);
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
