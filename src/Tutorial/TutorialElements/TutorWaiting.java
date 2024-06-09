package Tutorial.TutorialElements;

import Elements.Node;

public class TutorWaiting extends Node {
    public TutorWaiting(int x, int y, int width, int height) {

        super(x, y, width, height);
        image = getImage("/tutorial/waiting.PNG");
    }
}
