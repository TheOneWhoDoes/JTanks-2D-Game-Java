package pack.entities;

import pack.entities.manager.EntityManager;
import pack.graphics.Assets;
import pack.graphics.Camera;

import java.awt.*;

/**
 * this class is for
 * plant of map
 */
public class Bush extends Entity {

    public Bush(float x, float y, EntityManager entityManager) {
        super(x, y, 100, 100, entityManager);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(Assets.bush, (int)(x - Camera.getXOffset()), (int)(y - Camera.getYOffset()), width, height, null);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
