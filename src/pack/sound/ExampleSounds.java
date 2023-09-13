package pack.sound;

import pack.entities.manager.EntityManager;

/**
 * this class manages playing different
 * audios in the game
 */
public class ExampleSounds {

    private static Sound sound1 = null;
    private static Sound soundEndOfGame = null;
    private static Sound  SoundStart = null;

    public static void playAgree(boolean write) {
        new Sound("res/Sounds/agree.wav", false,0);
        if (write)
            EntityManager.soundCharacters.add('1');
    }

    public static void playCannon(boolean write) {
        new Sound("res/Sounds/cannon.wav",false,0);
        if (write)
            EntityManager.soundCharacters.add('2');
    }

    public static void playEndOfGame() {
       soundEndOfGame =  new Sound("res/Sounds/endOfGame.wav",false,0);
    }

    public static void closeSoundEndOfGame() {
        if(soundEndOfGame != null) {
            soundEndOfGame.getClip().close();
        }
    }

    public static void playEnemyBulletToMyTank(boolean write) {
        new Sound("res/Sounds/EnemyBulletToMyTank.wav",false,0);
        if (write)
            EntityManager.soundCharacters.add('3');
    }


    public static void playEnemyShot(boolean write) {
        new Sound("res/Sounds/enemyshot.wav",false,0);
        if (write)
            EntityManager.soundCharacters.add('4');
    }


    public static void playGameSound1() {
        sound1 = new Sound("res/Sounds/gameSound1.wav", true, 10);
    }

    public static void closeSound1() {
        if(sound1!=null) {
            sound1.getClip().close();
        }
    }

    public static void playLightGun(boolean write) {
        new Sound("res/Sounds/lightgun.wav",false,0);
        if (write)
            EntityManager.soundCharacters.add('5');
    }


    public static void playSelect(boolean write) {
        new Sound("res/Sounds/select.wav",false,0);
        if (write)
            EntityManager.soundCharacters.add('6');
    }

    public static void playStartup() {
       SoundStart =  new Sound("res/Sounds/startup.wav",false,0);
    }

    public static void closeSoundStartUp() {
        if(SoundStart!=null) {
            SoundStart.getClip().close();
        }
    }


}
