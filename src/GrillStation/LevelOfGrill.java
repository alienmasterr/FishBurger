package GrillStation;

import Elements.Node;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelOfGrill extends Node {
    private int levelOne;
    private int levelTwo;
    public LevelOfGrill(int x, int y, int width, int height, int levelOne, int levelTwo) {
        super(x, y, width, height);
        this.levelOne = levelOne;
        this.levelTwo = levelTwo;
        image = getImage("/temp.png");

        BufferedImage copyImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = copyImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.PLAIN, 50));
        drawCenteredString(g2d, String.valueOf(levelOne), new Rectangle(10, 10, width / 2, height), g2d.getFont());
        drawCenteredString(g2d, String.valueOf(levelTwo), new Rectangle(90, 25, width / 2, height), g2d.getFont());

        g2d.dispose();
        image = copyImage;
    }

    private void drawCenteredString(Graphics2D g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }
}
//class RareLevelOfGrill extends LevelOfGrill {
//
//    public RareLevelOfGrill(int x, int y, int width, int height, int levelOne, int levelTwo) {
//        super(x, y, width, height, levelOne, levelTwo);
//
//        image = getImage("/sauses/mayo.png");
//    }
//}
//class MediumLevelOfGrill extends LevelOfGrill {
//    public MediumLevelOfGrill(int x, int y, int width, int height, int levelOne, int levelTwo) {
//        super(x, y, width, height, levelOne, levelTwo);
//        image = getImage("/sauses/muscard.png");
//    }
//}
//class WellDoneLevelOfGrill extends LevelOfGrill {
//    public WellDoneLevelOfGrill(int x, int y, int width, int height, int levelOne, int levelTwo) {
//        super(x, y, width, height, levelOne, levelTwo);
//        image = getImage("/sauses/catsup.png");
//    }
//}
//class BurnedLevelOfGrill extends LevelOfGrill {
//    public BurnedLevelOfGrill(int x, int y, int width, int height, int levelOne, int levelTwo) {
//        super(x, y, width, height, levelOne, levelTwo);
//        image = getImage("/sauses/bbq.png");
//    }
//}
