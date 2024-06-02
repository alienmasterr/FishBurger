package RatingStation.RatingElements;

import Elements.Node;

public class RatingBackground extends Node {
    public RatingBackground(int x, int y, int width, int height){
        super(x, y, width, height);
        image = getImage("/orderstation/orderbackground.png");
    }
}
