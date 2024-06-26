package Store.StoreElements;

import Level.Level;

import static Store.Store.iconBought;

public class IconK extends Accessories {
    public IconK(int x, int y, int width, int height, int price) {
        super(x, y, width, height, price);
        if(Level.levelState < 3){
            image = getImage("/store/lockedproduct.png");
        }if(iconBought){
            image = getImage("");
        }else {
            image = getImage("/store/icon.png");
        }
    }
    public IconK(int x, int y, int width, int height) {
        super(x, y, width, height, 100);
        if(Level.levelState < 3  && !iconBought){
            image = getImage("/store/lockedproduct.png");
        }else {
            image = getImage("/store/icon.png");
        }
    }
}
