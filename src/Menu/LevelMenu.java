package Menu;

import javax.swing.*;
import java.awt.*;

public class LevelMenu extends JPanel {

    public LevelMenu(){
        super();
        setStaticSize();
        setBackground(Color.PINK);
    }

    private void setStaticSize(){
        setMaximumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setMaximumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
    }
}
