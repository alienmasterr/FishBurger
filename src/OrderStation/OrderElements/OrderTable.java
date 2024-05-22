package OrderStation.OrderElements;

import Elements.Node;

public class OrderTable extends Node {
    public OrderTable(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/ordertable.png");
    }
}
