package BuildStation;

import BuildStation.BuildElements.BuildBackground;
import Elements.Node;
import Menu.Game;
import Products.Product;
import java.awt.*;
import java.io.File;

public class BuildStation {
    private ProductTray[] productTrays = new ProductTray[7];
    private BuildBackground background = new BuildBackground(0,0, Game.WIDTH, Game.HEIGHT-100);
    public BuildStation(){
        fillTrays();
    }
    private void fillTrays(){
        File[] files = (new File("res/products")).listFiles();
        if(files == null)
            return;
        for(int i = 0; i < productTrays.length; i++){
            productTrays[i] = new ProductTray(0, 580-i*80, 215, 160);
            productTrays[i].setProductSprite("/products/" + files[i].getName());
        }
    }
    public void draw(Graphics2D g2d){
        drawBase(g2d);
    }

    private void drawBase(Graphics2D g2d){
        background.draw(g2d);
        for(int i = 0; i < 7; i++){
            productTrays[i].draw(g2d);
        }
    }

    private class ProductTray extends Node {
        private Product product = new Product();
        public ProductTray(int x, int y, int width, int height){
            super(x, y, width, height);
            image = getImage("/buildmenu/buildtray.png");
        }

        public void setProductSprite(String src){
            product.getImage(src);
        }

        @Override
        public void draw(Graphics2D g2d) {
            super.draw(g2d);
            product.setX(x+20);
            product.setY(y+12);
            product.setHeight(100);
            product.setWidth(150);
            product.draw(g2d);
        }
    }
}
