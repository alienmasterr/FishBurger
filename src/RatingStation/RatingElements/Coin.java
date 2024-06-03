package RatingStation.RatingElements;

import Elements.Node;

public class Coin extends Node {
    public Coin(int x, int y, int width, int height){
        super(x, y, width, height);
        image = getImage("/ratingstation/coin.png");
    }
}
