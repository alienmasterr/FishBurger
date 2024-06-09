package Tutorial.TutorialElements;

import Elements.Node;

public class TutorMeat extends Node {
    public TutorMeat(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/tutorial/meat.PNG");
    }
}
