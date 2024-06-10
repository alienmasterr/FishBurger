package GrillStation.GrillStationElements;

import Elements.Node;
import Store.Store;

public class Sink extends Node {
    public Sink(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/grillstation/sink.png");
    }

    public void chooseImage(){
        if(Store.goldenSinkBought){
            image = getImage("/store/goldensink.png");
        }
    }
}
