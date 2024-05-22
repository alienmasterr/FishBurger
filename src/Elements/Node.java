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
    public Node(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width =width;
        this.height = height;
    }

    public BufferedImage getImage(String fileName) {
        try{
            image = ImageIO.read(getClass().getResourceAsStream(fileName));
        } catch(IOException ignore){}
        return image;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, x, y, width, height, null);
    }
}
