package Tutorial.TutorialElements;

import Elements.Node;

public class TutorStore extends Node {
    public TutorStore(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/tutorial/store.PNG");
    }
}
