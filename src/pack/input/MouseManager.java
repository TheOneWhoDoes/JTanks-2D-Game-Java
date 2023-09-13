package pack.input;

import pack.entities.Entity;
import pack.graphics.Camera;
import pack.ui.MyUIManager;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * this class is for
 * managing mouse click
 */
public class MouseManager extends MouseAdapter {

    private static MyUIManager uiManager;

    public static boolean leftMouseButton;
    public static boolean rightMouseButton;
    public static int rightMouseButtonFlag = 1;

    public static double angle;
    public static double dx;
    public static double dy;

    @Override
    public void mouseMoved(MouseEvent e) {
        dx = e.getX() - Camera.getEntityX() + Camera.getXOffset();
        dy = e.getY() - Camera.getEntityY() + Camera.getYOffset();
        angle = (Math.atan2(dy, dx) / (Math.PI)) * 180;
        if(uiManager != null)
            uiManager.onMouseMove(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        dx = e.getX() - Camera.getEntityX() + Camera.getXOffset();
        dy = e.getY() - Camera.getEntityY() + Camera.getYOffset();
        angle = (Math.atan2(dy, dx) / (Math.PI)) * 180;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e))
            rightMouseButtonFlag *= -1;

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e))
            leftMouseButton = true;
        if (SwingUtilities.isRightMouseButton(e))
            rightMouseButton = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            leftMouseButton = false;
            if(uiManager != null)
                uiManager.onMouseRelease(e);
        }
        if (SwingUtilities.isRightMouseButton(e))
            rightMouseButton = false;
    }

    /**
     * this method is set
     * angle of player to enemy
     * @param player
     * @param enemy
     * @return
     */
    public static double angleToPlayer(Entity player, Entity enemy){
        float dx = enemy.getX() - player.getX();
        float dy = enemy.getY() - player.getY();
        return  (Math.atan2(dy, dx) / (Math.PI)) * 180 + 180;
    }

    public static void setUIManager(MyUIManager manager) {
        uiManager = manager;
    }

}
