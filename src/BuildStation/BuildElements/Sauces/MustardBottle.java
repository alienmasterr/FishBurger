package BuildStation.BuildElements.Sauces;

import BuildStation.BuildElements.Sauce;
import BuildStation.BuildElements.SauceBottle;
import Products.Product;

public class MustardBottle extends SauceBottle {
    public MustardBottle(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/sauses/muscard.png");
    }

    public MustardBottle(){
        setImage("/sauses/muscard.png");
    }

    public Product createSauce(){
        Sauce sauce = new Sauce(this.getX()+this.getWidth()/2, this.getY()+this.getWidth(), 150, 100);
        sauce.getImage("/sauses/drip/muscard.png");
        sauce.setSplashSrc("/sauses/splashes/muscard.png");
        return sauce;
    }
}
