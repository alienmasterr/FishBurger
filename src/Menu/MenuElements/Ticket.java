package Menu.MenuElements;

import Elements.Node;
import Products.*;

import java.util.ArrayList;

public class Ticket extends Node {
    private ArrayList<Product> receipt = new ArrayList<>();

    public Ticket(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/orderstation/ticket.png");
    }

    public void fillTicket(int maxIndex) {
        if (receipt.isEmpty())
            receipt.add(new LowerBun());
        else if (receipt.size() == maxIndex - 1)
            receipt.add(new UpperBun());
        else
            receipt.add(getRandomProduct());
        setReceiptPositions();
    }

    private void setReceiptPositions() {
        for (int i = 0; i < receipt.size(); i++) {
            receipt.get(i).setX(835);
            receipt.get(i).setY(407 - (i * 37));
            receipt.get(i).setWidth(40);
            receipt.get(i).setHeight(25);
        }
    }

    public void updateReceiptPosition() {
        for (int i = receipt.size()-1; i >-1; i--) {
            receipt.get(i).setX(this.getX() + 95);
            receipt.get(i).setY(this.getY() + 383 - (i * 37));
        }
    }

    public ArrayList<Product> getReceipt() {
        return receipt;
    }

    public boolean isFilled() {
        return !receipt.isEmpty();
    }

    private Product getRandomProduct() {
        int generatedNum = getRandomNumber(0, 6);
        return switch (generatedNum) {
            case 0 -> new Cheese();
            case 1 -> new Cucumber();
            case 2 -> new Onion();
            case 3 -> new Spinach();
            case 4 -> new Tomato();
            default -> new Meat();
        };
    }

    private int getRandomNumber(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
