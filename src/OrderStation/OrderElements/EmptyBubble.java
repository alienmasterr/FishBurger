package OrderStation.OrderElements;

import Elements.Node;
import Products.Product;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EmptyBubble extends Node {
    private Product shownProduct = new Product(297, 135, 100, 100);
    public EmptyBubble(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/emptybubble.png");
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
        if(shownProduct != null) {
            shownProduct.draw(g2d);
        }
    }

    public Product getShownProduct() {
        return shownProduct;
    }

    public void setProductImage(BufferedImage image) {
        shownProduct.setSprite(image);
    }
}
