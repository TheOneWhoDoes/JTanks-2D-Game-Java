package pack.entities.players;

import pack.entities.*;
import pack.entities.manager.EntityManager;
import pack.graphics.*;
import pack.input.*;
import pack.sound.ExampleSounds;

/**
 * this class is server
 * that manage main player
 */
public class ServerPlayer extends Player {

    private float startX;
    private float startY;


    public ServerPlayer(float x, float y, int number, EntityManager entityManager) {
        super(x, y, number, entityManager);
        startX = x;
        startY = y;
    }

    @Override
    public void tick() {

        if (!alive) {
            Camera.centerOnEntity(x, y, width, height);
            degreeGun = MouseManager.angle;
            return;
        }


        getCheat();

        move();
        getFood();
        upgrade();
        shoot();
        getDamage();
        prepareRender();
    }

    /**
     * this method is for cheat code
     */
    private void getCheat() {
        if (KeyManager.fullLife) {
            health = MAX_HEALTH;
            KeyManager.fullLife =false;
        }
        if (KeyManager.fullCB) {
            cannon = MAX_CANNON;
            KeyManager.fullCB =false;
        }
        if (KeyManager.upgradeWeapon) {
            cannonLevel = 3;
            bulletLevel = 2;
            KeyManager.upgradeWeapon = false;
        }
    }


    /**
     * move
     */
    private void move() {
        //MOVEMENT
        up = KeyManager.up;
        down = KeyManager.down;
        left = KeyManager.left;
        right = KeyManager.right;

        xMove = 0;
        yMove = 0;

        if (up)
            yMove = -ySpeed;
        if (down)
            yMove = ySpeed;
        if (left)
            xMove = -xSpeed;
        if (right)
            xMove = xSpeed;

        x += xMove;
        y += yMove;

        y -= yMove;

        if (entityManager.doCollideWithHardWalls(this) != null ||
                entityManager.doCollideWithSoftWalls(this) != null ||
                entityManager.doCollideWithEnemyTank(this) != null ||


                entityManager.doCollideWithEnemyCar(this) != null  ||
                entityManager.doCollideWithArtillery(this) != null ||
                entityManager.doCollideWithBarbedWires(this) != null)

            x -= xMove;

        y += yMove;
        if (entityManager.doCollideWithHardWalls(this) != null ||
                entityManager.doCollideWithSoftWalls(this) != null ||
                entityManager.doCollideWithEnemyTank(this) != null ||

                entityManager.doCollideWithEnemyCar(this) != null ||
                entityManager.doCollideWithArtillery(this) != null ||
                entityManager.doCollideWithBarbedWires(this) != null)

            y -= yMove;


        Camera.centerOnEntity(x, y, width, height);


    }

    /**
     * this method is
     * getting food
     */
    private void getFood() {
        if (bullet != MAX_BULLET) {
            BulletFood bulletFood = entityManager.doCollideWithBulletFood(this);
            if (bulletFood != null) {
                ExampleSounds.playAgree(true);
                bullet = MAX_BULLET;
                entityManager.removeBulletFood(bulletFood);
            }
        }

        if (cannon != MAX_CANNON) {
            CannonFood cannonFood = entityManager.doCollideWithCannonFood(this);
            if (cannonFood != null) {
                ExampleSounds.playAgree(true);
                cannon = MAX_CANNON;
                entityManager.removeCannonFood(cannonFood);
            }
        }

        if (health != MAX_HEALTH) {
            RepairFood repairFood = entityManager.doCollideWithRepairFood(this);
            if (repairFood != null) {
                ExampleSounds.playAgree(true);
                health = MAX_HEALTH;
                entityManager.removeRepairFood(repairFood);
            }
        }

    }

    /**
     * this method is for upgrading
     * gun of tank
     */
    private void upgrade() {
        Upgrader upgrader = entityManager.doCollideWithUpgrader(this);

        if (upgrader != null) {
            if (gunState == -1)
                updateBullet();
            else
                updateCannon();

            entityManager.removeUpgrader(upgrader);
        }

        //if both of them are fully upgraded, nothing upgrades, but the Star is removed anyways.
    }

