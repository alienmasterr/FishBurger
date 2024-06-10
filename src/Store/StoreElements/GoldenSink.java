package Store.StoreElements;

import Level.Level;

public class GoldenSink extends Accessories {
    public GoldenSink(int x, int y, int width, int height, int price) {
        super(x, y, width, height, price);
        if(Level.levelState <2){
            image = getImage("/store/lockedproduct.png");
        }else {
            image = getImage("/store/goldensink.png");
        }
    }
}
