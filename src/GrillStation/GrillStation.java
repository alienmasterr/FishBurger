package GrillStation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Menu.Game;

public class GrillStation extends JPanel  implements MouseListener{
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
//    public GrillStation() {
//        JFrame frame = new JFrame("Grill Station");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500, 500);
//        frame.setLocationRelativeTo(null);
//        frame.setLayout(null);
//        frame.setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        new GrillStation();
//    }

    public GrillStation(){
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int mouseX = e.getX();
//                int mouseY = e.getY();
//
//                // Check if within the button bounds
//                int buttonX = Game.WIDTH / 10;
//                int buttonY = Game.HEIGHT - Game.HEIGHT / 3;
//                int buttonWidth = 100;
//                int buttonHeight = 50;
//
//                if (mouseX >= buttonX && mouseX <= buttonX + buttonWidth &&
//                        mouseY >= buttonY && mouseY <= buttonY + buttonHeight) {
//                    System.out.println("clicked");
//                    // Тут ви можете викликати метод paintComponent об'єкта Cutlet
//                    //Cutlet cutlet = new Cutlet(5, 10, 200, 200);
//                    Graphics2D g2d = (Graphics2D) getGraphics();
//                   // cutlet.paintComponent(g2d);
//
//                    Cutlet cutlet = new Cutlet(100, 100, 100, 100);
//                    cutlet.paintComponent(g2d);
//                }
//            }
//        });
    }
//
//    //свої дані
//
    //метод що відмальовує панель
    public void draw(Graphics2D g2d) {
        //System.out.println("невже");
        new GrillStation().paintComponent(g2d);
        g2d.setColor(Color.lightGray);
        g2d.fillRect(0, Game.HEIGHT / 5, Game.WIDTH, Game.HEIGHT - Game.HEIGHT / 5);

        g2d.setColor(Color.decode("#f2ecd0"));
        g2d.fillRect(Game.WIDTH / 2 - Game.WIDTH / 4, Game.HEIGHT / 2 - Game.HEIGHT / 4, Game.WIDTH / 2, Game.HEIGHT / 2);


        rawMeat(g2d);

        Cutlet cutlet = new Cutlet(100, 100, 0, 0);
        cutlet.paintComponent(g2d);


//        Cutlet cutlet = new Cutlet(100, 100, 100, 100);
//        cutlet.paintComponent(g2d);

        //cutlet.reduceTime();
    }

    public void drawTrash(Graphics2D g2d) {

    }

    //працює як кнопка. натискаєш сюди і на пательні з'являється котлета
    public void rawMeat(Graphics2D g2d) {
        g2d.fillRect(Game.WIDTH / 10, Game.HEIGHT - Game.HEIGHT / 3, 100, 50);
    }

    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        // Check if within the button bounds
        int buttonX = Game.WIDTH / 10;
        int buttonY = Game.HEIGHT - Game.HEIGHT / 3;
        int buttonWidth = 100;
        int buttonHeight = 50;

        if (mouseX >= buttonX && mouseX <= buttonX + buttonWidth &&
                mouseY >= buttonY && mouseY <= buttonY + buttonHeight) {

            System.out.println("clicked");
        }
    }


}
