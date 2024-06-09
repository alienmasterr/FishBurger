package BuildStation.BuildElements;

import Level.Level;
import Products.Product;

public class Sauce extends Product {
    private String splashSrc;
    public Sauce(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/sauses/splashes/" + getRandomImage());
    }

    public Sauce(){
            setImage("/sauses/splashes/" + getRandomImage());
    }

    private String getRandomImage(){
        return switch (getRandomNumber(1, Level.getAmountOfSauces())) {
            case 1 -> "bbq.png";
            case 2 -> "catsup.png";
            case 3 -> "mayo.png";
            default -> "musсard.png";
        };
    }

    private int getRandomNumber(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
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
