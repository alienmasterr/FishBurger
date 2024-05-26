package GrillStation;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import GrillStation.GrillStationElements.GrillBoard;
import Menu.*;
import GrillStation.GrillStationElements.Meat;
import Menu.Game;

public class GrillStation {

    private MainPanel.GamePanel parent;
    private Meat meat = new Meat(0, 240, 100, 100);
//    private Meat meat2 = new Meat(0, 200, 100, 100);
    private GrillBoard grillBoard = new GrillBoard(Game.WIDTH / 2 - Game.WIDTH / 4, Game.HEIGHT / 2 - Game.HEIGHT / 4, Game.WIDTH / 2, Game.HEIGHT / 2);

    private Point initialClick;


    public GrillStation(MainPanel.GamePanel parent) {
        this.parent = parent;
        addMouseListeners();
    }

    private boolean meatTaken = false;
    private void addMouseListeners() {
        parent.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getX() >= meat.getX() && e.getX() <= meat.getX() + 200 && e.getY() >= meat.getY() && e.getY() <= meat.getY() + 200) {
                    initialClick = e.getPoint();
//                    System.out.println("Taking Meat");
                }
            }
        });


        parent.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if (!meatTaken && initialClick != null) {
                    int thisX = meat.getX();
                    int thisY = meat.getY();

//                    System.out.println("thisX: " + thisX + " thisY: " + thisY);
                    if (thisX >= grillBoard.getX() && thisX <= grillBoard.getX() + grillBoard.getWidth() && thisY >= grillBoard.getY() && thisY <= grillBoard.getY() + grillBoard.getHeight()) {
                        System.out.println("INSIDE");
                        for (int i = 1; i <= 4; i++) {
                            if(!areaOccupied(grillBoard.getX() + 10 * i, grillBoard.getY() + 10 * i, meat)){
                                meat.setPosition(grillBoard.getX() + 10 * i, grillBoard.getY() + 10 * i);
                                meatTaken = true;
//                                break;
                            }
                        }


                    }


                    int xMoved = e.getX() - initialClick.x;
                    int yMoved = e.getY() - initialClick.y;

                    int X = thisX + xMoved;
                    int Y = thisY + yMoved;

                    meat.setPosition(X, Y);
                    initialClick = e.getPoint();
                }
            }
        });
    }

    public boolean areaOccupied(int x, int y, Meat meat) {
        return meat.getX() == x && meat.getY() == y;
    }

////    private void takeMeat() {
////        if (parent.mouse.pressed && parent.mouse.x >= getMeat().getX() && parent.mouse.x <= getMeat().getX() + 200 && parent.mouse.y <= getMeat().getY() + 200  && parent.mouse.y >= getMeat().getY()){
////            System.out.println("Taking Meat");
////        }
//////            parent.orderState = OrderState.CUSTOMER_ORDERING;
////    }
//
//    public Meat getMeat(){
//        return meat;
//    }


//
//    //свої дані
//

    //метод що відмальовує панель
    public void draw(Graphics2D g2d) {

        g2d.setColor(Color.lightGray);
        g2d.fillRect(0, Game.HEIGHT / 5, Game.WIDTH, Game.HEIGHT - Game.HEIGHT / 5);

//        g2d.setColor(Color.decode("#f2ecd0"));
//        g2d.fillRect(Game.WIDTH / 2 - Game.WIDTH / 4, Game.HEIGHT / 2 - Game.HEIGHT / 4, Game.WIDTH / 2, Game.HEIGHT / 2);


        grillBoard.draw(g2d);
        meat.draw(g2d);
//        meat2.draw(g2d);
//        takeMeat();


    }

    public void drawTrash(Graphics2D g2d) {

    }

    //працює як кнопка. натискаєш сюди і на пательні з'являється котлета
    public void rawMeat(Graphics2D g2d) {
        g2d.fillRect(Game.WIDTH / 10, Game.HEIGHT - Game.HEIGHT / 3, 100, 50);
    }


}
