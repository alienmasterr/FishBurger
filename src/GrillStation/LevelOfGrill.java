package GrillStation;

import Elements.Node;

public class LevelOfGrill extends Node {
    public LevelOfGrill(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/sauses/mayo.png");
    }
}
class RareLevelOfGrill extends LevelOfGrill {
    public RareLevelOfGrill(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/sauses/mayo.png");
    }
}
class MediumLevelOfGrill extends LevelOfGrill {
    public MediumLevelOfGrill(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/sauses/muscard.png");
    }
}
class WellDoneLevelOfGrill extends LevelOfGrill {
    public WellDoneLevelOfGrill(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/sauses/catsup.png");
    }
}
class BurnedLevelOfGrill extends LevelOfGrill {
    public BurnedLevelOfGrill(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/sauses/bbq.png");
    }
}
