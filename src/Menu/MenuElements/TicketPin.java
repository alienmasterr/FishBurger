package Menu.MenuElements;

import Elements.Node;
import Products.Product;

import java.awt.*;
import java.util.ArrayList;

public class TicketPin extends Node {
    private Ticket ticket;
    private boolean drawTicket = true;
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
            for (Product product : temp)
                product.draw(g2d);
        }
    }

    public boolean isDrawTicket() {
        return drawTicket;
    }

    public void setDrawTicket(boolean drawTicket) {
        this.drawTicket = drawTicket;
    }
}
