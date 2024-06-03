package RatingStation.RatingElements;

import Elements.Node;

public class FrontJar extends Node {
    public FrontJar(int x, int y, int width, int height){
        super(x, y, width, height);
        image = getImage("/ratingstation/frontjar.png");
    }
}
