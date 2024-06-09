package Tutorial.TutorialElements;

import Elements.Node;

public class TutorFirstHello extends Node {
    public TutorFirstHello(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/tutorial/firstHello.PNG");
    }
}
