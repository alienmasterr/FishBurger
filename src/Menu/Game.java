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
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.black);
        MainPanel mainPanel = new MainPanel();
        setBox(mainPanel);
        setVisible(true);
    }

    private void setBox(JPanel panel){
        Box box = new Box(BoxLayout.Y_AXIS);
        box.add(Box.createVerticalGlue());
        box.add(panel);
        box.add(Box.createVerticalGlue());
        add(box);
    }
}
