package BuildStation.BuildElements.Sauces;

import BuildStation.BuildElements.Sauce;
import BuildStation.BuildElements.SauceBottle;
import Products.Product;

public class CatsupBottle extends SauceBottle {
    public CatsupBottle(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/sauses/catsup.png");
    }

    public CatsupBottle(){
        setImage("/sauses/catsup.png");
    }

    public Product createSauce(){
        Sauce sauce = new Sauce(this.getX()+this.getWidth()/2, this.getY()+this.getWidth(), 150, 100);
        sauce.getImage("/sauses/drip/catsup.png");
        sauce.setSplashSrc("/sauses/splashes/catsup.png");
        return sauce;
    }
}
