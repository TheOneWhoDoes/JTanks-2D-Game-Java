package pack.states;

import pack.Game;
import pack.ThreadPool;
import pack.entities.manager.EntityManager;
import pack.graphics.Assets;
import pack.input.MouseManager;
import pack.network.Client;
import pack.network.Server;
import pack.sound.ExampleSounds;
import pack.ui.ClickListener;
import pack.ui.MyUIManager;
import pack.ui.UIImageButton;
import java.awt.*;

/**
 * this class is the first state of the game,
 * which is the menu, that user selects a way
 * to start the game
 */
public class MainMenuState extends State {

    private MyUIManager uiManager;

    public MainMenuState() {
        ExampleSounds.closeSoundEndOfGame();
        ExampleSounds.closeSound1();
        ExampleSounds.playStartup();
        uiManager = new MyUIManager();
        MouseManager.setUIManager(uiManager);

        initButtons();

    }

    private void initButtons() {
        UIImageButton createGame = new UIImageButton(250, 100, 300, 45, Assets.createGame, new ClickListener() {
            @Override
            public void onClick() {

            }
        });

        UIImageButton easy = new UIImageButton(550, 105, 90, 35, Assets.easy, new ClickListener() {
            @Override
            public void onClick() {

                GameState gameState = new GameState(GameState.Difficulty.EASY);
                ThreadPool.execute(new Server(gameState));
                Game.setState(gameState);
                MouseManager.setUIManager(null);
            }
        });

        UIImageButton normal = new UIImageButton(650, 105, 90, 35, Assets.normal, new ClickListener() {
            @Override
            public void onClick() {

                GameState gameState = new GameState(GameState.Difficulty.NORMAL);
                ThreadPool.execute(new Server(gameState));
                Game.setState(gameState);
                MouseManager.setUIManager(null);
            }
        });

        UIImageButton hard = new UIImageButton(750, 105, 90, 35, Assets.hard, new ClickListener() {
            @Override
            public void onClick() {

                GameState gameState = new GameState(GameState.Difficulty.HARD);
                ThreadPool.execute(new Server(gameState));
                Game.setState(gameState);
                MouseManager.setUIManager(null);
            }
        });

        UIImageButton joinGame = new UIImageButton(250, 150, 300, 45, Assets.joinGame, new ClickListener() {
            @Override
            public void onClick() {
                Client client = new Client("127.0.0.1");
                boolean connected = client.connect();
                if (connected) {
                    Game.setState(new ClientGameState());
                    MouseManager.setUIManager(null);

                }
            }
        });

        UIImageButton exit = new UIImageButton(250, 200, 300, 45, Assets.exit, new ClickListener() {
            @Override
            public void onClick() {
                System.exit(0);
            }
        });


        uiManager.addObject(easy);
        uiManager.addObject(normal);
        uiManager.addObject(hard);
        uiManager.addObject(createGame);
        uiManager.addObject(joinGame);
        uiManager.addObject(exit);
    }


    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(Assets.menuBackground, 0, 0, Game.frameWidth, Game.frameHeight, null);
        uiManager.render(g);
    }

    @Override
    public EntityManager getEntityManager() {
        return null;
    }
}