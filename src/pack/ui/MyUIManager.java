package pack.ui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * this class manages the UIObjects that are
 * used in MenuState and LostWinState
 */
public class MyUIManager {

    private ArrayList<UIObject> objects;

    public MyUIManager() {

        objects = new ArrayList<>();
    }

    public void tick() {
        for(UIObject o : objects)
            o.tick();
    }

    public void render(Graphics2D g){
        for(UIObject o : objects)
            o.render(g);
    }

    public void onMouseMove(MouseEvent e){
        for(UIObject o : objects)
            o.onMouseMove(e);
    }

    public void onMouseRelease(MouseEvent e){

        for(UIObject o : objects)
            o.onMouseRelease(e);
    }

    public void addObject(UIObject o){
        objects.add(o);
    }



}
