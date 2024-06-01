package BuildStation.BuildElements;

import Elements.Node;

public class TicketHolder extends Node {
    public TicketHolder(int x, int y, int width, int height){
        super(x, y, width, height);
        image = getImage("/buildmenu/ticketholder.png");
    }
}
