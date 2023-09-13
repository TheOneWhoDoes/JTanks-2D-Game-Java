package pack.states;

import pack.Game;
import pack.entities.manager.EntityManager;
import pack.graphics.Assets;
import pack.input.MouseManager;
import pack.sound.ExampleSounds;
import pack.ui.ClickListener;
import pack.ui.MyUIManager;
import pack.ui.UIImageButton;

import java.awt.*;

/**
 * this class is the state of players
 * when they lose or win the game
 */
public class LostWinState extends State {
    public enum LostWin {
        LOST, WIN;
    }

    private MyUIManager uiManager;
    private LostWin state;


    public LostWinState(LostWin state) {
        ExampleSounds.closeSound1();
        ExampleSounds.closeSoundStartUp();
        ExampleSounds.playEndOfGame();
        uiManager = new MyUIManager();
        MouseManager.setUIManager(uiManager);
        this.state = state;
        initButtons();
    }

    private void initButtons() {

        UIImageButton exitToMenu = new UIImageButton(600, 400, 300, 45, Assets.exitBackToMenu, new ClickListener() {
            @Override
            public void onClick() {

                Game.setState(new MainMenuState());
            }
        });

        uiManager.addObject(exitToMenu);
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(Assets.menuBackgroundBlurred, 0, 0, Game.frameWidth, Game.frameHeight, null);
        if (state == LostWin.LOST)
            g.drawImage(Assets.youLost, 250, 200, Assets.youLost.getWidth(), Assets.youLost.getHeight(), null);

        else
            g.drawImage(Assets.youWin, 250, 200, Assets.youWin.getWidth(), Assets.youWin.getHeight(), null);

        uiManager.render(g);
    }

    @Override
    public EntityManager getEntityManager() {
        return null;
    }
}
