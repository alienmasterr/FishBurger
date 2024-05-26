package Products;

import Elements.Node;

public class Spinach extends Product {
    public Spinach(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/spinach.png");
    }

    public Spinach(){
        setImage("/spinach.png");
    }
}
