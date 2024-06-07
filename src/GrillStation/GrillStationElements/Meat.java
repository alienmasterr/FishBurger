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

    public static boolean isGrilling = true;
  //  boolean canFlip = false;
    private boolean grilling = false;



    public Meat(int x, int y, int width, int height) {
        super(x, y, width, height);

        //path = "/meat/meat.png";

        image = getImage("/meat/rawmeat.png");
    }

    public Meat(int x, int y, int width, int height, boolean grilling) {
        super(x, y, width, height);
        this.grilling = grilling;
        //path = "/meat/meat.png";
        image = getImage("/meat/rawmeat.png");
    }

//    public boolean isOnGrill(boolean isOnGrill) {
//        return isOnGrill;
//    }

    public void setGrilling(boolean grilling) {
        this.grilling = grilling;
    }
    public boolean getGrilling(){
        return grilling;
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
                   if(grilling) {
                       grilling();
                       counter++;
                   }
                    if(GrillStation.meatSent){
                        timer.stop();
                    }
                   if (counter == 10) {
                        image = getImage("/meat/meat.png");
                        //canFlip = true;
                        //isGrilling = false;
                    } else if (counter == 20) {
                        image = getImage("/meat/overcookedmeat.png");
                        //canFlip = false;
                    } else if (counter == 30) {
                        image = getImage("/meat/burntmeat.png");
                        timer.stop();
                    }
                }
            });

    }

    public void getFlipped() {
        //if(canFlip){
            y = getY() - 15;
            image = getImage("/meat/rawmeat1.png");
            y = getY() + 15;
       // }
    }
}

