package BuildStation;

import BuildStation.BuildElements.BuildBackground;
import Elements.Node;
import Menu.Game;
import Products.Product;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class BuildStation {
    private Product activeProduct;
    private ArrayList<Product> burgerProducts = new ArrayList<>();
    private ProductTray[] productTrays = new ProductTray[7];
    private BuildBackground background = new BuildBackground(0, 0, Game.WIDTH, Game.HEIGHT - 100);

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
        update();
    }

    //треба додати перевірку на коліжон при триманні продукта
    private void update() {
        updateActiveProduct();
        updateNonactiveProducts();
        updateLastProduct();
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
            if (fallenProduct.getY() - 30 <= product.getY())
                return true;
        }
        return false;
    }

    private boolean isFalling(Product product) {
        return product.getY() <= 560;
    }

    private void updateActiveProduct() {
        updateActiveProductMovement();
        updateActiveProductState();
    }

    private void updateActiveProductState() {
        if (!Game.mouse.pressed)
            activeProduct = null;
    }

    private void updateActiveProductMovement() {
        if (activeProduct == null)
            return;
        activeProduct.setX(Game.mouse.x - 75);
        activeProduct.setY(Game.mouse.y - 50);
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
