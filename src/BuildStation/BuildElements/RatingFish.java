package BuildStation.BuildElements;

import Elements.Node;

public class RatingFish extends Node {

    private int yVelocity = 1;
    public RatingFish(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/ratingstation/rating_fish.png");
    }

    public void waitForRating(){
        if(y > 360 || y < 325)
            yVelocity*=-1;
        y+=yVelocity;
    }
}
