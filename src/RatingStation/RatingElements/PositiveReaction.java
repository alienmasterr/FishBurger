package RatingStation.RatingElements;

import Elements.Node;

public class PositiveReaction extends Node {
    public PositiveReaction(int x, int y, int width, int height){
        super(x, y, width, height);
        image = getImage("/ratingstation/happy.png");
    }
}
