package pack.ui;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * this abstract class is representing the
 * general form of a UI object
 */
public abstract class UIObject {

    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected boolean hovering;

    public UIObject(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle((int) x, (int) y, width, height);

    }

    public abstract void tick();

    public abstract void render(Graphics2D g);

    public abstract void onClick();

    public void onMouseMove(MouseEvent e){
        if(bounds.contains(e.getX(), e.getY()))
            hovering = true;
        else
            hovering = false;
    }

    public void onMouseRelease(MouseEvent e){
        if(hovering)
            onClick();
    }

}
