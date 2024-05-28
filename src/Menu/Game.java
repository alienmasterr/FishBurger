package Menu;

import Elements.Mouse;
import Enums.FrameState;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    public static Mouse mouse = new Mouse();
    public static final int HEIGHT = 800;
    public static final int WIDTH = 1000;
    private GameMenu gameMenu = new GameMenu();
    private MainMenu mainMenu = new MainMenu(this);
    private LevelMenu levelMenu = new LevelMenu();
    public FrameState frameState = FrameState.MAIN_MENU;
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
        pack();
        setVisible(true);
    }

    public void setVisiblePanel(FrameState newState) {
        setVisible(false);
        if(box != null)
            remove(box);
        frameState = newState;
        switch (frameState){
            case MAIN_MENU -> setBox(mainMenu);
            case CHOOSE_LEVEL -> setBox(levelMenu);
            case GAME -> setBox(gameMenu);
        }
        setVisible(true);
    }

    private void setBox(JPanel panel) {
        box = new Box(BoxLayout.Y_AXIS);
        box.add(Box.createVerticalGlue());
        box.add(panel);
        box.add(Box.createVerticalGlue());
        add(box);
    }
}
