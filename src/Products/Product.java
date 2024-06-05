package Products;

import Elements.Node;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Product extends Node {
    private int initialX, initialY;
    private boolean isUsed = false;

    public Product(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Product() {
    }

    public int getInitialX() {
        return initialX;
    }

    public int getInitialY() {
        return initialY;
    }

    public void setInitialX(int initialX) {
        this.initialX = initialX;
    }

    public void setInitialY(int initialY) {
        this.initialY = initialY;
    }

    public BufferedImage getSprite() {
        return image;
    }

    public void setSprite(BufferedImage image) {
        this.image = image;
    }

    public void setImage(String src) {
        image = getImage(src);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
