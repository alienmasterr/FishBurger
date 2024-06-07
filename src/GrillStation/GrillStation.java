package GrillStation;

import java.awt.*;

import Enums.CookingState;
import GrillStation.GrillStationElements.*;
import Menu.*;
import Menu.Game;

import java.util.ArrayList;
import javax.swing.*;

public class GrillStation {

    private final GameMenu.GamePanel parent;
    //private Timer timer;

    private final ArrayList<Meat> meatArrayList = new ArrayList<>();

    private final Mince mince = new Mince(Game.WIDTH / 15, Game.HEIGHT - Game.HEIGHT / 3 + Game.HEIGHT / 10, 150, 100);
    private final Trash trash = new Trash(Game.WIDTH / 12, Game.HEIGHT / 2 - Game.HEIGHT / 4, 100, 100);
    private final Plate plate = new Plate(Game.WIDTH / 2 + Game.WIDTH / 3, Game.HEIGHT / 2 + Game.HEIGHT / 6, 100, 100);
    public final Spatula spatula = new Spatula(Game.WIDTH / 2 + Game.WIDTH / 3, Game.HEIGHT / 4, 20, 200);
    private final GrillBoard grillBoard = new GrillBoard(Game.WIDTH / 2 - Game.WIDTH / 4, Game.HEIGHT / 2 - Game.HEIGHT / 6, Game.WIDTH / 2, Game.HEIGHT / 3);
    private final GrillBackground grillBackground = new GrillBackground(0, 0, Game.WIDTH, Game.HEIGHT);

    //todo може приватним?
    public static Meat selectedMeat = null;
    public static boolean meatSent = false;
    public static boolean spatulaTaken=false;
    //public static boolean meatOnGrill=false;

    public GrillStation(GameMenu.GamePanel parent) {
        this.parent = parent;
    }

    private void moveSpatula() {
        if (!spatulaTaken && Game.mouse.pressed && Game.mouse.x >= spatula.getX() && Game.mouse.x <= spatula.getX() + 100 && Game.mouse.y >= spatula.getY() && Game.mouse.y <= spatula.getY() + 200) {
            spatulaTaken = true;
            Game.mouse.dragging = true;
        }
        if (spatulaTaken) {
            spatula.setPosition(Game.mouse.x, Game.mouse.y);
        }
        if (!Game.mouse.pressed && spatulaTaken) {
            Game.mouse.dragging = false;
        }
    }

    private void flip(){
        if(spatulaTaken &&  selectedMeat != null && Game.mouse.pressed && Game.mouse.x >= selectedMeat.getX() && Game.mouse.x <= selectedMeat.getX() + 100 && Game.mouse.y >= selectedMeat.getY() && Game.mouse.y <= selectedMeat.getY() + 200 && selectedMeat.getGrilling()){
            selectedMeat.getFlipped();
            spatulaTaken=false;
            spatula.setPosition(Game.WIDTH / 2 + Game.WIDTH / 3, Game.HEIGHT / 4);

        }
    }

    private void sendMeat(Meat meat) {
        parent.transferMeatToBuild(meat);
    }

//    public boolean areaOccupied(int x, int y, Meat meat) {
//        return meat.getX() == x && meat.getY() == y;
//    }

    //метод що відмальовує панель
    public void draw(Graphics2D g2d) {
        parent.pin.setDrawTicket(true);
        drawBase(g2d);
        switch (parent.cookingState) {
            case NO_MEAT -> createMeat(g2d);
            case MEAT_NOT_READY -> drawNewMeat(g2d);
            case MEAT_GRILLING -> grillingMeat(g2d);
            case MEAT_READY, MEAT_SENT_TO_BD, MEAT_SHROWN_AWAY -> readyMeat(g2d);
            case MEAT_BURNING -> drawNewMeat(g2d);//burntMeat
        }
        update();
    }

    private void update() {
        updateActiveElement();
        updateActiveElementMovement();
        updateMovedToGrillBoard();
        updateTransferToBuildStation();
        updateShrowAway();
    }


    private void updateTransferToBuildStation() {
        if (selectedMeat == null)
            return;
        if (selectedMeat.getX() > plate.getX() - 20 && selectedMeat.getX() < plate.getX() + plate.getWidth() + 20 && selectedMeat.getY() > plate.getY() - 20 && selectedMeat.getY() < plate.getY() + plate.getHeight() + 20) {
            //System.out.println("sent");
            sendMeat(selectedMeat);
            meatArrayList.remove(selectedMeat);
            //grillingMeatArrayList.remove(selectedMeat);
            parent.cookingState = CookingState.MEAT_SENT_TO_BD;
            meatSent = true;
            selectedMeat = null;
        }
    }

    private void updateMovedToGrillBoard() {
        if (selectedMeat == null)
            return;
        if (selectedMeat.getX() >= grillBoard.getX() && selectedMeat.getX() <= grillBoard.getX() + grillBoard.getWidth() && selectedMeat.getY() >= grillBoard.getY() && selectedMeat.getY() <= grillBoard.getY() + grillBoard.getHeight()) {
            parent.cookingState = CookingState.MEAT_GRILLING;
            //meatOnGrill=true;

           selectedMeat.setGrilling(true);
            //grillingMeatArrayList.add(selectedMeat);
           // meatArrayList.remove(selectedMeat);
            // System.out.println("перейшли в режим смаження");
            //Meat.isGrilling = true;
        }else{
            //meatOnGrill = false;

            selectedMeat.setGrilling(false);

            //Meat.isGrilling = false;
        }
    }

