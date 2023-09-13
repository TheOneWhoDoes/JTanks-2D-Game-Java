package pack.states;

import pack.Game;
import pack.entities.BarbedWire;
import pack.entities.Bush;
import pack.entities.HardWall;
import pack.entities.players.ClientPlayer;
import pack.entities.manager.EntityManager;
import pack.graphics.Assets;
import pack.graphics.Camera;
import pack.input.KeyManager;
import pack.input.MouseManager;
import pack.network.Client;
import pack.sound.ExampleSounds;
import pack.world.World;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * this class is the state of a client-player
 * which sends data to and receives data from
 * the connected server and updates players screen
 */
public class ClientGameState extends State {

    private World world;
    private EntityManager entityManager;
    private boolean gotStatics = false;
    private ArrayList<HardWall> hardWalls;
    private ArrayList<BarbedWire> barbedWires;
    private ArrayList<Bush> bushes;
    private int c = 0;

    private int health;
    private int cannon;
    private int bullet;


    public ClientGameState() {
        world = new World("res/world/worldFile.txt");

    }


        @Override
    public void tick() {


         //GETTING STATIC STUFF FOR 1 TIME

         if (!gotStatics) {

             InputStream wallsIn = Client.getInputStream();
             try {
                 ObjectInputStream wallReader = new ObjectInputStream(wallsIn);
                 hardWalls = (ArrayList<HardWall>) wallReader.readObject();

             } catch (Exception ex) {
                 Game.setState(new MainMenuState());
                 return;
             }

             InputStream barbedWiresIn = Client.getInputStream();
             try {
                 ObjectInputStream barbedWireReader = new ObjectInputStream(barbedWiresIn);
                 barbedWires = (ArrayList<BarbedWire>) barbedWireReader.readObject();

             } catch (Exception ex) {
                 Game.setState(new MainMenuState());
                 return;
             }

             InputStream BushesIn = Client.getInputStream();
             try {
                 ObjectInputStream BushesReader = new ObjectInputStream(BushesIn);
                 bushes = (ArrayList<Bush>) BushesReader.readObject();

             } catch (Exception ex) {
                 Game.setState(new MainMenuState());
                 return;
             }


             gotStatics = true;
         }


         //SENDING COMMAND

            StringBuilder command = new StringBuilder();
            if (KeyManager.fullLife) {
                command.append("1");
                KeyManager.fullLife = false;
            } else
                command.append("0");

            if (KeyManager.fullCB) {
                command.append("1");
                KeyManager.fullCB =false;
            } else
                command.append("0");

            if (KeyManager.upgradeWeapon) {
                command.append("1");
                KeyManager.upgradeWeapon = false;
            } else
                command.append("0");

            command.append(",");


        OutputStream out = Client.getOutputStream();
        if (KeyManager.up)
            command.append("1,");
        else
            command.append("0,");

        if (KeyManager.down)
            command.append("1,");
        else
            command.append("0,");

        if (KeyManager.right)
            command.append("1,");
        else
            command.append("0,");

        if (KeyManager.left)
            command.append("1,");
        else
            command.append("0,");

        if (MouseManager.rightMouseButton)
            command.append("1,");
        else
            command.append("0,");

        if (MouseManager.leftMouseButton)
            command.append("1,");
        else
            command.append("0,");

            command.append(MouseManager.angle);

        try {
            out.write(command.toString().getBytes());
        } catch (IOException e) {
            Game.setState(new MainMenuState());
            return;
        }


        //GETTING PLAYERS STATE
        byte[] buffer = new byte[1024];
        int size = -1;
        InputStream stateIn = Client.getInputStream();
        try {
            size = stateIn.read(buffer);
        } catch (IOException e) {
            Game.setState(new MainMenuState());
            return;
        }

        String sounds;
        String position = new String(buffer, 0, size);
        String[] tokens = position.split(",");

        float x = 0,y = 0;
        try {
            sounds = tokens[0];
            for (int i = 0; i < sounds.length(); i++) {
                switch (sounds.charAt(i)) {
                    case '1':
                        ExampleSounds.playAgree(false); break;
                    case '2':
                        ExampleSounds.playCannon(false); break;
                    case '3':
                        ExampleSounds.playEnemyBulletToMyTank(false); break;
                    case '4':
                        ExampleSounds.playEnemyShot(false); break;
                    case '5':
                        ExampleSounds.playLightGun(false); break;
                    case '6':
                        ExampleSounds.playSelect(false); break;
                }
            }
            health = Integer.parseInt(tokens[1]);
            cannon = Integer.parseInt(tokens[2]);
            bullet = Integer.parseInt(tokens[3]);
            x = Float.parseFloat(tokens[4]);
            y = Float.parseFloat(tokens[5]);
        } catch (Exception ex) {
            Game.setState(new MainMenuState());
            return;
        }

        Camera.centerOnEntity(x, y, 100, 100);

        InputStream managerIn = Client.getInputStream();
            try {
                ObjectInputStream managerReader = new ObjectInputStream(managerIn);
                entityManager = (EntityManager) managerReader.readObject();
                System.out.println(entityManager.gameWin);
                System.out.println(entityManager.gameOver);


                //SAVE INTO FILE TO CHECK THE SIZE
//                c++;
//                if (c == 50) {
//                    try (FileOutputStream fout = new FileOutputStream("manager.bin");
//                         ObjectOutputStream ooo = new ObjectOutputStream(fout)) {
//
//                        ooo.writeObject(entityManager);
//                    }
//                }

                //

            } catch (Exception ex) {
                Game.setState(new MainMenuState());
                return;
            }
        }

    @Override
    public void render(Graphics2D g) {
        if (entityManager.gameWin) {
            Game.setState(new LostWinState(LostWinState.LostWin.WIN));
            return;
        }

        if (entityManager.gameOver) {
            Game.setState(new LostWinState(LostWinState.LostWin.LOST));
            return;
        }

        world.render(g);
        entityManager.render(g, false);
        for (HardWall h : hardWalls)
            if ((h.getX() + h.getWidth() > Camera.getXOffset()) && (h.getX() < (Camera.getXOffset() + Game.frameWidth)) && (h.getY() + h.getHeight() > Camera.getYOffset())
                    && (h.getY() < (Camera.getYOffset() + Game.frameHeight)))
            h.render(g);

        for (BarbedWire b : barbedWires)
            if ((b.getX() + b.getWidth() > Camera.getXOffset()) && (b.getX() < (Camera.getXOffset() + Game.frameWidth)) && (b.getY() + b.getHeight() > Camera.getYOffset())
                    && (b.getY() < (Camera.getYOffset() + Game.frameHeight)))
            b.render(g);

        for (Bush b : bushes)
            if ((b.getX() + b.getWidth() > Camera.getXOffset()) && (b.getX() < (Camera.getXOffset() + Game.frameWidth)) && (b.getY() + b.getHeight() > Camera.getYOffset())
                    && (b.getY() < (Camera.getYOffset() + Game.frameHeight)))
            b.render(g);

        renderPlayerSate(g);
    }

    private void renderPlayerSate(Graphics2D g) {
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
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
