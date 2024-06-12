package GrillStation.GrillStationElements;

import Elements.Node;
import Enums.CookingState;
import GrillStation.GrillStation;
import Menu.GameMenu;
import Menu.MenuElements.SoundPlayer;
import Products.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Meat extends Product {

    private Timer timer;
    public boolean isGrilling = true;
    private boolean isGrillingRightSide = true;
    private boolean grilling = false;
    private int sideOne=0;
    private int sideTwo=0;
    private boolean canFlip = false;

    public Meat(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/meat/rawmeat.png");
    }

    public int getSideOne() {
        return sideOne;
    }
    public int getSideTwo() {
        return sideTwo;
    }
    public void setSideOne(int sideOne) {
        this.sideOne = sideOne;
    }
    public void setSideTwo(int sideTwo) {
        this.sideTwo = sideTwo;
    }

    public Meat(int x, int y, int width, int height, boolean grilling, int sideOne, int sideTwo, boolean canFlip) {
        super(x, y, width, height);
        this.grilling = grilling;
        this.sideOne = sideOne;
        this.sideTwo = sideTwo;
        this.canFlip = canFlip;
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
            this.timer = new Timer(600, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                   if(grilling) {
//                       grilling();
//                       counter++;
//                       sideOne++;
//                       if(canFlip) {
//                           sideTwo++;
//                       }
//                   }
//                    if(GrillStation.meatSent){
//                        timer.stop();
//                    }
//                   if (counter == 20) {
//                        image = getImage("/meat/rawmeat1.png");
//                    } else if(counter == 50){
//                       image = getImage("/meat/meat.png");
//                    }else if (counter == 70) {
//                        image = getImage("/meat/overcookedmeat.png");
//                    } else if (counter == 90) {
//                        image = getImage("/meat/burntmeat.png");
//                    }else if (counter==100){
//                       timer.stop();
//                   }
                    if(GrillStation.meatSent){
                        timer.stop();
                    }
                    if(grilling) {
                        grilling();
                        if (isGrillingRightSide) {
                            sideOne = sideOne < 100 ? sideOne + 1 : 100;
                            sideTwo = sideTwo > 0 ? sideTwo - 1 : 0;
                        } else {
                            sideOne = sideOne > 0 ? sideOne - 1 : 0;
                            sideTwo = sideTwo < 100 ? sideTwo + 1 : 100;
                        }
                        counter++;
                    }
                    if (counter == 50) {
                        image = getImage("/meat/rawmeat1.png");
                    } else if(counter == 160){
                       image = getImage("/meat/meat.png");
                    }else if (counter == 180) {
                        image = getImage("/meat/overcookedmeat.png");
                    } else if (counter == 200) {
                        image = getImage("/meat/burntmeat.png");
                    }
                }
            });
    }



    public void getFlipped() {
       canFlip = true;
       isGrillingRightSide = !isGrillingRightSide;
    }

}