    /**
     * this method is for
     * updating cannon
     */
    private void updateCannon() {
        if (cannonLevel < 3) {
            cannonLevel++;
            if (cannonLevel == 1 || cannonLevel == 3) {
                cannonRate -= 0.5;
            } else {
                cannonRate += 0.5;
            }
        } else if (bulletLevel < 2)
            updateBullet();

        ExampleSounds.playSelect(true);

    }

    /**
     * this method is for
     * updating bullet
     */
    private void updateBullet() {
        if (bulletLevel < 2) {
            bulletLevel++;
            bulletRate--;
            bulletCounter = -1;
        } else if (cannonLevel < 3)
            updateCannon();

        ExampleSounds.playSelect(true);

    }

    /**
     * this method is
     * for set shoot of enemy
     */
    private void shoot() {
        gunState = MouseManager.rightMouseButtonFlag;

        degreeGun = MouseManager.angle;
        if (MouseManager.leftMouseButton) {

            timeEnd = System.nanoTime();
            if ((timeEnd - timeStart) / 1000000000 > cannonRate)
                canShoot = true;

            if (gunState == 1 && cannon > 0) {
                if (canShoot) {


                    entityManager.createFriendlyCannon((int) ((x + width / 2) - 12 + 65 * Math.cos(Math.toRadians(degreeGun))), (int) ((y + height / 2) + 60 * Math.sin(Math.toRadians(degreeGun))), degreeGun);

                    ExampleSounds.playCannon(true);
                    if (cannonLevel == 2 || cannonLevel == 3) {
                        entityManager.createFriendlyCannon((int) ((x + width / 2) - 12 + 65 * Math.cos(Math.toRadians(degreeGun))), (int) ((y + height / 2) + 60 * Math.sin(Math.toRadians(degreeGun))), degreeGun + 8);
                        entityManager.createFriendlyCannon((int) ((x + width / 2) - 12 + 65 * Math.cos(Math.toRadians(degreeGun))), (int) ((y + height / 2) + 60 * Math.sin(Math.toRadians(degreeGun))), degreeGun - 8);

                    }
                    cannon--;

                    canShoot = false;
                    timeStart = System.nanoTime();
                }
            } else if (gunState == -1 && bullet > 0) {
                if (bulletCounter == bulletRate) {

                    entityManager.createFriendlyBullet((int) ((x + width / 2) - 12 + 65 * Math.cos(Math.toRadians(degreeGun))),(int) ((y + height / 2) + 60 * Math.sin(Math.toRadians(degreeGun))), degreeGun);


                    ExampleSounds.playLightGun(true);

                    bullet--;
                    bulletCounter = -1;
                }
                bulletCounter++;
            }
        }

    }

    /**
     * this method is for
     * set damaging enemy
     *
     */
    private void getDamage() {
        Bullet enemyBullet = entityManager.doCollideWithEnemyBullet(this);
        if (enemyBullet != null) {
            ExampleSounds.playEnemyBulletToMyTank(true);
            health -= Bullet.DAMAGE;
            entityManager.removeEnemyBullet(enemyBullet);
        }

        Cannon enemyCannon = entityManager.doCollideWithEnemyCannon(this);
        if (enemyCannon != null) {
            health -= Cannon.DAMAGE;
            entityManager.removeEnemyCannon(enemyCannon);
        }

        Mine mine = entityManager.doCollideWithMine(this);
        if (mine != null) {
            health -= Mine.DAMAGE;
            ExampleSounds.playEnemyBulletToMyTank(true);
            entityManager.removeMine(mine);
        }

        if (health <= 0) {
            health = 0;
            alive = false;
        }

    }

    /**
     * set degree at
     * this method
     */
    private void prepareRender() {
        if (up && right)
            degree = -45;
        else if (up && left)
            degree = -135;
        else if (right && down)
            degree = 45;
        else if (left && down)
            degree = 135;
        else if (down)
            degree = 90;
        else if (up)
            degree = -90;
        else if (right)
            degree = 0;
        else if (left)
            degree = 180;
    }


    public float getStartX() {
        return startX;
    }

    public float getStartY() {
        return startY;
    }

}
