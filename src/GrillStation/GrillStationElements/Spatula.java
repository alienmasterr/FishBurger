package GrillStation.GrillStationElements;

import Elements.Node;

public class Spatula extends Node {
    public Spatula(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/temp.png");
    }
}
