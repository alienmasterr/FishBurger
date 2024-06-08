package Store;

import Elements.Node;
import Level.Level;

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

class Chair extends Accessories {

    public Chair(int x, int y, int width, int height, int price) {
        super(x, y, width, height, price);
        image = getImage("/temp.png");
    }


}

class Table extends Accessories {
    public Table(int x, int y, int width, int height, int price) {
        super(x, y, width, height, price);
        image = getImage("/temp.png");
    }
}

class GoldenSpatula extends Accessories {
    public GoldenSpatula(int x, int y, int width, int height, int price) {
        super(x, y, width, height, price);
        if(Level.levelState < 2){
            image = getImage("/store/lockedproduct.png");
        }else {
            image = getImage("/store/icon.png");
        }
    }
}

class Music extends Accessories {
    public Music(int x, int y, int width, int height, int price) {
        super(x, y, width, height, price);
        if(Level.levelState <2){
            image = getImage("/store/lockedproduct.png");
        }else {
            image = getImage("/store/icon.png");
        }
    }
}

class Painting extends Accessories {
    public Painting(int x, int y, int width, int height, int price) {
        super(x, y, width, height, price);
        if(Level.levelState <3){
            image = getImage("/store/lockedproduct.png");
        }else {
            image = getImage("/temp.png");
        }
    }
}

class Icon extends Accessories {
    public Icon(int x, int y, int width, int height, int price) {
        super(x, y, width, height, price);
        if(Level.levelState < 3){
            image = getImage("/store/lockedproduct.png");
        }else {
            image = getImage("/store/icon.png");
        }
    }
}
