package Products;

import Elements.Node;

public class Spinach extends Product {
    public Spinach(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/temp.png");
    }

    public Spinach(){
        setImage("/temp.png");
    }
}
