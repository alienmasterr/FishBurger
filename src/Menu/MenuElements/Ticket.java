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

    public void decreaseSize() {
        if (width > 67)
            width -= 15;
        if (height > 140)
            height -= 20;
    }

    public void increaseSize() {
        if (width < 230)
            width += 15;
        if (height < 420)
            height += 20;
    }

    public void updateReceiptPosition() {
        for (int i = receipt.size() - 1; i > -1; i--) {
            receipt.get(i).setX((int) (this.getX() + getScaledXDiff()));
            receipt.get(i).setY((int) (this.getY() + getScaledBase() - (i * getScaledRecDiff())));
            receipt.get(i).setWidth((int) (getScaledWidth()));
            receipt.get(i).setHeight((int) (getScaledHeight()));
        }
    }

    private double getScaledWidth() {
        return ((double) (this.getWidth() * 40) / 230);
    }

    private double getScaledHeight() {
        return (double) (this.getHeight() * 25) / 420;
    }

    private double getScaledXDiff(){
        return 95*getScaledWidth()/40;
    }

    private double getScaledRecDiff(){
        return 37*getScaledHeight()/25;
    }

    private double getScaledBase(){
        return 383*getScaledHeight()/25;
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
