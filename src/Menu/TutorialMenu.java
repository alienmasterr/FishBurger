package Menu;

import Menu.TutorialElements.FirstPage;

import javax.swing.*;
import java.awt.*;

public class TutorialMenu extends JPanel {
    private Game parent;
    public TutorialMenu(Game parent){
        super();
        this.parent = parent;
        setStaticSize();
        setLayout(null);
    }

    private void setStaticSize(){
        setMaximumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setMinimumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        drawFirstPage(g2d);

    }

    private void drawFirstPage(Graphics2D g2d){
        FirstPage firstPage = new FirstPage(100,100,Game.WIDTH-200,Game.HEIGHT-200);
        firstPage.draw(g2d);

    }


}


