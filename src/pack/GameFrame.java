package pack;

import javax.swing.JFrame;

/**
 * GameFrame is just
 * for main frame
 */
public class GameFrame extends JFrame {

    public GameFrame(String title) {
        super(title);
    }

    // creating buffer strategy
    public void initBufferStrategy() {
        createBufferStrategy(3);
    }

}
