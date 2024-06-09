package Store.StoreElements;

public class Chair extends Accessories {

    public Chair(int x, int y, int width, int height, int price) {
        super(x, y, width, height, price);
        image = getImage("/store/chair.png");
    }

    public Chair(int x, int y, int width, int height) {
        super(x, y, width, height, 10);
        image = getImage("/store/chair.png");
    }


}
