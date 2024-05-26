package Products;

import Elements.Node;

public class Cucumber extends Product {
    public Cucumber(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/cucumber.png");
    }

    public Cucumber(){
        setImage("/cucumber.png");
    }
}
