package OrderStation.OrderElements;

import Elements.Node;
import Products.Product;

public class OrderBubble extends Node {
    public OrderBubble(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/bubble.png");
    }
}
