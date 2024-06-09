package Tutorial.TutorialElements;

import Elements.Node;

public class TutorButtons extends Node {
    public TutorButtons(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/tutorial/buttons.PNG");
    }
}
