package doodlejump;

import edu.macalester.graphics.CanvasWindow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 
 */
public class JumpPadManager {
    private CanvasWindow canvas;
    private List<JumpPad> jumpPads;

    /**
     * 
     */
    public JumpPadManager(CanvasWindow canvas) {
        jumpPads = new ArrayList<>();
        this.canvas = canvas;
    }
    
    public void createJumpPads() {
        // create some jump pads w/ randomization
    }

    public void removeJumpPads() {
        // remove all the jump pads, make sure ot clear list of pads too
    }


    /**
     * Checks whether the doodle hits any of the jump pads, removing the first intersecting jump pad if so.
     * @return Once a jump pad has been hit
     * 
     * Acknowledgements: This method was created with help from the following website:
     * https://stackoverflow.com/questions/8104692/how-to-avoid-java-util-concurrentmodificationexception-when-iterating-through-an
     */
    // can change this a bit to be less confusing
    public void testHit(Doodle doodle) {
        for (Iterator<JumpPad> iterator = jumpPads.iterator(); iterator.hasNext();) {
            JumpPad jp = iterator.next();
            if (jp.intersects(doodle, canvas)) {
                removeJumpPads();
                return;
            }
        }
    }
}
