package OrderStation.OrderElements;

import Elements.Node;
import OrderStation.EmptyBubble;
import OrderStation.OrderStation;

import java.awt.image.BufferedImage;

public class Customer extends Node {
    //дописати рандомайзер вибору картинок для кастомерів
    private int yVelocity = -2;
    private OrderBubble bubble = new OrderBubble(200, 40, 300, 300);
    private EmptyBubble emptyBubble = new EmptyBubble(200, 40, 300, 300);

    public Customer(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/customer1.png");
    }

    public void goToTable(){
        y+=yVelocity;
        if(y <= 140 || y >= 180)
            yVelocity*=-1;
        x-=3;
    }

    public OrderBubble getOrderBubble(){
        return bubble;
    }

    public boolean gotToTable(){
        return x <= 480;
    }

    public EmptyBubble getEmptyBubble() {
        return emptyBubble;
    }

    public void waitForOrder(){
        yVelocity/=2;
        if(y < 140 || y > 160)
            yVelocity*=-1;
        y+=yVelocity;
        yVelocity*=2;
    }
}
