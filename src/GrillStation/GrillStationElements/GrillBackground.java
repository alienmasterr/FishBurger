package GrillStation.GrillStationElements;

import Elements.Node;

public class GrillBackground extends Node {

    public GrillBackground(int x, int y, int width, int height){
        super(x, y, width, height);
        image = getImage("/grillstation/grillbackground.png");
    }
}
