package pack.entities;

import pack.Game;
import pack.entities.manager.EntityManager;
import pack.entities.players.Player;
import pack.graphics.Assets;
import pack.graphics.Camera;
import pack.input.MouseManager;
import pack.sound.ExampleSounds;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * this class is for
 * enemy tank that is
 * a enemy
 */
public class EnemyTank extends Entity {
    private boolean up, down, right, left;
    private boolean upFinal, downFinal, rightFinal, leftFinal;
    private double degree;
    private double degreeGun;
    private float health = 1;
    private final int SPEED = 8;
    private final int FIRE_Rate = 20; //the less, the faster
    private int fireCounter = 0;
    private int loot;


    public EnemyTank(float x, float y, EntityManager entityManager, int loot) {
        super(x, y, 100, 100, entityManager);
        this.loot = loot;

    }

    @Override
    public void tick() {
        getDamage();
        doAI();
    }

    /**
     * this method is for
     * AI of enemy
     */
    private void doAI() {
        up = false;
        down = false;
        right = false;
        left = false;
        upFinal = false;
        downFinal = false;
        leftFinal = false;
        rightFinal = false;

        boolean equalXORY=true;
        //if for game.width and game.height
        if (entityManager.deltaXToClosestPlayer(this) < (Game.frameWidth) && entityManager.deltaYToClosestPlayer(this) < (Game.frameHeight)) {
            Player closestPlayer = entityManager.getClosestPlayer(this);
            if (closestPlayer == null)
                return;
            degreeGun = MouseManager.angleToPlayer(closestPlayer, this);
            //same gun and tank
            boolean flag = true;
            Cannon cannon = entityManager.createEnemyCannon(x, y, degreeGun);

            while (((Math.abs(cannon.getX() + cannon.xPlus - closestPlayer.getX()) > 2)
                    || (Math.abs(cannon.getY() + cannon.yPlus - closestPlayer.getY()) > 2)) &&
                    (Math.abs(cannon.getBounds().x) < Game.frameWidth) &&
                    (Math.abs(cannon.getBounds().y) < Game.frameHeight)) {
                if ((entityManager.doCollideWithHardWalls(cannon) != null)) {
                    entityManager.removeEnemyCannon(cannon);
                    flag = false;
                    break;
                }
                cannon.xPlus += Math.cos(Math.toRadians(degreeGun)) * SPEED;
                cannon.yPlus += Math.sin(Math.toRadians(degreeGun)) * SPEED;
            }

            cannon.xPlus = 0;
            cannon.yPlus = 0;
            if (fireCounter != FIRE_Rate) {
                fireCounter++;
                entityManager.removeEnemyCannon(cannon);
            } else {
                fireCounter = -1;
            }

            //flag to show can shoot or no or to move toward player
            //we can move with xPlus and yPlus
            if (flag) {
                degree = degreeGun;
                x += Math.cos(Math.toRadians(degreeGun)) * SPEED;
                y += Math.sin(Math.toRadians(degreeGun)) * SPEED;
                if (entityManager.doCollideWithHardWalls(this) != null) {
                    entityManager.removeEnemyCannon(cannon);
                    x -= Math.cos(Math.toRadians(degreeGun)) * SPEED;
                    y -= Math.sin(Math.toRadians(degreeGun)) * SPEED;
                    flag = false;
                } else if (entityManager.doCollideWithSoftWalls(this) != null ||
                        entityManager.doCollideWithPlayer(this) != null ||
                        entityManager.doCollideWithBarbedWires(this) != null) {
                    x -= Math.cos(Math.toRadians(degreeGun)) * SPEED;
                    y -= Math.sin(Math.toRadians(degreeGun)) * SPEED;
                    flag = false;
                }

            }
            if (!flag) {

                boolean move = false;
                if (((closestPlayer.getXMove() == 0) && (closestPlayer.getYMove() == 0))&&((Math.abs(closestPlayer.getY() - y) < 4)||(Math.abs(closestPlayer.getX() - x) < 4))) {
                    equalXORY=false;
                }else {
                    if ((closestPlayer.getXMove() == 0) && (closestPlayer.getYMove() == 0)) {
                        if (x > closestPlayer.getX()) {

                            left = true;
                            leftFinal = true;
                        }
                        if (x < closestPlayer.getX()) {
                            right = true;
                            rightFinal = true;
                        }
                        if (y >= closestPlayer.getY()) {
                            up = true;
                            upFinal = true;
                        }
                        if (y < closestPlayer.getY()) {
                            down = true;
                            downFinal = true;
                        }
                    } else {
                        if (x > closestPlayer.getX()) {

                            left = true;
                            leftFinal = true;
                        }
                        if (x < closestPlayer.getX()) {
                            right = true;
                            rightFinal = true;
                        }
                        if (y >= closestPlayer.getY()) {
                            up = true;
                            upFinal = true;
                        }
                        if (y < closestPlayer.getY()) {
                            down = true;
                            downFinal = true;
                        }
                    }


                    for (int i = 0; i < 3; i++) {
                        if (up && right)
                            degree = -45;
                        else if (up && left)
                            degree = -135;
                        else if (right && down) {
                            degree = 45;
                        } else if (left && down)
                            degree = 135;
                        else if (down)
                            degree = 90;
                        else if (up)
                            degree = -90;
                        else if (right)
                            degree = 0;
                        else if (left)
                            degree = 180;

                        move = true;
                        x += Math.cos(Math.toRadians(degree)) * SPEED;
                        y += Math.sin(Math.toRadians(degree)) * SPEED;
                        if (entityManager.doCollideWithHardWalls(this) != null) {

                            move = false;
                            x -= Math.cos(Math.toRadians(degree)) * SPEED;
                            y -= Math.sin(Math.toRadians(degree)) * SPEED;

                            if (i == 1) {
                                if (left) {
                                    left = false;
                                    if ((up == false) && (upFinal == true))
                                        up = true;
                                    if ((down == false) && (downFinal == true))
                                        down = true;
                                } else if (right) {
                                    right = false;
                                    if ((up == false) && (upFinal == true))
                                        up = true;
                                    if ((down == false) && (downFinal == true))
                                        down = true;

                                } else if (down) {
                                    down = false;
                                    if ((right == false) && (rightFinal == true))
                                        right = true;
                                    if ((left == false) && (leftFinal == true))
                                        left = true;
                                } else if (up) {
                                    up = false;
                                    if ((right == false) && (rightFinal == true))
                                        right = true;
                                    if ((left == false) && (leftFinal == true))
                                        left = true;
                                }
                                continue;
                            }

                            if (up) {
                                up = false;
                                continue;
                            }

                            if (down) {
                                down = false;
                                continue;
                            }

                            if (right) {
                                right = false;
                                continue;
                            }

                            if (left) {
                                left = false;
                                continue;
                            }

                        } else {
                            break;
                        }

                    }
                }

                if ((!move)&&(!equalXORY)) {

                }

                if (entityManager.doCollideWithPlayer(this) != null) {
                    x -= Math.cos(Math.toRadians(degree)) * SPEED;
                    y -= Math.sin(Math.toRadians(degree)) * SPEED;

                } else {

                    y -= Math.sin(Math.toRadians(degree)) * SPEED;

                    if (entityManager.doCollideWithHardWalls(this) != null ||
                            entityManager.doCollideWithSoftWalls(this) != null ||
                            entityManager.doCollideWithEnemyTank(this) != null ||
                            entityManager.doCollideWithEnemyCar(this) != null ||
                            entityManager.doCollideWithBarbedWires(this) != null)
                        x -= Math.cos(Math.toRadians(degree)) * SPEED;
                    ;

                    y += Math.sin(Math.toRadians(degree)) * SPEED;
                    if (entityManager.doCollideWithHardWalls(this) != null ||
                            entityManager.doCollideWithSoftWalls(this) != null ||
                            entityManager.doCollideWithEnemyTank(this) != null ||
                            entityManager.doCollideWithEnemyCar(this) != null ||
                            entityManager.doCollideWithBarbedWires(this) != null)
                        y -= Math.sin(Math.toRadians(degree)) * SPEED;

                }

            }
            if (entityManager.containEnemyCanon(cannon)!=null)
                ExampleSounds.playEnemyShot(true);

        }
    }

