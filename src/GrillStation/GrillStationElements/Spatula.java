package GrillStation.GrillStationElements;

import Elements.Node;
import Store.Store;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import static Store.Store.goldenSpatulaBought;

public class Spatula extends Node {

    public Spatula(int x, int y, int width, int height) {
        super(x, y, width, height);

            image = getImage("/grillstation/splatula.png");


    }

    public void chooseImage(){
        if(goldenSpatulaBought){
            image = getImage("/store/goldenspatula.png");
        }
    }

}

