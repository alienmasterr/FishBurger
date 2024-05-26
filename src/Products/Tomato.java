package Products;

import Elements.Node;

public class Tomato extends Product {
    public Tomato(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/temp.png");
    }

    public Tomato(){
        setImage("/temp.png");
    }
}
