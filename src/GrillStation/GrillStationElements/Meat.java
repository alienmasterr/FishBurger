package GrillStation.GrillStationElements;

import Elements.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.TimerTask;

public class Meat extends Node {



//    private double timeTillReady;
//    //номер замовлення до якого належить котлета
//    private double orderNumber;
//    private int xCoordinate;
//    private int yCoordinate;


    public Meat(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/meat.png");
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private int yVelocity = -4;

    public void beReady(){
    }

    public void grilling() {
        y += yVelocity;
        yVelocity *= -1;
    }


//    public void reduceTime() {
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            int secondsPassed = 0;
//
//            @Override
//            public void run() {
//                secondsPassed++;
//                //System.out.println("Seconds passed: " + secondsPassed);
//                if (secondsPassed >= 60) {
//                    timer.cancel();
//                }
//            }
//        }, 0, 1000);
//
//    }


//    public double getOrderNumber() {
//        return orderNumber;
//    }
//
//    public void setOrderNumber(double orderNumber) {
//        this.orderNumber = orderNumber;
//    }
//
//    public double getTimeTillReady() {
//        return timeTillReady;
//    }
//
//    public void setTimeTillReady(double timeTillReady) {
//        this.timeTillReady = timeTillReady;
//    }
//
//    public int getxCoordinate() {
//        return xCoordinate;
//    }
//
//    public void setxCoordinate(int xCoordinate) {
//        this.xCoordinate = xCoordinate;
//    }
//
//    public int getyCoordinate() {
//        return yCoordinate;
//    }
//
//    public void setyCoordinate(int yCoordinate) {
//        this.yCoordinate = yCoordinate;
//    }
}

