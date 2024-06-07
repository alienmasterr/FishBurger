package Elements;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

//коли створюєш об'єкт, то екстендаєш цей клас
//для задання картинки в своєму класі пишеш
// image = getImage(/(назва картинки + розширення));
// твій об'єкт унаслідує метод draw, який буде відмальовувати об'єкт
//за нинішніми координатами
//цей клас не можна змінювати. можна лише додавати
//методи для отримання координат або інших параметрів

public class Node {
    protected int x, y;
    protected int width, height;
    protected BufferedImage image;
    protected String src;

    public Node(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Node() {
    }

    public BufferedImage getImage(String fileName) {
        this.src = fileName;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(fileName));
        } catch (IOException ignore) {
        }
        return image;
    }

    public BufferedImage getSprite() {
        return image;
    }

    public void setImage(String fileName) {
        this.src = fileName;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, x, y, width, height, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getSrc() {
        return src;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
