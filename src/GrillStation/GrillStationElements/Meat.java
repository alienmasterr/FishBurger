package GrillStation.GrillStationElements;

import Elements.Node;
import Enums.CookingState;
import GrillStation.GrillStation;
import Menu.GameMenu;
import Products.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Meat extends Product {

    private Timer timer;

    public static boolean isGrilling = true;
    private boolean grilling = false;

    public Meat(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/meat/rawmeat.png");
    }

    public BufferedImage getTheImage() {
        return image;
    }

    public Meat(int x, int y, int width, int height, boolean grilling) {
        super(x, y, width, height);
        this.grilling = grilling;
        image = getImage("/meat/rawmeat.png");
    }

    public void setGrilling(boolean grilling) {
        this.grilling = grilling;
    }
    public boolean getGrilling(){
        return grilling;
    }

    private int yVelocity = -4;

    public void beReady() {
    }

    public void grilling() {
        if (isGrilling) {
            y += yVelocity;
            yVelocity *= -1;
        }
    }

    public void startGrilling() {
        if (timer == null) {
            setupTimer();
        }
        timer.start();
    }

    int counter = 0;

    public void setupTimer() {
            this.timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   if(grilling) {
                       grilling();
                       counter++;
                   }
                    if(GrillStation.meatSent){
                        timer.stop();
                    }
                   if (counter == 20) {
                        image = getImage("/meat/meat.png");
                    } else if (counter == 30) {
                        image = getImage("/meat/overcookedmeat.png");
                    } else if (counter == 40) {
                        image = getImage("/meat/burntmeat.png");
                        timer.stop();
                    }
                }
            });
    }

    public void getFlipped() {
            y = getY() - 15;
            image = getImage("/meat/rawmeat1.png");
            y = getY() + 15;
    }
}

