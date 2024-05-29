package OrderStation.OrderElements;

import Elements.Node;

public class OrderFish extends Node {
    private int yVelocity = -1;
    public OrderFish(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/orderstation/order_fish.png");
    }

    public void waitForCustomer(){
        if(y < 225 || y > 240)
            yVelocity*=-1;
        y+=yVelocity;
    }
}
