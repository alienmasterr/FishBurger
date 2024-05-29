package BuildStation.BuildElements;

import Elements.Node;

public class BuildBackground extends Node {

    public BuildBackground(int x, int y, int width, int height){
        super(x, y, width, height);
        image = getImage("/buildbackground.png");
    }
}
