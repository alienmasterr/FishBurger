package Products;

public class Onion extends Product {
    public Onion(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/products/onion.png");
    }

    public Onion(){setImage("/products/onion.png");}
}
