package Menu;

import Elements.Mouse;
import Enums.FrameState;
import Enums.LevelState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class Game extends JFrame {
    public static final int HEIGHT = 800;
    public static final int WIDTH = 1000;
    public static Mouse mouse = new Mouse();
    private GameMenu gameMenu = new GameMenu(this);
    private MainMenu mainMenu = new MainMenu(this);
    private LevelMenu levelMenu = new LevelMenu(this);
    public FrameState frameState = FrameState.MAIN_MENU;
    public LevelState levelState;
    private Box box;

    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        super("Fish Burger!");
        setSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.black);
        setVisiblePanel(frameState);
    }

    public void startGame(LevelState level){
        levelState = level;
        setVisiblePanel(FrameState.GAME);
    }

    public void setVisiblePanel(FrameState newState) {
        if(box != null)
            remove(box);
        frameState = newState;
        switch (frameState){
            case MAIN_MENU -> setBox(mainMenu);
            case CHOOSE_LEVEL -> setBox(levelMenu);
            case GAME -> setBox(gameMenu);
            case EXIT -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        pack();
        setVisible(true);
    }

    private void setBox(JPanel panel) {
        if(frameState == FrameState.GAME)
            gameMenu.restart();
        box = new Box(BoxLayout.Y_AXIS);
        box.add(Box.createVerticalGlue());
        box.add(panel);
        box.add(Box.createVerticalGlue());
        add(box);
    }
}
