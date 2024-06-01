package BuildStation.BuildElements;

import Elements.Node;

public class TicketBackground extends Node {

    public TicketBackground(int x, int y, int width, int height){
        super(x, y, width, height);
        image = getImage("/buildmenu/ticketbackground.png");
    }
}
