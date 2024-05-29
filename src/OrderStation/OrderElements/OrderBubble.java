package OrderStation.OrderElements;

import Elements.Node;

public class OrderBubble extends Node {
    public OrderBubble(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/customers/customer_elements/bubble.png");
    }
}
