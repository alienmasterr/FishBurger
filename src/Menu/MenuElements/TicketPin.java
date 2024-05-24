package Menu.MenuElements;

import Elements.Node;

import java.awt.*;

public class TicketPin extends Node {
    private Ticket ticket;
    public TicketPin(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/ticketpin.png");
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
        if(ticket != null)
            ticket.draw(g2d);
    }
}
