package Store.StoreElements;

import Level.Level;

import static Store.Store.paintingBought;

public class Painting extends Accessories {
    public Painting(int x, int y, int width, int height, int price) {
        super(x, y, width, height, price);
        if(Level.levelState <3){
            image = getImage("/store/lockedproduct.png");
        }
        if(paintingBought){
            image = getImage("");
        }else {
            image = getImage("/store/picture.png");
        }
    }
    public Painting(int x, int y, int width, int height) {
        super(x, y, width, height, 100);
        if(Level.levelState <3 && !paintingBought){
            image = getImage("/store/lockedproduct.png");
        }else{
            image = getImage("/store/picture.png");
        }
    }
}
