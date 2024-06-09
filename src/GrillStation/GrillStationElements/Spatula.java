package GrillStation.GrillStationElements;

import Elements.Node;
import Store.Store;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Spatula extends Node {

//    private Timer timer;
//    private boolean goingUp = true;
//    private int delta = 10;
//    private int originalY;

    public Spatula(int x, int y, int width, int height) {
        super(x, y, width, height);


        image = getImage("/grillstation/splatula.png");


//        originalY = y;
//
//        timer = new Timer(30, new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                animateBounce();
//            }
//        });
    }

    public void chooseImage(){
        if(Store.goldenSpatulaBought){
            image = getImage("/store/goldenspatula.png");
        }
    }


//    public void startBouncing() {
//        timer.start();
//    }
//
//    public void stopBouncing() {
//        timer.stop();
//    }
//
//    private void animateBounce() {
//        if (goingUp) {
//            y -= 2;
//            if (y <= originalY - delta) {
//                goingUp = false;
//            }
//        } else {
//            y += 2;
//            if (y >= originalY) {
//                goingUp = true;
//                stopBouncing();
//            }
//        }
//    }
}

