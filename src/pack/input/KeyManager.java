package pack.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * this class is
 * for managing keys
 */
public class KeyManager implements KeyListener {

    public static boolean up, down, left, right;


    public ArrayList<Integer> arrayListLife = new ArrayList<>();
    public ArrayList<Integer> arrayListCB = new ArrayList<>();
    public ArrayList<Integer> arrayListUpgrade = new ArrayList<>();
    public final ArrayList<Integer> life;
    public final ArrayList<Integer> CB;
    public final ArrayList<Integer> upgrade;

    public static boolean fullLife = false;
    public static boolean fullCB = false;
    public static boolean upgradeWeapon = false;


    /**
     * this method is
     * for cheat code
     */
    public void initialKey() {
        life.add(KeyEvent.VK_E);
        life.add(KeyEvent.VK_F);
        life.add(KeyEvent.VK_K);

        CB.add(KeyEvent.VK_G);
        CB.add(KeyEvent.VK_P);
        CB.add(KeyEvent.VK_N);

        upgrade.add(KeyEvent.VK_V);
        upgrade.add(KeyEvent.VK_Q);
        upgrade.add(KeyEvent.VK_T);
    }

    public KeyManager() {
        upgrade = new ArrayList<>();
        CB = new ArrayList<>();
        life = new ArrayList<>();
        initialKey();
    }

    /**
     * this method is
     * for cheat code
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'e':
                arrayListLife.add(KeyEvent.VK_E);
                arrayListUpgrade = new ArrayList<>();
                arrayListCB = new ArrayList<>();
                break;
            case 'f':
                arrayListLife.add(KeyEvent.VK_F);
                arrayListUpgrade = new ArrayList<>();
                arrayListCB = new ArrayList<>();
                break;
            case 'k':
                arrayListLife.add(KeyEvent.VK_K);
                arrayListUpgrade = new ArrayList<>();
                arrayListCB = new ArrayList<>();
                break;
            case 'g':
                arrayListCB.add(KeyEvent.VK_G);
                arrayListUpgrade = new ArrayList<>();
                arrayListLife = new ArrayList<>();
                System.out.println("G");
                break;
            case 'p':
                arrayListCB.add(KeyEvent.VK_P);
                arrayListUpgrade = new ArrayList<>();
                arrayListLife = new ArrayList<>();
                System.out.println("p");
                break;
            case 'n':
                arrayListCB.add(KeyEvent.VK_N);
                arrayListUpgrade = new ArrayList<>();
                arrayListLife = new ArrayList<>();
                System.out.println("N");
                break;
            case 'v':
                arrayListUpgrade.add(KeyEvent.VK_V);
                arrayListLife = new ArrayList<>();
                arrayListCB = new ArrayList<>();
                break;
            case 'q':
                arrayListUpgrade.add(KeyEvent.VK_Q);
                arrayListCB = new ArrayList<>();
                arrayListLife = new ArrayList<>();
                break;
            case 't':
                arrayListUpgrade.add(KeyEvent.VK_T);
                arrayListCB = new ArrayList<>();
                arrayListLife = new ArrayList<>();
                break;

            default:
                arrayListCB = new ArrayList<>();
                arrayListUpgrade = new ArrayList<>();
                arrayListLife = new ArrayList<>();
                break;
        }

        if (arrayListLife.equals(life)) {
            fullLife = true;
            arrayListLife = new ArrayList<>();
        } else if (arrayListCB.equals(CB)) {
            fullCB = true;
            arrayListCB = new ArrayList<>();
        } else if (arrayListUpgrade.equals(upgrade)) {
            upgradeWeapon = true;
            arrayListUpgrade = new ArrayList<>();
        } else if (arrayListLife.size() == 3)
            arrayListLife = new ArrayList<>();

        else if (arrayListUpgrade.size() == 3)
            arrayListUpgrade = new ArrayList<>();

        else if (arrayListCB.size() == 3)
            arrayListCB = new ArrayList<>();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
            down = true;
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
            up = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
            right = true;
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
            left = true;
    }

    /**
     * managing keys that released
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
            down = false;
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
            up = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
            right = false;
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
            left = false;

    }
}
