package Products;

public class UpperBun extends Product {
    public UpperBun(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/products/upperbun.png");
    }

    public UpperBun(){
        setImage("/products/upperbun.png");
    }
}