    /**
     * this method is for
     * manage damaging enemy
     */
    private void getDamage() {
        Bullet bullet = entityManager.doCollideWithFriendlyBullet(this);
        if (bullet != null) {
            health -= Bullet.DAMAGE;
            entityManager.removeFriendlyBullet(bullet);
        }

        Cannon cannon = entityManager.doCollideWithFriendlyCannon(this);
        if (cannon != null) {
            health -= Cannon.DAMAGE;
            entityManager.removeFriendlyCannon(cannon);
        }

        if (health <= 0) {
            entityManager.removeEnemyTank(this);
            switch (loot) {
                case 1: entityManager.createUpgrader(x, y); break;
                case 2: entityManager.createCannonFood(x, y); break;
                case 3: entityManager.createBulletFood(x, y); break;
            }
        }
    }


    @Override
    public void render(Graphics2D g) {

        BufferedImage image = Assets.enemyTank;
        AffineTransform transform = AffineTransform.getTranslateInstance((int) (x - Camera.getXOffset()), (int) (y - Camera.getYOffset()));
        transform.rotate(Math.toRadians(degree), image.getWidth() / 2, image.getHeight() / 2);


        g.drawImage(image, transform, null);


        BufferedImage imageGun = Assets.enemyTankGun;
        AffineTransform transformGun = AffineTransform.getTranslateInstance((int) (x - Camera.getXOffset()) + 15, (int) (y - Camera.getYOffset()));
        transformGun.rotate(Math.toRadians(degreeGun), imageGun.getWidth() / 2 - 15, imageGun.getHeight() / 2);


        g.drawImage(imageGun, transformGun, null);
    }

    @Override
    public Rectangle getBounds() {

        return new Rectangle((int) x + 2 * SPEED / 3, (int) y + 2 * SPEED / 3, width - 4 * SPEED / 3 , height - 4 * SPEED / 3);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof EnemyTank))
            return false;

        EnemyTank other = (EnemyTank) obj;
        return (x == other.x) && (y == other.y);
    }

}

