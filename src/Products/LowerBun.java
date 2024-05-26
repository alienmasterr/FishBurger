package Products;

import Elements.Node;

public class LowerBun extends Product {
    public LowerBun(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/lowerbun.png");
    }

    public LowerBun(){
        setImage("/lowerbun.png");
    }
}
