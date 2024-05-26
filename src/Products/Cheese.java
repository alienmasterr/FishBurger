package Products;

import Elements.Node;

public class Cheese extends Product {
    public Cheese(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/cheese.png");
    }

    public Cheese(){
        setImage("/cheese.png");
    }
}
