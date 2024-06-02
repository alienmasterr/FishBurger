package RatingStation.RatingElements;

import Elements.Node;

public class RatingBalloon extends Node {
    public RatingBalloon(int x, int y, int width, int height){
        super(x, y, width, height);
        image = getImage("/ratingstation/ratingballon.png");
    }
}
