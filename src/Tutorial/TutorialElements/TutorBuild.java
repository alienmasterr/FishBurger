package Tutorial.TutorialElements;

import Elements.Node;

public class TutorBuild extends Node {
    public TutorBuild(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/tutorial/build.PNG");
    }
}
