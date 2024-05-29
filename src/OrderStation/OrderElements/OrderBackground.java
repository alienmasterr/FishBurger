package OrderStation.OrderElements;

import Elements.Node;

public class OrderBackground extends Node {
    public OrderBackground(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/orderstation/orderbackground.png");
    }
}
