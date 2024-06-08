package Store.StoreElements;

import Elements.Node;

public class ProductBack extends Node {
    public ProductBack(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/store/productback.png");
    }

}
