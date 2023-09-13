package pack.entities;

import pack.entities.manager.EntityManager;
import pack.graphics.Assets;
import pack.graphics.Camera;
import java.awt.*;

/**
 * this class is for
 * barbed wire that is
 * an entity
 */
public class BarbedWire extends Entity {

    public BarbedWire(float x, float y, EntityManager entityManager) {
        super(x, y, 100, 100, entityManager);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(Assets.barbedWire, (int)(x - Camera.getXOffset()), (int)(y - Camera.getYOffset()), width, height, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);

    }
}
