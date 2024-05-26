package Products;

import Elements.Node;

public class Product extends Node {
    public Product(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    public Product(){};
    public void setImage(String src){
        image = getImage(src);
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
}
