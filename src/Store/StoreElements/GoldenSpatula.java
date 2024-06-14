package Store.StoreElements;

import Level.Level;

import static Store.Store.goldenSpatulaBought;

public class GoldenSpatula extends Accessories {
    public GoldenSpatula(int x, int y, int width, int height, int price) {
        super(x, y, width, height, price);
        if(goldenSpatulaBought){
            image = getImage("");

        }else{
            image = getImage("/store/goldenspatula.png");
        }

    }
}
