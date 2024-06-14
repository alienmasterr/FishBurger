package GrillStation.GrillStationElements;

import Elements.Node;
import GrillStation.GrillStation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mouth extends Node {
    public int speed = 20;
    public Timer timer;
    public boolean falling = false;
    public boolean goingUpHook = false;

    public Mouth(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/grillstation/som.png");

        timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (falling) {
                    updateDown();
                }
                if (goingUpHook) {
                    updateUp();
                }
            }
        });
    }

    public void goDown() {
        falling = true;
        timer.start();
    }

    public void updateDown() {
        y += speed;
        if(y>= 10){
            falling = false;
            //System.out.println("астанавісь");
            GrillStation.meatStolen = true;
            goingUpHook = true;
        }
    }

    public void updateUp(){
        y-=speed;
        if(y<= -height){
            goingUpHook = false;
        }

    }
}
