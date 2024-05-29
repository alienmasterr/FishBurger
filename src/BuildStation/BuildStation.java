package BuildStation;

import BuildStation.BuildElements.BuildBackground;
import Elements.Node;
import Menu.Game;
import Products.Product;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BuildStation {
    //свої дані
    private BuildBackground background = new BuildBackground(0,0, Game.WIDTH, Game.HEIGHT-100);
    public void draw(Graphics2D g2d){
        drawBase(g2d);
    }

    private void drawBase(Graphics2D g2d){
        background.draw(g2d);
        for(int i = 0; i < 7; i++){
            ProductTray tray  = new ProductTray(0, 580-i*80, 215, 160);
            tray.draw(g2d);
        }
    }

    private class ProductTray extends Node {
        private Product product = new Product();
        public ProductTray(int x, int y, int width, int height){
            super(x, y, width, height);
            image = getImage("/buildtray.png");
        }

        public void setProductSprite(BufferedImage image){
            product.setSprite(image);
        }
    }
}
