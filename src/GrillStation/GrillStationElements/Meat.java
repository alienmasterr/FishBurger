package GrillStation.GrillStationElements;

import Elements.Node;
import Menu.GameMenu;

import java.awt.image.BufferedImage;

public class Meat extends Node {
    private String path;
    public Meat(int x, int y, int width, int height) {
        super(x, y, width, height);
        String path = "/meat/meat.png";
        image = getImage(path);
    }

    public Meat(int x, int y, int width, int height, String path) {
        super(x, y, width, height);
        image = getImage(path);
    }

     public String getPath(){
        return path;
     }
     public void setPath(String path){
        this.path = path;
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
}

