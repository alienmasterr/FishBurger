package RatingStation;

import BuildStation.BuildElements.*;
import Menu.*;
import OrderStation.OrderElements.Customer;
import Products.Product;
import RatingStation.RatingElements.*;
import java.awt.*;
import java.util.ArrayList;

public class RatingStation {
    private GameMenu.GamePanel parent;
    private Customer customer;
    private RatingFish fish = new RatingFish(0, 340, (int) (250*1.1), (int) (383*1.1));
    private TicketHolder ticketHolder;
    private ArrayList<Product> receipt;
    private ArrayList<Product> burgerResult;
    private RatingBackground background = new RatingBackground(0, 0, Game.WIDTH, 500);
    private RatingTable ratingTable = new RatingTable(-200, 500, Game.WIDTH+500, 270);
    public RatingStation(GameMenu.GamePanel parent) {
        this.parent = parent;
    }

    public void draw(Graphics2D g2d){
        drawBase(g2d);
        for(Product pr: burgerResult)
            pr.draw(g2d);
        update();
    }

    private void drawBase(Graphics2D g2d){
        background.draw(g2d);
        customer.draw(g2d);
        ratingTable.draw(g2d);
        ticketHolder.draw(g2d);
        fish.draw(g2d);
    }

    private void update(){
        fish.waitForRating();
        customer.waitForOrder();
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setBurgerResult(ArrayList<Product> burgerResult) {
        this.burgerResult = burgerResult;
        relocateBurgerElements();
    }

    private void relocateBurgerElements(){
        for(Product pr: burgerResult){
            pr.setY(pr.getY()-35);
            pr.setX(pr.getX()+40);
        }
    }

    public void setReceipt(ArrayList<Product> receipt) {
        this.receipt = receipt;
        parent.pin.getTicket().setY( parent.pin.getTicket().getY()-45);
        parent.pin.getTicket().updateReceiptPosition();
    }

    public void setTicketHolder(TicketHolder ticketHolder) {
        this.ticketHolder = ticketHolder;
        ticketHolder.setY(ticketHolder.getY()-45);
    }
}
