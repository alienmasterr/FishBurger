package BuildStation.BuildElements;

import Products.Product;

public class SauceBottle extends Product {

    public SauceBottle(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/sauses/catsup.png");
    }

    public SauceBottle(){
        setImage("/sauses/catsup.png");
    }

    public Product createSauce(){
        return new Sauce(this.getX()+this.getWidth()/2, this.getY()+this.getWidth(), 150, 100);
    }
}
