package GrillStation.GrillStationElements;

import Elements.Node;

public class Sink extends Node {
    public Sink(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/grillstation/sink.png");
    }
}
