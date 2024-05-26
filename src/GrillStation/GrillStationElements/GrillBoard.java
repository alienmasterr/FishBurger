package GrillStation.GrillStationElements;

import Elements.Node;

import javax.swing.*;
import java.awt.*;

public class GrillBoard extends Node {

    public GrillBoard(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/temp.png");
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
