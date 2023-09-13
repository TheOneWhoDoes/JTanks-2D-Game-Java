package pack.entities;

import pack.entities.manager.EntityManager;
import pack.graphics.Assets;
import pack.graphics.Camera;
import java.awt.*;

/**
 * this class is for
 * hard wall that is entity
 */
public class HardWall extends Entity {

    public HardWall(float x, float y, EntityManager entityManager) {
        super(x, y, 100, 100, entityManager);
    }

    @Override
    public void tick() {
        getDamage();
    }

    /**
     * this method is for
     * manage damaging of hard wall
     */
    private void getDamage() {
        Bullet friendlyBullet = entityManager.doCollideWithFriendlyBullet(this);
        if (friendlyBullet != null)
            entityManager.removeFriendlyBullet(friendlyBullet);

        Cannon friendlyCannon = entityManager.doCollideWithFriendlyCannon(this);
        if (friendlyCannon != null)
            entityManager.removeFriendlyCannon(friendlyCannon);

        Bullet enemyBullet = entityManager.doCollideWithEnemyBullet(this);
        if (enemyBullet != null)
            entityManager.removeEnemyBullet(enemyBullet);

        Cannon enemyCannon = entityManager.doCollideWithEnemyCannon(this);
        if (enemyCannon != null)
            entityManager.removeEnemyCannon(enemyCannon);
    }


    @Override
    public void render(Graphics2D g) {
        g.drawImage(Assets.hardWall, (int)(x - Camera.getXOffset()), (int)(y - Camera.getYOffset()), width, height, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);

    }
}
