package GrillStation;

import java.awt.*;
import java.awt.event.*;

import Enums.CookingState;
import GrillStation.GrillStationElements.GrillBoard;
import GrillStation.GrillStationElements.Plate;
import GrillStation.GrillStationElements.Trash;
import Menu.*;
import GrillStation.GrillStationElements.Meat;
import Menu.Game;

import javax.swing.*;

public class GrillStation {

    private GameMenu.GamePanel parent;
    private Timer timer;

    private Meat meat = new Meat(0, 300, 100, 100);
    private Trash trash = new Trash(Game.WIDTH / 12, Game.HEIGHT / 2 - Game.HEIGHT / 4, 100, 100);
    private Plate plate = new Plate(Game.WIDTH / 2 + Game.WIDTH / 3, Game.HEIGHT / 2 + Game.HEIGHT / 6, 100, 100);
//    private Meat meat2 = new Meat(0, 200, 100, 100);
    private GrillBoard grillBoard = new GrillBoard(Game.WIDTH / 2 - Game.WIDTH / 4, Game.HEIGHT / 2 - Game.HEIGHT / 6, Game.WIDTH / 2, Game.HEIGHT / 2);

    private Point initialClick;


    public GrillStation(GameMenu.GamePanel parent) {
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
//                        System.out.println("INSIDE");
                        parent.cookingState = CookingState.MEAT_GRILLING;
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
        drawBase(g2d);
        switch(parent.cookingState){
            case MEAT_GRILLING -> grillingMeat();
            case MEAT_READY -> meat.beReady();
        }
    }

    int counter =0;
    public void setupTimer() {
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                meat.grilling();
                counter++;
                if (counter == 10) {
                    System.out.println("sec");
                    timer.stop();
                    parent.cookingState = CookingState.MEAT_READY;
                }
            }
        });
    }

    public void startGrilling() {
        if (timer == null) {
            setupTimer();
        }
        timer.start();
    }

    public void grillingMeat(){
        startGrilling();
    }

    private void drawBase(Graphics2D g2d) {
        g2d.setColor(Color.lightGray);
        g2d.fillRect(0, Game.HEIGHT / 5, Game.WIDTH, Game.HEIGHT - Game.HEIGHT / 5);
        grillBoard.draw(g2d);
        meat.draw(g2d);
        plate.draw(g2d);
        trash.draw(g2d);
    }

//    //працює як кнопка. натискаєш сюди і на пательні з'являється котлета
//    public void rawMeat(Graphics2D g2d) {
//        g2d.fillRect(Game.WIDTH / 10, Game.HEIGHT - Game.HEIGHT / 3, 100, 50);
//    }


}
