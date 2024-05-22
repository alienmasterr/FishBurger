package GrillStation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Menu.Game;

public class GrillStation {

    public GrillStation(){

    }
//
//    //свої дані
//
    //метод що відмальовує панель
    public void draw(Graphics2D g2d) {


        g2d.setColor(Color.lightGray);
        g2d.fillRect(0, Game.HEIGHT / 5, Game.WIDTH, Game.HEIGHT - Game.HEIGHT / 5);

        g2d.setColor(Color.decode("#f2ecd0"));
        g2d.fillRect(Game.WIDTH / 2 - Game.WIDTH / 4, Game.HEIGHT / 2 - Game.HEIGHT / 4, Game.WIDTH / 2, Game.HEIGHT / 2);


        rawMeat(g2d);
        drawTrash(g2d);

        Cutlet cutlet = new Cutlet(100, 100, 0, 0);
        cutlet.paintComponent(g2d);


    }

    public void drawTrash(Graphics2D g2d) {

    }

    //працює як кнопка. натискаєш сюди і на пательні з'являється котлета
    public void rawMeat(Graphics2D g2d) {
        g2d.fillRect(Game.WIDTH / 10, Game.HEIGHT - Game.HEIGHT / 3, 100, 50);
    }



}
