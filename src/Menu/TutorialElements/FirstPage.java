package Menu.TutorialElements;

import Elements.Node;

public class FirstPage extends Node {
    public FirstPage(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/tutorial/tutor.png");
    }
}
