package Menu;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    public static final int HEIGHT = 800;
    public static final int WIDTH = 1000;

    public static void main(String[] args) {
        new Game();
    }
    public Game(){
        super("Fish Burger!");
        setSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        MainPanel mainPanel = new MainPanel();
        add(mainPanel);
        setVisible(true);
    }
}
