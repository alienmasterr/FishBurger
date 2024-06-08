package Store;

import Elements.Node;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accessories extends Node {
    public int speed = 25;
    public Timer timer;
    public boolean falling = false;

    private int price;

    public Accessories(int x, int y, int width, int height, int price) {
        super(x, y, width, height);
        this.price = price;

        timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (falling) {
                    update();
                }
            }
        });
    }

    public void startFalling() {
        falling = true;
        timer.start();
    }

    public void update() {
        y += speed;
    }

    public int getPrice() {
        return price;
    }

}

class AccessoryOne extends Accessories {

    public AccessoryOne(int x, int y, int width, int height, int price) {
        super(x, y, width, height, price);
        image = getImage("/temp.png");


    }


}

class AccessoryTwo extends Accessories {
    private int speed = 25;
    private Timer timer;
    private boolean falling = false;

    public AccessoryTwo(int x, int y, int width, int height, int price) {
        super(x, y, width, height, price);
        image = getImage("/temp.png");
    }
}

class AccessoryThree extends Accessories {
    public AccessoryThree(int x, int y, int width, int height, int price) {
        super(x, y, width, height, price);
        image = getImage("/store/lockedproduct.png");
    }
}

class AccessoryFour extends Accessories {
    public AccessoryFour(int x, int y, int width, int height, int price) {
        super(x, y, width, height, price);
        image = getImage("/temp.png");
    }
}

class AccessoryFive extends Accessories {
    public AccessoryFive(int x, int y, int width, int height, int price) {
        super(x, y, width, height, price);
        image = getImage("/temp.png");
    }
}

class AccessorySix extends Accessories {
    public AccessorySix(int x, int y, int width, int height, int price) {
        super(x, y, width, height, price);
        image = getImage("/temp.png");
    }
}
