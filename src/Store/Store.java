package Store;

import Menu.GameMenu;

import java.awt.*;

import Menu.*;

import java.awt.*;

public class Store {
    private final GameMenu.GamePanel parent;
    public Store(GameMenu.GamePanel parent) {
        this.parent = parent;
    }

    public void draw(Graphics2D g2d) {
        drawBase(g2d);

    }

    private void drawBase(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
    }
}
