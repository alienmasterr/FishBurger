package Menu;

import Elements.Node;

import javax.swing.*;
import java.awt.*;

public class TutorialMenu extends JPanel {
    private Game parent;
    public TutorialMenu(Game parent){
        super();
        this.parent = parent;
        setStaticSize();
        setLayout(null); //якщо юзатимеш якийсь лейтаут а не абсолютне позиціювання, то прибери це
    }

    private void setStaticSize(){
        setMaximumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setMinimumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
    }

    //в панелях немає методу драв який я нам зробила
    //тут все відмальовується в пейнткомпонент
    //робити анімації через посекундну зміну (як в грі) тут не вийде
    //оскільки тут немає потоку, бо він знаходиться в грі. (лоігічно :0)
    //ти можеш перемальовувати елементи в яких оновилася позиція через repaint
    //але хз навіщо
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

    }
}
