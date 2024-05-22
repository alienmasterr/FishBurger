package OrderStation;

import OrderStation.OrderElements.OrderBackground;
import OrderStation.OrderElements.OrderFish;
import OrderStation.OrderElements.OrderTable;
import Menu.Game;
import java.awt.*;

public class OrderStation {
    private OrderFish orderFish = new OrderFish(0, 200, 500, 500);
    private OrderTable orderTable = new OrderTable(0, 500, Game.WIDTH, 200);
    private OrderBackground background = new OrderBackground(0, 0, Game.WIDTH, 500);
    public void draw(Graphics2D g2d){
        orderTable.draw(g2d);
        background.draw(g2d);
        orderFish.draw(g2d);
    }
}
