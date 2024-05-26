package Products;

import Elements.Node;

public class Cucumber extends Product {
    public Cucumber(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/temp.png");
    }

    public Cucumber(){
        setImage("/temp.png");
    }
}
