package Store.StoreElements;

import static Store.Store.chairBought;

public class Chair extends Accessories {

    public Chair(int x, int y, int width, int height, int price) {
        super(x, y, width, height, price);
        if(chairBought){
            image = getImage("");
        }else{
            image = getImage("/store/chair.png");

        }
    }

    public Chair(int x, int y, int width, int height) {
        super(x, y, width, height, 10);
        image = getImage("/store/chair.png");
    }


}
