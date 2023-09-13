package pack.graphics;

import java.awt.image.BufferedImage;

/**
 * this class keeps all buffered image
 * for managing it
 */
public class Assets {

    public static BufferedImage player, dirt, hardWall, softWall[],
            playerBulletGun[], playerCannonGun[], bullet, cannon,
            repairFood, bulletFood, cannonFood, upgrader,
            artilleryBase, artilleryGun, artilleryDead, mine,
            enemyTank, enemyTankGun, enemyCar, enemyCarGun, barbedWire,
            bush, gameEnder, cannonNum, bulletNum, healthNum,
            menuBackground, menuBackgroundBlurred, createGame[], joinGame[], exit[],
            easy[], normal[], hard[], cursor, youLost, youWin, exitBackToMenu[];


    /**
     * this method load
     * all the buffered image
     */
    public static void init() {

        dirt = pack.utils.ImageLoader.load("res/textures/dirt.png");
        player = pack.utils.ImageLoader.load("res/textures/player.png");
        hardWall = pack.utils.ImageLoader.load("res/textures/stone.png");

        playerBulletGun = new BufferedImage[3];

        playerBulletGun[0] = pack.utils.ImageLoader.load("res/textures/playerBulletGun0.png");
        playerBulletGun[1] = pack.utils.ImageLoader.load("res/textures/playerBulletGun1.png");
        playerBulletGun[2] = pack.utils.ImageLoader.load("res/textures/playerBulletGun2.png");

        playerCannonGun = new BufferedImage[4];

        playerCannonGun[0] = pack.utils.ImageLoader.load("res/textures/playerCannonGun0.png");
        playerCannonGun[1] = pack.utils.ImageLoader.load("res/textures/playerCannonGun1.png");
        playerCannonGun[2] = pack.utils.ImageLoader.load("res/textures/playerCannonGun2.png");
        playerCannonGun[3] = pack.utils.ImageLoader.load("res/textures/playerCannonGun3.png");

        bullet = pack.utils.ImageLoader.load("res/textures/bullet.png");
        cannon = pack.utils.ImageLoader.load("res/textures/cannon.png");

        softWall = new BufferedImage[4];
        softWall[0] = pack.utils.ImageLoader.load("res/textures/softWall1.png");
        softWall[1] = pack.utils.ImageLoader.load("res/textures/softWall2.png");
        softWall[2] = pack.utils.ImageLoader.load("res/textures/softWall3.png");
        softWall[3] = pack.utils.ImageLoader.load("res/textures/softWall4.png");
        repairFood = pack.utils.ImageLoader.load("res/textures/repairFood.png");
        cannonFood = pack.utils.ImageLoader.load("res/textures/cannonFood.png");
        bulletFood = pack.utils.ImageLoader.load("res/textures/bulletFood.png");
        upgrader = pack.utils.ImageLoader.load("res/textures/upgrader.png");
        artilleryBase = pack.utils.ImageLoader.load("res/textures/artilleryBase.png");
        artilleryGun = pack.utils.ImageLoader.load("res/textures/artilleryGun.png");
        artilleryDead = pack.utils.ImageLoader.load("res/textures/artilleryDead.png");

        mine = pack.utils.ImageLoader.load("res/textures/mine.png");
        barbedWire = pack.utils.ImageLoader.load("res/textures/barbedWire.png");

        enemyTank = pack.utils.ImageLoader.load("res/textures/enemyTank.png");
        enemyTankGun = pack.utils.ImageLoader.load("res/textures/enemyTankGun.png");
        enemyCar = pack.utils.ImageLoader.load("res/textures/enemyCar.png");
        enemyCarGun = pack.utils.ImageLoader.load("res/textures/enemyCarGun.png");

        bush = pack.utils.ImageLoader.load("res/textures/bush.png");
        cannonNum = pack.utils.ImageLoader.load("res/textures/cannonNum.png");
        bulletNum = pack.utils.ImageLoader.load("res/textures/bulletNum.png");
        healthNum = pack.utils.ImageLoader.load("res/textures/healthNum.png");

        gameEnder = pack.utils.ImageLoader.load("res/textures/gameEnder.png");



        //MENU
        menuBackground = pack.utils.ImageLoader.load("res/textures/menuBackground.jpg");
        menuBackgroundBlurred = pack.utils.ImageLoader.load("res/textures/menuBackgroundBlurred.jpg");

        createGame = new BufferedImage[2];
        createGame[0] = pack.utils.ImageLoader.load("res/textures/createGame0.png");
        createGame[1] = pack.utils.ImageLoader.load("res/textures/createGame1.png");

        joinGame = new BufferedImage[2];
        joinGame[0] = pack.utils.ImageLoader.load("res/textures/joinGame0.png");
        joinGame[1] = pack.utils.ImageLoader.load("res/textures/joinGame1.png");

        exit = new BufferedImage[2];
        exit[0] = pack.utils.ImageLoader.load("res/textures/exit0.png");
        exit[1] = pack.utils.ImageLoader.load("res/textures/exit1.png");

        easy = new BufferedImage[2];
        easy[0] = pack.utils.ImageLoader.load("res/textures/easy0.png");
        easy[1] = pack.utils.ImageLoader.load("res/textures/easy1.png");

        normal = new BufferedImage[2];
        normal[0] = pack.utils.ImageLoader.load("res/textures/normal0.png");
        normal[1] = pack.utils.ImageLoader.load("res/textures/normal1.png");

        hard = new BufferedImage[2];
        hard[0] = pack.utils.ImageLoader.load("res/textures/hard0.png");
        hard[1] = pack.utils.ImageLoader.load("res/textures/hard1.png");

        exitBackToMenu = new BufferedImage[2];
        exitBackToMenu[0] = pack.utils.ImageLoader.load("res/textures/exitBackToMenu0.png");
        exitBackToMenu[1] = pack.utils.ImageLoader.load("res/textures/exitBackToMenu1.png");

        cursor = pack.utils.ImageLoader.load("res/textures/cursor.png");

        youLost = pack.utils.ImageLoader.load("res/textures/youLost.png");
        youWin = pack.utils.ImageLoader.load("res/textures/youWin.png");

    }

}
