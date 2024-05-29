package Products;

public class Cheese extends Product {
    public Cheese(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/products/cheese.png");
    }

    public Cheese(){
        setImage("/products/cheese.png");
    }
}
