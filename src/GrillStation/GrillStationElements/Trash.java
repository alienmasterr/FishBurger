package GrillStation.GrillStationElements;

import Elements.Node;

public class Trash extends Node {

    public Trash(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/temp.png");
    }
}
