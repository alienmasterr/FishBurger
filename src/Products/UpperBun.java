package Products;

import Elements.Node;

public class UpperBun extends Product {
    public UpperBun(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/temp.png");
    }

    public UpperBun(){
        setImage("/temp.png");
    }
}
