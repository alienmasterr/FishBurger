package BuildStation;

import BuildStation.BuildElements.BuildBackground;
import Elements.Node;
import Menu.Game;
import Products.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class BuildStation {
    private Product activeProduct;
    private Product lastActiveProduct;
    private ArrayList<Product> burgerProducts = new ArrayList<>();
    private ProductTray[] productTrays = new ProductTray[7];
    private BuildBackground background = new BuildBackground(0, 0, Game.WIDTH, Game.HEIGHT - 100);
    private int diffX = -1;
    private int diffY = -1;

    public BuildStation() {
        fillTrays();
    }

    private void fillTrays() {
        File[] files = (new File("res/products")).listFiles();
        if (files == null)
            return;
        for (int i = 0; i < productTrays.length; i++) {
            productTrays[i] = new ProductTray(0, 580 - i * 80, 215, 160);
            productTrays[i].setProductSprite("/products/" + files[i].getName());
        }
    }

    public void draw(Graphics2D g2d) {
        drawBase(g2d);
    }

    private void drawBase(Graphics2D g2d) {
        background.draw(g2d);
        for (int i = 0; i < 7; i++)
            productTrays[i].draw(g2d);
        for (Product product : burgerProducts)
            product.draw(g2d);
        if (lastActiveProduct != null)
            lastActiveProduct.draw(g2d);
        update();
    }

    private void update() {
        updateLastActiveProduct();
        updateLastProduct();
        updateActiveProduct();
        updateNonactiveProducts();

    }

    private void updateLastProduct() {
        if (burgerProducts.isEmpty())
            return;
        Product lastProduct = burgerProducts.getLast();
        if (Game.mouse.pressed && Game.mouse.x >= lastProduct.getX() && Game.mouse.x <= lastProduct.getX() + 150 && Game.mouse.y >= lastProduct.getY() && Game.mouse.y <= lastProduct.getY() + 100) {
            activeProduct = lastProduct;
        }
    }

    private void updateNonactiveProducts() {
        for (Product product : burgerProducts)
            if (isFalling(product) && !isColliding(product))
                product.setY(product.getY() + 15);
    }

    private boolean isColliding(Product product) {
        for (Product fallenProduct : burgerProducts) {
            if (fallenProduct == product)
                continue;
            if (fallenProduct.getY() - 30 <= product.getY() && fallenProduct.getX() - 150 <= product.getX() && fallenProduct.getX() + 150 >= product.getX())
                return true;
        }
        return false;
    }

    private boolean isFalling(Product product) {
        return product.getY() <= 560;
    }

    private void updateActiveProduct() {
        updateActiveProductState();
        updateActiveProductMovement();
        updateActiveProductCollision();
    }

    private void updateActiveProductState() {
        if (Game.mouse.pressed || activeProduct == null)
            return;
        checkFallingLocation();
        activeProduct = null;
    }

    private void updateLastActiveProduct() {
        if (lastActiveProduct == null)
            return;
        returnToTray();
    }

    private void returnToTray() {
        Product trayProduct = getTrayProduct(lastActiveProduct);
        if (diffX == -1 && diffY == -1) {
            int distX = trayProduct.getX()-lastActiveProduct.getX();
            int distY = trayProduct.getY()-lastActiveProduct.getY();
            double gDist = Math.sqrt(distX*distX + distY*distY);
            int steps = (int)((gDist-1)/30);
            diffY = distY/steps;
            diffX = distX/steps;
        }
        if (trayProduct != null)
            moveToTray(trayProduct, lastActiveProduct);
    }

    private void moveToTray(Product trayProduct, Product product) {
        if (trayProduct.getX() - 30 <= product.getX() && trayProduct.getX() + 30 >= product.getX() && trayProduct.getY() - 30 < product.getY() && trayProduct.getY() + 30 > product.getY()) {
            diffX = -1;
            diffY = -1;
            lastActiveProduct = null;
            return;
        }
        lastActiveProduct.setX(lastActiveProduct.getX()+diffX);
        lastActiveProduct.setY(lastActiveProduct.getY()+diffY);
    }

    private void checkFallingLocation() {
        if ((activeProduct.getY() >= 560 || activeProduct.getX() < 200 || activeProduct.getX() > 700)) {
            lastActiveProduct = activeProduct;
            burgerProducts.remove(activeProduct);
        }
    }

    private Product getTrayProduct(Product original) {
        for (ProductTray tray : productTrays) {
            if (tray.product.getSprite() == original.getSprite())
                return tray.product;
        }
        return null;
    }

    private void updateActiveProductMovement() {
        if (activeProduct == null)
            return;
        activeProduct.setX(Game.mouse.x - 75);
        activeProduct.setY(Game.mouse.y - 50);
    }

    private void updateActiveProductCollision() {
        if (activeProduct == null)
            return;
        for (Product fallenPr : burgerProducts) {
            if (fallenPr == activeProduct)
                continue;
            if (fallenPr.getY() - 30 <= activeProduct.getY() && fallenPr.getY() + 100 >= activeProduct.getY() && fallenPr.getX() - 100 < activeProduct.getX() && fallenPr.getX() + 120 > activeProduct.getX())
                activeProduct.setY(fallenPr.getY() - 29);
        }
    }

    private class ProductTray extends Node {
        private Product product = new Product();

        public ProductTray(int x, int y, int width, int height) {
            super(x, y, width, height);
            image = getImage("/buildmenu/buildtray.png");
        }

        public void setProductSprite(String src) {
            product.getImage(src);
            setProductLocation();
        }

        @Override
        public void draw(Graphics2D g2d) {
            super.draw(g2d);
            checkAction();
            product.draw(g2d);
        }

        public void checkAction() {
            if (Game.mouse.pressed && Game.mouse.x >= product.getX() && Game.mouse.x <= product.getX() + 150 && Game.mouse.y >= product.getY() && Game.mouse.y <= product.getY() + 100) {
                if (activeProduct != null)
                    return;
                activeProduct = createProduct(product);
                burgerProducts.add(activeProduct);
            }
        }

        private Product createProduct(Product original) {
            Product copy = new Product(original.getX(), original.getY(), original.getWidth(), original.getHeight());
            copy.setSprite(original.getSprite());
            return copy;
        }

        private void setProductLocation() {
            product.setX(x + 20);
            product.setY(y + 12);
            product.setHeight(100);
            product.setWidth(150);
        }
    }
}
