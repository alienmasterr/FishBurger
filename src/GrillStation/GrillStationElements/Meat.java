package GrillStation.GrillStationElements;

import Elements.Node;

public class Meat extends Node {


    public Meat(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/meat/meat.png");
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private int yVelocity = -4;

    public void beReady(){
    }
    public void beShrownAway(){
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

