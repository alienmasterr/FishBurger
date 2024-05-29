package Products;

public class Tomato extends Product {
    public Tomato(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/products/tomato.png");
    }

    public Tomato(){
        setImage("/products/tomato.png");
    }
}
