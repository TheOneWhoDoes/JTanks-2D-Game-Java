package pack.states;

import pack.entities.manager.EntityManager;

import java.awt.*;
import java.io.Serializable;

/**
 * State show a state
 * of an object
 * and it is abstract
 */
public abstract class State implements Serializable {

    public State() {

    }

    public abstract void tick();
    public abstract void render(Graphics2D g);
    public abstract EntityManager getEntityManager();

}
