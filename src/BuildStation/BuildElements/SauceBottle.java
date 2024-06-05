package BuildStation.BuildElements;

import Products.Product;

public class SauceBottle extends Product {
    private String sauceDripSrc = "/sauses/drip/bbq.png";
    private String sauceSplashSrc = "/sauses/splashes/bbq.png";
    public SauceBottle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public SauceBottle(){
    }

    public String getSauceDripSrc() {
        return sauceDripSrc;
    }

    public String getSauceSplashSrc() {
        return sauceSplashSrc;
    }

    public void setSauceDripSrc(String sauceDripSrc) {
        this.sauceDripSrc = sauceDripSrc;
    }

    public void setSauceSplashSrc(String sauceSplashSrc) {
        this.sauceSplashSrc = sauceSplashSrc;
    }

    public Product createSauce(){
        Sauce sauce = new Sauce(this.getX()+this.getWidth()/2, this.getY()+this.getWidth(), 150, 100);
        sauce.getImage("/sauses/drip/bbq.png");
        sauce.setSplashSrc("/sauses/splashes/bbq.png");
        return sauce;
    }
}
