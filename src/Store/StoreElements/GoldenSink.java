package Store.StoreElements;

import Level.Level;

import static Store.Store.goldenSinkBought;

public class GoldenSink extends Accessories {
    public GoldenSink(int x, int y, int width, int height, int price) {
        super(x, y, width, height, price);
        if (Level.levelState < 2) {
            image = getImage("/store/lockedproduct.png");
        }
        if(goldenSinkBought){
            image= getImage("");
        }else {
            image = getImage("/store/goldensink.png");
        }
    }
}
