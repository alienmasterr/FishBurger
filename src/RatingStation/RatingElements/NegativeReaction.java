package RatingStation.RatingElements;

import Elements.Node;

public class NegativeReaction extends Node {
    public NegativeReaction(int x, int y, int width, int height){
        super(x, y, width, height);
        image = getImage("/ratingstation/angry.png");
    }
}
