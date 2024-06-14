package GrillStation.GrillStationElements;

import Elements.Node;

public class CookFish extends Node {
    public CookFish(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/grillstation/cookfish.png");
    }

    int yVelocity = -1;

    public void cookMeat() {
        if (y < 470 || y > 485)
            yVelocity *= -1;
        y += yVelocity;
    }
}
