package Products;

import Elements.Node;

public class Onion extends Product {
    public Onion(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/temp.png");
    }

    public Onion(){setImage("/temp.png");}
}
