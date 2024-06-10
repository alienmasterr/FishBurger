package Menu;

import Elements.Node;
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
    ButtonExit buttonExit = new ButtonExit(Game.WIDTH/2-100, Game.HEIGHT-220, 200, 200);
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        //buttonExit.draw(g2d);
        drawFirstPage(g2d);

    }

    private void drawFirstPage(Graphics2D g2d){
        FirstPage firstPage = new FirstPage(70,10,Game.WIDTH-140,Game.HEIGHT-20);
        firstPage.draw(g2d);

    }


}

class ButtonExit extends Node {
    public ButtonExit(int x, int y, int w, int h) {
        super(x, y, w, h);
        image = getImage("/temp.png");
    }
}


