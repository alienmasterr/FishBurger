package Products;

public class Cucumber extends Product {
    public Cucumber(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/products/cucumber.png");
    }

    public Cucumber(){
        setImage("/products/cucumber.png");
    }
}
