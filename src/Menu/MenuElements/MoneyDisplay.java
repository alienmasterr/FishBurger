package Menu.MenuElements;

import Elements.Node;
import java.awt.*;

public class MoneyDisplay extends Node {
    private double currentMoney;
    public MoneyDisplay(int x, int y, int width, int height){
        super(x, y, width, height);
        image = getImage("/ratingstation/coin.png");
    }

    public void setCurrentMoney(double currentMoney) {
        this.currentMoney = currentMoney;
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
        drawMoney(g2d);
    }

    private void drawMoney(Graphics2D g2d){
        g2d.setPaint(Color.black);
        g2d.getFont().deriveFont(Font.PLAIN, 40);
        g2d.drawString(String.format("%.2f", currentMoney) + "$", getX()+getWidth()+5, (int) (getY()+getHeight()/1.5));
    }
}
