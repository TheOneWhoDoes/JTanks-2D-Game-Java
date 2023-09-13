package pack.entities;

import pack.entities.manager.EntityManager;
import java.awt.*;
import java.io.Serializable;

/**
 * this class is abstract class
 * that super class of entities
 */
public abstract class Entity implements Serializable {

    protected float x, y;
    protected int width, height;
    protected EntityManager entityManager;

    public Entity(float x, float y, int width, int height, EntityManager entityManager){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.entityManager = entityManager;
    }

    public abstract void tick();

    public abstract void render(Graphics2D g);

    public abstract Rectangle getBounds();


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
