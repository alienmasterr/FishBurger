package BuildStation.BuildElements.Sauces;

import BuildStation.BuildElements.Sauce;
import BuildStation.BuildElements.SauceBottle;
import Products.Product;

public class MayoBottle extends SauceBottle {
    public MayoBottle(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/sauses/mayo.png");
    }

    public MayoBottle(){
        setImage("/sauses/mayo.png");
    }

    public Product createSauce(){
        Sauce sauce = new Sauce(this.getX()+this.getWidth()/2, this.getY()+this.getWidth(), 150, 100);
        sauce.getImage("/sauses/drip/mayo.png");
        sauce.setSplashSrc("/sauses/splashes/mayo.png");
        return sauce;
    }
}
