package Products;

public class LowerBun extends Product {
    public LowerBun(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/products/lowerbun.png");
    }

    public LowerBun(){
        setImage("/products/lowerbun.png");
    }
}
