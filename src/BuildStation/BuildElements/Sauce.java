package BuildStation.BuildElements;

import Products.Product;

public class Sauce extends Product {
    private String splashSrc;
    public Sauce(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/products/drip/bbq.png");
    }

    public String getSplashSrc() {
        return splashSrc;
    }

    public void setSplashSrc(String splashSrc) {
        this.splashSrc = splashSrc;
    }

    public void turnIntoSplash(){
        setImage("/products/splashes/bbq.png");
    }
}
