package Menu.MenuElements;

import BuildStation.BuildElements.Sauce;
import Level.Level;
import Products.*;

//клас-ускладнення що позначає загаданий елемент від кастомера
public class Unknown extends Product {
    private Product secretProduct;
    public Unknown(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/higherlevels/something.png");
        fillSecretProduct();
    }

    public Unknown(){
        if(Level.levelState > 1) {
            setImage("/higherlevels/something.png");
            fillSecretProduct();
        }
        else
            setImage("/products/meat.png");
    }

    public Product getSecretProduct() {
        return secretProduct;
    }

    public void setSecretProduct(Product secretProduct) {
        this.secretProduct = secretProduct;
    }

    private void fillSecretProduct(){
        secretProduct = getRandomProduct();
    }

    private Product getRandomProduct() {
        int generatedNum = getRandomNumber(0, 8);
        return switch (generatedNum) {
            case 0 -> new Cheese();
            case 1 -> new Cucumber();
            case 2 -> new Onion();
            case 3 -> new Spinach();
            case 4 -> new Tomato();
            case 5 -> new Sauce();
            default -> new Meat();
        };
    }

    private int getRandomNumber(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
