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

    public void setWidth(int width){
        this.width = width;
    }

    public void setHeight(int height){
        this.height = height;
    }
}
