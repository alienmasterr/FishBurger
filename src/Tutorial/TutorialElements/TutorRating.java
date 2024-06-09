package Tutorial.TutorialElements;

import Elements.Node;

public class TutorRating extends Node {
    public TutorRating(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/tutorial/rating.PNG");
    }
}
