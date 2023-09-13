package pack.entities.players;

import pack.entities.Entity;
import pack.entities.manager.EntityManager;
import pack.graphics.Assets;
import pack.graphics.Camera;
import pack.states.GameState;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * this class is for
 * feature of player
 */
public class Player extends Entity {

    protected int number;
    protected boolean up, down, right, left;
    protected int degree;
    protected double degreeGun;
    protected float xMove, yMove;
    protected final int SPEED = 20;
    protected float xSpeed;
    protected float ySpeed;

    protected boolean canShoot = true;
    protected double timeStart;
    protected double timeEnd;

    protected int gunState; //1.Cannon, -1.Bullet
    protected double cannonRate = 0.8; //the less, the faster
    protected int cannonLevel = 0;
    protected int bulletRate = 3; //the less, the faster
    protected int bulletLevel = 0;
    protected int bulletCounter = 0;


    protected final float MAX_HEALTH;
    protected float health;

    protected final int MAX_CANNON;
    protected int cannon;

    protected final int MAX_BULLET;
    protected int bullet;

    protected boolean alive = true;


    public float getXMove() {
        return xMove;
    }

    public float getYMove() {
        return yMove;
    }

    public Player(float x, float y, int number, EntityManager entityManager) {
        super(x, y, 100, 100, entityManager);
        this.number = number;
        gunState = 1;
        MAX_HEALTH = GameState.maxPlayerHealth;
        health = MAX_HEALTH;
        MAX_BULLET = GameState.maxPlayerBullet;
        bullet = MAX_BULLET;
        MAX_CANNON = GameState.maxPlayerCannon;
        cannon = MAX_CANNON;
        xSpeed = (float)(Math.sqrt(2) / 2 * SPEED);
        ySpeed = (float)(Math.sqrt(2) / 2 * SPEED);

    }


    @Override
    public void tick() {
    }

    /**
     * this method is for
     * rendering player
     * @param g graphics 2D
     */
    @Override
    public void render(Graphics2D g) {
        BufferedImage image = Assets.player;
        AffineTransform transform = AffineTransform.getTranslateInstance((int) (x - Camera.getXOffset()), (int) (y - Camera.getYOffset()));
        transform.rotate(Math.toRadians(degree), image.getWidth()/2 , image.getHeight()/2 );


        g.drawImage(image, transform, null);

        BufferedImage imageGun = null;

        if (gunState == 1) {
            switch (cannonLevel) {
                case 0: imageGun = Assets.playerCannonGun[0]; break;
                case 1: imageGun = Assets.playerCannonGun[1]; break;
                case 2: imageGun = Assets.playerCannonGun[2]; break;
                case 3: imageGun = Assets.playerCannonGun[3]; break;
            }
        }
        else {
            switch (bulletLevel) {
                case 0: imageGun = Assets.playerBulletGun[0]; break;
                case 1: imageGun = Assets.playerBulletGun[1]; break;
                case 2: imageGun = Assets.playerBulletGun[2]; break;
            }

        }

        AffineTransform transformGun = AffineTransform.getTranslateInstance((int) (x - Camera.getXOffset()) + 10, (int) (y - Camera.getYOffset()));
        transformGun.rotate(Math.toRadians(degreeGun), imageGun.getWidth() / 2 - 12 , imageGun.getHeight() / 2);

        g.drawImage(imageGun, transformGun, null);
    }

    public void renderPlayerState(Graphics2D g) {

        g.drawImage(Assets.cannonNum, 15, 40, null);
        g.drawImage(Assets.bulletNum, 15, 110, null);
        g.drawImage(Assets.healthNum, 15, 180, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        g.drawString(cannon + "", 40, 110);
        g.drawString(bullet + "", 40, 180);
        g.drawString((int) health + "", 40, 225);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x + 2 * SPEED / 3, (int) y + 2 * SPEED / 3, width -  4 * SPEED / 3 , height - 4 * SPEED / 3);
    }

    public boolean isAlive() {
        return alive;
    }

    public int getHealth() {
        return (int) health;
    }

    public int getCannon() {
        return cannon;
    }

    public int getBullet() {
        return bullet;
    }

}