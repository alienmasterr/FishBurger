package Products;

public class Spinach extends Product {
    public Spinach(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/products/spinach.png");
    }

    public Spinach(){
        setImage("/products/spinach.png");
    }
}
