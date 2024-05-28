package GrillStation.GrillStationElements;

import Elements.Node;

public class BurningSign extends Node {

    public BurningSign(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/temp.png");
    }
}
