package Menu.MenuElements;

import Elements.Node;
import Products.Product;

import java.awt.*;
import java.util.ArrayList;

public class TicketPin extends Node {
    private Ticket ticket;
    public TicketPin(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/orderstation/ticketpin.png");
    }

    public Ticket getTicket() {
        return ticket;
    }

    public boolean hasTicket(){
        return ticket != null;
    }

    public void setTicket(Ticket ticket){
        this.ticket = ticket;
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
        if(ticket == null)
            return;
        ticket.draw(g2d);
        if(ticket.isFilled()){
            ArrayList<Product> temp = ticket.getReceipt();
            for(int i = 0; i < temp.size(); i++){
//                temp.get(i).setX(835);
//                temp.get(i).setY(407-(i*37));
//                temp.get(i).setWidth(40);
//                temp.get(i).setHeight(25);
                temp.get(i).draw(g2d);
            }
        }
    }
}
