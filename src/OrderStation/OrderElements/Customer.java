package OrderStation.OrderElements;

import Elements.Node;

public class Customer extends Node {
    //дописати рандомайзер вибору картинок для кастомерів
    private int yVelocity = -2;
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

    public boolean gotToTable(){
        return x <= 550;
    }
}
