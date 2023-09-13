package pack.entities;

import pack.entities.manager.EntityManager;
import pack.graphics.Assets;
import pack.graphics.Camera;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * this class is for
 * any bullet
 */
public class Bullet extends Entity {

    private final int SPEED = 16;
    public static final float DAMAGE = 0.1f;
    private double angle;
    private float xSpeed, ySpeed;
    public int xPlus, yPlus;


    public Bullet(float x, float y, double angle, EntityManager entityManager) {
        super(x, y, 24, 24, entityManager);
        this.angle = angle;
        xSpeed = (float) (SPEED * Math.cos(Math.toRadians(angle)));
        ySpeed = (float) (SPEED * Math.sin(Math.toRadians(angle)));
        xPlus = 0;
        yPlus = 0;
    }

    @Override
    public void tick() {
        move();
    }

    private void move() {
        x += xSpeed;
        y += ySpeed;
    }

    @Override
    public void render(Graphics2D g) {

        BufferedImage image = Assets.bullet;
        AffineTransform transform = AffineTransform.getTranslateInstance((int) (x - Camera.getXOffset()), (int) (y - Camera.getYOffset()));
        transform.rotate(Math.toRadians(angle), image.getWidth()/2 , image.getHeight()/2 );

        g.drawImage(image, transform, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x + xPlus, (int) y + yPlus, width, height);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Bullet))
            return false;

        Bullet other = (Bullet)obj;
        return (x == other.x) && (y == other.y) && (angle == other.angle);
    }
}
