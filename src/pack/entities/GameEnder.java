package pack.entities;

import pack.entities.manager.EntityManager;
import pack.graphics.Assets;
import pack.graphics.Camera;
import java.awt.*;

/**
 * this class is for
 * managing end game
 */
public class GameEnder extends Entity {

    public GameEnder(float x, float y, EntityManager entityManager) {
        super(x, y, 100, 100, entityManager);
    }

    @Override
    public void tick() {
        if (entityManager.doCollideWithAllPlayers(this)) {
            entityManager.gameWin = true;
        }

    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(Assets.gameEnder, (int)(x - Camera.getXOffset()), (int)(y - Camera.getYOffset()), width, height, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 100, 100);
    }
}
