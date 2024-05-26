package Menu.MenuElements;

import Elements.Node;
import Products.*;

import java.util.ArrayList;

public class Ticket extends Node {
    private ArrayList<Product> receipt = new ArrayList<>();
    public Ticket(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/ticket.png");
    }

    public void fillTicket(){
        receipt.clear();
        receipt.add(new LowerBun(2, 2, 2, 2));
        for(int i = 0; i < 5; i++){
            receipt.add(getRandomProduct());
        }
        receipt.add(new UpperBun(2, 2, 2, 2));
    }

    public ArrayList<Product> getReceipt() {
        return receipt;
    }

    public boolean isFilled(){
        return !receipt.isEmpty();
    }

    private Product getRandomProduct(){
        int generatedNum = getRandomNumber(1, 7);
        return switch (generatedNum) {
            case 0 -> new Cheese();
            case 1 -> new Cucumber();
            case 2 -> new Onion();
            case 3 -> new Spinach();
            case 4 -> new Tomato();
            default -> new Meat();
        };
    }

    private int getRandomNumber(int min, int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
