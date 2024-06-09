package Tutorial.TutorialElements;

import Elements.Node;

public class TutorLevel extends Node {
    public TutorLevel(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/tutorial/level.PNG");
    }
}
