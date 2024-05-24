package Menu.MenuElements;

import Elements.Node;

public class Ticket extends Node {
    public Ticket(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/ticket.png");
    }
}
