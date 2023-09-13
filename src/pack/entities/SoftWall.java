package pack.entities;

import pack.entities.manager.EntityManager;
import pack.graphics.Assets;
import pack.graphics.Camera;
import java.awt.*;

/**
 * this class is for
 * managing soft wall of
 * map that is an Entity
 */
public class SoftWall extends Entity {

    private final float MAX_HEALTH = 4;
    private float health;
    private int loot;

    public SoftWall(float x, float y, EntityManager entityManager, int loot) {
        super(x, y, 100, 100, entityManager);
        health = MAX_HEALTH;
        this.loot = loot;
    }

    @Override
    public void tick() {
        getDamage();
    }

    /**
     * this method is for
     * manage damaging soft wall
     */
    private void getDamage() {

        Cannon friendlyCannon = entityManager.doCollideWithFriendlyCannon(this);
        if (friendlyCannon != null) {
            entityManager.removeFriendlyCannon(friendlyCannon);
            health -= Cannon.DAMAGE;
        }

        Bullet friendlyBullet = entityManager.doCollideWithFriendlyBullet(this);
        if (friendlyBullet != null) {
            entityManager.removeFriendlyBullet(friendlyBullet);
            health -= Bullet.DAMAGE;
        }

        Bullet enemyBullet = entityManager.doCollideWithEnemyBullet(this);
        if (enemyBullet != null) {
            entityManager.removeEnemyBullet(enemyBullet);
            health -= Bullet.DAMAGE;
        }

        Cannon enemyCannon = entityManager.doCollideWithEnemyCannon(this);
        if (enemyCannon != null) {
            entityManager.removeEnemyCannon(enemyCannon);
            health -= Cannon.DAMAGE;
        }

        if (health <= 0) {
            entityManager.removeSoftWall(this);
            switch (loot) {
                case 1: entityManager.createUpgrader(x, y); break;
                case 2: entityManager.createCannonFood(x, y); break;
                case 3: entityManager.createBulletFood(x, y); break;
            }

        }
    }


    @Override
    public void render(Graphics2D g) {
        if (health > 3)
            g.drawImage(Assets.softWall[0], (int)(x - Camera.getXOffset()), (int)(y - Camera.getYOffset()), width, height, null);
        else if (health > 2)
            g.drawImage(Assets.softWall[1], (int)(x - Camera.getXOffset()), (int)(y - Camera.getYOffset()), width, height, null);
        else if (health > 1)
            g.drawImage(Assets.softWall[2], (int)(x - Camera.getXOffset()), (int)(y - Camera.getYOffset()), width, height, null);
        else if (health > 0)
        g.drawImage(Assets.softWall[3], (int)(x - Camera.getXOffset()), (int)(y - Camera.getYOffset()), width, height, null);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof SoftWall))
            return false;

        SoftWall other = (SoftWall) obj;
        return (x == other.x) && (y == other.y);
    }

}
