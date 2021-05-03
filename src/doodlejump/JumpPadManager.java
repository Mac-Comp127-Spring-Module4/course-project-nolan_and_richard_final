package doodlejump;

import edu.macalester.graphics.CanvasWindow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 * 
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
     * 
     */
    public void createJumpPads() {
        int numPads = rand.nextInt(8)+1;
        double x = 100;
        double y = 600;

        for (int i = 0; i < numPads; i++) {
            x += rand.nextInt(100) + 75;
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
     * 
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
    // can change this a bit to be less confusing
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
