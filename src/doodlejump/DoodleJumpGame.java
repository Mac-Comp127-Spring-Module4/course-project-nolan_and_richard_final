package doodlejump;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;

import java.awt.Color;


/**
 * The game of "Doodle Jump Remastered". Creates the canvas window, adds the supporting 
 * elements (doodle, jump pads, cannonballs) to the window, and then animates/runs the game until the 
 * doodle hits the bottom of the screen or a cannonball and the user loses. Keeps track of the score
 * in the upper left corner.
 */
public class DoodleJumpGame {
   public static final int CANVAS_WIDTH = 800;
   public static final int CANVAS_HEIGHT = 800;
   
   private final CanvasWindow canvas;
   private Doodle doodle;
   private JumpPadManager jumpPadManager;
   private CannonBallManager cannonBallManager;
   private GraphicsText score;
   private GraphicsText intro;
   private int scoreCount = 0;

   private int lives = 1;


    public DoodleJumpGame() {
       canvas = new CanvasWindow("Doodle Jump Remastered", CANVAS_WIDTH, CANVAS_HEIGHT);
       jumpPadManager = new JumpPadManager(canvas);
       cannonBallManager = new CannonBallManager(canvas);

       setupGame();
    }


    public static void main(String[] args) {
       DoodleJumpGame game = new DoodleJumpGame();
       game.runGame();
    }


    /**
     * Sets up the initial state of the game by creating a doodle, initial
     * jump pads, score counter, and background. Displays intro message.
     * Pauses for 7 seconds.
     */
    public void setupGame() {
        intro = new GraphicsText();
        intro.setFont(FontStyle.BOLD, 20);
        intro.setCenter(25, 200);
        intro.setText("Control the doodle with your mouse to land on the black jump pads (+1). \n Avoid falling off or hitting the red obstacles, if you do, you lose.");
        canvas.add(intro);

        createDoodle(CANVAS_WIDTH/2.0, CANVAS_HEIGHT/2.0);
        jumpPadManager.createJumpPads();
        cannonBallManager.createCannonBalls();

        score = new GraphicsText();
        score.setFont(FontStyle.BOLD, 20);
        score.setCenter(10, 20);
        score.setText("Score: " + scoreCount);
        canvas.add(score);

        canvas.setBackground(new Color(255, 255, 207));
        
        canvas.draw();
        canvas.pause(7000);
        canvas.remove(intro);
    }

    /**
     * Handles the animaton of the doodle and jump pads. Exits the game if the
     * doodle hits the bottom of the canvas window or an obstacle (loss).
     */
    public void runGame() {
        canvas.onMouseMove(doodle::moveDoodle);

        canvas.animate(() -> {
            if (jumpPadManager.testHit(doodle)){
                scoreCount++;
                score.setText("Score: " + scoreCount);
            }
            doodle.updatePosition(0.05);
            cannonBallManager.updatePostition(0.05);

            if ((!doodle.updatePosition(0.05)) || cannonBallManager.testHit(doodle)) {
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
