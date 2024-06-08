package Store.StoreElements;

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

