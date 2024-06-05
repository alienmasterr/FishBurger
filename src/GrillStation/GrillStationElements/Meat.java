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
    //private String path;
    private Timer timer;

    boolean isGrilling = true;
    boolean canFlip = false;


    public Meat(int x, int y, int width, int height) {
        super(x, y, width, height);

        //path = "/meat/meat.png";

        image = getImage("/meat/rawmeat.png");
    }


    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private int yVelocity = -4;

    public void beReady() {
    }

    public void beShrownAway() {
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

    //todo цей таймер треба стопати
    public void setupTimer() {
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("counter: " + counter);
                System.out.println("смажимо");
                grilling();
                counter++;
                if (counter == 5) {
                    //image = getImage("/meat/rawmeat.png");
                } else if (counter == 10) {
                    System.out.println("10 sec");
                    image = getImage("/meat/meat.png");
                    canFlip = true;
                    isGrilling = false;
                } else if (counter == 20) {
                    System.out.println("20 sec");
                    image = getImage("/meat/overcookedmeat.png");
                    canFlip = false;
                } else if (counter == 30) {
                    System.out.println("30 sec");
                    image = getImage("/meat/burntmeat.png");
                    timer.stop();
                }
            }
        });
    }

    public void getFlipped() {
        if(canFlip){
            y = getY() - 15;
            image = getImage("/meat/rawmeat1.png");
            y = getY() + 15;
        }

    }
}

