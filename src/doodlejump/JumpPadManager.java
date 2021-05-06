package doodlejump;

import edu.macalester.graphics.CanvasWindow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


    /**
     * JumpPadManager controls all of the jumpPads, including their creation,
     * removal, and checking for intersections with the doodle.
     */
    public class JumpPadManager {
        private CanvasWindow canvas;
        private List<JumpPad> jumpPads;
        private Random rand = new Random();

        public JumpPadManager(CanvasWindow canvas) {
            jumpPads = new ArrayList<>();
            this.canvas = canvas;
        }
    

    /**
     * Creates a random number of jump pads from 1-5 with varying
     * x and y locations. Adds the jump pads to the jumpPads list.
     */
    public void createJumpPads() {
        int numPads = rand.nextInt(5)+1;
        double x = 10;
        double y = 600;

        for (int i = 0; i < numPads; i++) {
            x += rand.nextInt(100) + 75;
            if (x > DoodleJumpGame.CANVAS_WIDTH - JumpPad.JUMP_PAD_WIDTH - 100){
                return;
            }
            if (rand.nextBoolean()){
                y += rand.nextInt(70);
            }
            else {
                y -= rand.nextInt(60);
            }
            JumpPad jumpPad = new JumpPad(x, y);
            jumpPad.addToCanvas(canvas);
            jumpPads.add(jumpPad);
        }
    }

    /**
     * Removes all of the jump pads from the canvas and the list containing
     * the jump pads.
     * 
     * Acknowledgements: This method was created with help from the following website:
     * https://stackoverflow.com/questions/8104692/how-to-avoid-java-util-concurrentmodificationexception-when-iterating-through-an
     */
    public void removeJumpPads() {
        for (Iterator<JumpPad> iterator = jumpPads.iterator(); iterator.hasNext();) {
            JumpPad jp = iterator.next();
            jp.removeFromCanvas(canvas);
            iterator.remove();
        }
    }


    /**
     * Checks whether the doodle hits any of the jump pads, removing the first intersecting jump pad if so.
     * @return Once a jump pad has been hit
     * 
     * Acknowledgements: This method was created with help from the following website:
     * https://stackoverflow.com/questions/8104692/how-to-avoid-java-util-concurrentmodificationexception-when-iterating-through-an
     */
    public boolean testHit(Doodle doodle) {
        for (Iterator<JumpPad> iterator = jumpPads.iterator(); iterator.hasNext();) {
            JumpPad jp = iterator.next();
            if (jp.intersects(doodle)) {
                removeJumpPads();
                canvas.pause(5);
                createJumpPads();
                return true;
            }
        }
        return false;
    }
}
