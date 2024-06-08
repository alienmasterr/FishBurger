package Store.StoreElements;

import Elements.Node;

public class StoreBackground extends Node {
    public StoreBackground(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/store/storebackground.png");
    }
}
