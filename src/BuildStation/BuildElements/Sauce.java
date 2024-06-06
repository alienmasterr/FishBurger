package BuildStation.BuildElements;

import Products.Product;

public class Sauce extends Product {
    private String splashSrc;
    public Sauce(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/sauses/drip/bbq.png");
    }

    public String getSplashSrc() {
        return splashSrc;
    }

    public void setSplashSrc(String splashSrc) {
        this.splashSrc = splashSrc;
        this.setX(getX()-getWidth()/4);
    }

    public void turnIntoSplash(){
        setImage(splashSrc);
    }
}
