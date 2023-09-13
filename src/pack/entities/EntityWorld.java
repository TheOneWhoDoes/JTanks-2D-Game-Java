package pack.entities;

import pack.entities.manager.EntityManager;
import pack.utils.FileLoader;
import java.io.Serializable;

/**
 * this class is for
 * managing entities at
 * world
 */
public class EntityWorld implements Serializable {

    private int widthInEntity;
    private int heightInEntity;
    private int entityWidth = 100;
    private int entityHeight = 100;
    private EntityManager entityManager;


    public EntityWorld(EntityManager entityManager , String backgroundWorld, String foregroundWorld) {
        this.entityManager = entityManager;
        loadBackground(backgroundWorld);
        loadForeground(foregroundWorld);

    }

    /**
     * this method is for
     * loading background of
     * map
     * @param path
     */
    private void loadBackground(String path) {
        String background = FileLoader.loadFileAsString(path);
        String[] tokens = background.split("\\s+");

        widthInEntity = Integer.parseInt(tokens[0]);
        heightInEntity = Integer.parseInt(tokens[1]);

        /*

        BarbedWire: a
        BulletFood : b
        CannonFood : c
        EnemyTank : d
        EnemyCar : e
        HardWall : f
        Mine : g
        Player : h
        RepairFood : i
        SoftWall : j
        Upgrader : k
        َ Artillery_Left : l
        َ Artillery_Right : m
        َ Artillery_Down : n
        َ Artillery_Up : o
        GameEnder : p

        z : null
        */

        String z = "";
        for (int y = 0; y < widthInEntity ; y++) {
            for (int x = 0; x < heightInEntity; x++) {
                z = tokens[x + (y * heightInEntity) + 2];

                if (z.equals("a")) entityManager.createBarbedWire(x * entityWidth, y * entityHeight);
                if (z.equals("b")) entityManager.createBulletFood(x * entityWidth, y * entityHeight);
                if (z.equals("c")) entityManager.createCannonFood(x * entityWidth, y * entityHeight);

                if (z.equals("d")) entityManager.createEnemyTank(x * entityWidth, y * entityHeight, 0);
                if (z.equals("d1")) entityManager.createEnemyTank(x * entityWidth, y * entityHeight, 1);
                if (z.equals("d2")) entityManager.createEnemyTank(x * entityWidth, y * entityHeight, 2);
                if (z.equals("d3")) entityManager.createEnemyTank(x * entityWidth, y * entityHeight, 3);

                if (z.equals("e")) entityManager.createEnemyCar(x * entityWidth, y * entityHeight, 0);
                if (z.equals("e1")) entityManager.createEnemyCar(x * entityWidth, y * entityHeight, 1);
                if (z.equals("e2")) entityManager.createEnemyCar(x * entityWidth, y * entityHeight, 2);
                if (z.equals("e3")) entityManager.createEnemyCar(x * entityWidth, y * entityHeight, 3);

                if (z.equals("f")) entityManager.createHardWall(x * entityWidth, y * entityHeight);
                if (z.equals("g")) entityManager.createMine(x * entityWidth, y * entityHeight);
                if (z.equals("h")) entityManager.createServerPlayer(x * entityWidth, y * entityHeight);
                if (z.equals("i")) entityManager.createRepairFood(x * entityWidth, y * entityHeight);

                if (z.equals("j")) entityManager.createSoftWall(x * entityWidth, y * entityHeight, 0);
                if (z.equals("j1")) entityManager.createSoftWall(x * entityWidth, y * entityHeight, 1);
                if (z.equals("j2")) entityManager.createSoftWall(x * entityWidth, y * entityHeight, 2);
                if (z.equals("j3")) entityManager.createSoftWall(x * entityWidth, y * entityHeight, 3);

                if (z.equals("k")) entityManager.createUpgrader(x * entityWidth, y * entityHeight);
                if (z.equals("l")) entityManager.createArtillery(x * entityWidth, y * entityHeight, Artillery.Type.LEFT);
                if (z.equals("m")) entityManager.createArtillery(x * entityWidth, y * entityHeight, Artillery.Type.RIGHT);
                if (z.equals("n")) entityManager.createArtillery(x * entityWidth, y * entityHeight, Artillery.Type.DOWN);
                if (z.equals("o")) entityManager.createArtillery(x * entityWidth, y * entityHeight, Artillery.Type.UP);
                if (z.equals("p")) entityManager.createGameEnder(x * entityWidth, y * entityHeight);
            }

        }

    }


    private void loadForeground(String path) {

        //Bushes (and other possible foreground entities)
        String foreground = FileLoader.loadFileAsString(path);
        String[] tokens = foreground.split("\\s+");
        char z = ' ';
        for (int y = 0; y < widthInEntity ; y++) {
            for (int x = 0; x < heightInEntity; x++) {
                z = tokens[x + (y * heightInEntity) + 2].charAt(0);

                if (z == 'b') entityManager.createBush(x * entityWidth, y * entityHeight);

            }
        }

    }



}
