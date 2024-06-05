package BuildStation.BuildElements.Sauces;

import BuildStation.BuildElements.Sauce;
import BuildStation.BuildElements.SauceBottle;
import Products.Product;

public class BbqBottle extends SauceBottle {
    public BbqBottle(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/sauses/bbq.png");
    }

    public BbqBottle(){
        setImage("/sauses/bbq.png");
    }

    public Product createSauce(){
        Sauce sauce = new Sauce(this.getX()+this.getWidth()/2, this.getY()+this.getWidth(), 150, 100);
        sauce.getImage("/sauses/drip/bbq.png");
        sauce.setSplashSrc("/sauses/splashes/bbq.png");
        return sauce;
    }
}
