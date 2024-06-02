package RatingStation.RatingElements;

import Elements.Node;

public class RatingTable extends Node {
    public RatingTable(int x, int y, int width, int height){
        super(x, y, width, height);
        image = getImage("/ratingstation/ratingtable.png");
    }
}
