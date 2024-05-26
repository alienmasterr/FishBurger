package Products;

import Elements.Node;

public class UpperBun extends Product {
    public UpperBun(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/upperbun.png");
    }

    public UpperBun(){
        setImage("/upperbun.png");
    }
}