    private void updateShrowAway() {
        if (selectedMeat == null)
            return;
        if (selectedMeat.getX() >= trash.getX() && selectedMeat.getX() - 25 <= trash.getX() + trash.getWidth() && selectedMeat.getY() >= trash.getY() && selectedMeat.getY() <= trash.getY() + trash.getHeight() - 50) {
            //System.out.println("перейшли в режим м'со викинуто");
            meatArrayList.remove(selectedMeat);

            //grillingMeatArrayList.remove(selectedMeat);
            parent.cookingState = CookingState.MEAT_SHROWN_AWAY;
            //todo можливо треба перевірка чи це м'ясо взагалі існує в цьому ареї
            selectedMeat = null;
        }
    }

    private void updateActiveElementMovement() {
        if (selectedMeat == null)
            return;
        selectedMeat.setPosition(Game.mouse.x - selectedMeat.getWidth() / 2, Game.mouse.y - selectedMeat.getHeight() / 2);
    }

    private void updateActiveElement() {
        if (selectedMeat != null && Game.mouse.pressed)
            return;
        selectedMeat = null;
        findNewActiveElement();
    }

    private void findNewActiveElement() {
        for (Meat meat : meatArrayList) {
            findEl(meat);
        }
    }

    private void findEl(Meat meat) {
        if (Game.mouse.pressed && Game.mouse.x >= meat.getX() && Game.mouse.x <= meat.getX() + 100 && Game.mouse.y >= meat.getY() && Game.mouse.y <= meat.getY() + 200) {
            selectedMeat = meat;
            //return;
        }
    }

    private void createMeat(Graphics2D g2d) {
        grillBoard.draw(g2d);
        plate.draw(g2d);
        trash.draw(g2d);
        mince.draw(g2d);
        spatula.draw(g2d);
        activateMinceButton();
        moveSpatula();
    }

    private void activateMinceButton() {
        if (Game.mouse.pressed && Game.mouse.x >= mince.getX() && Game.mouse.x <= mince.getX() + 200 && Game.mouse.y <= mince.getY() + 200 && Game.mouse.y >= mince.getY()) {
            meatArrayList.add(new Meat(0, 350, 150, 100));
            parent.cookingState = CookingState.MEAT_NOT_READY;
        }
    }

    private void flipMeat() {
        if (selectedMeat != null && Game.mouse.pressed && Game.mouse.x >= selectedMeat.getX() && Game.mouse.x <= selectedMeat.getX() + 200 && Game.mouse.y <= selectedMeat.getY() + 200 && Game.mouse.y >= selectedMeat.getY() && selectedMeat.getX() >= grillBoard.getX() && selectedMeat.getX() <= grillBoard.getX() + grillBoard.getWidth() && selectedMeat.getY() >= grillBoard.getY() && selectedMeat.getY() <= grillBoard.getY() + grillBoard.getHeight()) {
            selectedMeat.getFlipped();
        }
    }

    private void readyMeat(Graphics2D g2d) {
        drawNewMeat(g2d);
        //meat.beReady();
        for (Meat meat : meatArrayList) meat.beReady();
    }

//    private void meatBurnt(Graphics2D g2d) {
//        //System.out.println("гориш чи ні");
//        drawNewMeat(g2d);
//        //todo тут треба селектид міт заміняти на іншу змінну м'яса, бо селектид це те що ми тримаємо мишкою
////       if (selectedMeat != null) {
////           // BurningSign burningSign = new BurningSign(selectedMeat.getX(), selectedMeat.getY(), 50, 50);
////           // burningSign.draw(g2d);
////           // selectedMeat.setImage("/meat/burntmeat.png");
////            System.out.println("картинка бурнт міт");
////      }
//
//    }

//    private void noMeat(Graphics2D g2d) {
//        grillBoard.draw(g2d);
//        plate.draw(g2d);
//        trash.draw(g2d);
//        mince.draw(g2d);
//
//        //createMeat(g2d);
//    }

//    public void startGrilling() {
//        if (timer == null) {
//           // setupTimer();
//        }
//        timer.start();
//    }

    public void grillingMeat(Graphics2D g2d) {
        drawNewMeat(g2d);
        //тут не було циклу
        //System.out.println("ми в грілін міт");

        //!!!!!!!!!!!був грілін
        for (Meat m : meatArrayList) {
            // System.out.println("ми в циклі");
            if(m.getX() >= grillBoard.getX() && m.getX() <= grillBoard.getX() + grillBoard.getWidth() && m.getY() >= grillBoard.getY() && m.getY() <= grillBoard.getY() + grillBoard.getHeight()) {
                m.startGrilling();
                //todo flip meat
                //flipMeat();
                flip();
            }
        }

    }

    private void drawNewMeat(Graphics2D g2d) {
        createMeat(g2d);

        for (Meat meat : meatArrayList) {
            meat.draw(g2d);
        }

    }

    private void drawBase(Graphics2D g2d) {
      //  g2d.setColor(Color.lightGray);
      //  g2d.fillRect(0, Game.HEIGHT / 5, Game.WIDTH, Game.HEIGHT - Game.HEIGHT / 5);
        grillBackground.draw(g2d);
    }


}
