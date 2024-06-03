package RatingStation.RatingElements;

import Elements.Node;

public class BackJar extends Node {
    public BackJar(int x, int y, int width, int height){
        super(x, y, width, height);
        image = getImage("/ratingstation/backjar.png");
    }
}
