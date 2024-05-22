package OrderStation.OrderElements;

import Elements.Node;

public class OrderFish extends Node {
    public OrderFish(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/order_fish.png");
    }
}
