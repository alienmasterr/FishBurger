package Store.StoreElements;

import Level.Level;

public class Music extends Accessories {
    public Music(int x, int y, int width, int height, int price) {
        super(x, y, width, height, price);
        if(Level.levelState <2){
            image = getImage("/store/lockedproduct.png");
        }else {
            image = getImage("/store/icon.png");
        }
    }
}
