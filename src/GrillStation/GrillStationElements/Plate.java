package GrillStation.GrillStationElements;

import Elements.Node;

public class Plate extends Node {

    public Plate(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/grillstation/plate.png");
    }
}
