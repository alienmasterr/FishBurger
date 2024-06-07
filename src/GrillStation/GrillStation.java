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
    private final ArrayList<Meat> grillingMeatArrayList = new ArrayList<>();

    private final Mince mince = new Mince(Game.WIDTH / 15, Game.HEIGHT - Game.HEIGHT / 3 + Game.HEIGHT / 10, 150, 100);
    private final Trash trash = new Trash(Game.WIDTH / 12, Game.HEIGHT / 2 - Game.HEIGHT / 4, 100, 100);
    private final Plate plate = new Plate(Game.WIDTH / 2 + Game.WIDTH / 3, Game.HEIGHT / 2 + Game.HEIGHT / 6, 100, 100);
    public final Spatula spatula = new Spatula(Game.WIDTH / 2 + Game.WIDTH / 3, Game.HEIGHT / 4, 20, 200);
    private final GrillBoard grillBoard = new GrillBoard(Game.WIDTH / 2 - Game.WIDTH / 4, Game.HEIGHT / 2 - Game.HEIGHT / 6, Game.WIDTH / 2, Game.HEIGHT / 3);

    //todo можу приватним?
    public static Meat selectedMeat = null;
    public static boolean meatSent = false;
    public static boolean spatulaTaken=false;

    public GrillStation(GameMenu.GamePanel parent) {
        this.parent = parent;
    }

    private void addMouseListeners() {
//        parent.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//
//                //ПРОБЛЕМА ТЯГАННЯ ТУТ
//                //якщо цей метод застосовувати до ліста, то можна тягати мишкою будь що будь коли
//                //але
//                //смажиться і стрибає лише котлета яку ти щойно перетягнув
//                for (Meat meat : meatArrayList) {
//                    if (e.getX() >= meat.getX() && e.getX() <= meat.getX() + 100 && e.getY() >= meat.getY() && e.getY() <= meat.getY() + 200) {
//                        initialClick = e.getPoint();
//                        selectedMeat = meat;
//                        break;
//                    }
//                }
//                for (Meat meat : grillingMeatArrayList) {
//                    if(meat!=null){
//                        if (e.getX() >= meat.getX() && e.getX() <= meat.getX() + 100 && e.getY() >= meat.getY() && e.getY() <= meat.getY() + 200) {
//                            initialClick = e.getPoint();
//                            selectedMeat = meat;
//                            break;
//                        }
//                    }
//                }
//            }
//        });
//
//        parent.addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                if (!meatTaken && initialClick != null && selectedMeat != null) {
//                    int thisX = selectedMeat.getX();
//                    int thisY = selectedMeat.getY();
//                    if (thisX >= grillBoard.getX() && thisX <= grillBoard.getX() + grillBoard.getWidth() && thisY >= grillBoard.getY() && thisY <= grillBoard.getY() + grillBoard.getHeight()) {
//                        parent.cookingState = CookingState.MEAT_GRILLING;
//                        // System.out.println("перейшли в режим смаження");
//                    }
//
//                    if (thisX >= trash.getX() && thisX <= trash.getX() + trash.getWidth() && thisY >= trash.getY() && thisY <= trash.getY() + trash.getHeight()) {
//                        System.out.println("перейшли в режим м'со викинуто");
//                        parent.cookingState = CookingState.MEAT_SHROWN_AWAY;
//
//                        //todo можливо треба перевірка чи це м'ясо взагалі існує в цьому ареї
//                        //ПРОБЛЕМА З ВИДАЛЕННЯМ
//                        meatArrayList.remove(selectedMeat);
//                        grillingMeatArrayList.remove(selectedMeat);
//                        selectedMeat = null;
//                        return;
//                    }
//
//                    if (thisX >= plate.getX() && thisX <= plate.getX() + plate.getWidth() && thisY >= plate.getY() && thisY <= plate.getY() + plate.getHeight()) {
//                        parent.cookingState = CookingState.MEAT_SENT_TO_BD;
//                        System.out.println("sent");
//                        meatArrayList.remove(selectedMeat);
//                        //numOfMeat++;
//                        sentMeat = true;
//                        selectedMeat = null;
//                        sendMeat(selectedMeat);
//                        return;
//                    }
//
//                    int xMoved = e.getX() - initialClick.x;
//                    int yMoved = e.getY() - initialClick.y;
//                    int X = thisX + xMoved;
//                    int Y = thisY + yMoved;
//                    selectedMeat.setPosition(X, Y);
//                    initialClick = e.getPoint();
//                }
//            }
//        });
    }

    private void moveSpatula() {
//        if (Game.mouse.pressed && Game.mouse.x >= spatula.getX() && Game.mouse.x <= spatula.getX() + 100 && Game.mouse.y >= spatula.getY() && Game.mouse.y <= spatula.getY() + 200) {
//            spatulaTaken = true;
//                spatula.setX(Game.mouse.x);
//                spatula.setY(Game.mouse.y);
//
//        }
        if (Game.mouse.pressed && Game.mouse.x >= spatula.getX() && Game.mouse.x <= spatula.getX() + 100 && Game.mouse.y >= spatula.getY() && Game.mouse.y <= spatula.getY() + 200) {
            //spatulaTaken = true;
            spatula.setX(Game.mouse.x);
            spatula.setY(Game.mouse.y);
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
            grillingMeatArrayList.remove(selectedMeat);

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
            grillingMeatArrayList.add(selectedMeat);
            meatArrayList.remove(selectedMeat);
            // System.out.println("перейшли в режим смаження");
        }
    }

    private void updateShrowAway() {
        if (selectedMeat == null)
            return;
        if (selectedMeat.getX() >= trash.getX() && selectedMeat.getX() - 25 <= trash.getX() + trash.getWidth() && selectedMeat.getY() >= trash.getY() && selectedMeat.getY() <= trash.getY() + trash.getHeight() - 50) {
            //System.out.println("перейшли в режим м'со викинуто");
            meatArrayList.remove(selectedMeat);
            grillingMeatArrayList.remove(selectedMeat);
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
        for (Meat meat : grillingMeatArrayList) {
            if (meat != null) {
                findEl(meat);
            }
        }
    }

    private void findEl(Meat meat) {
        if (Game.mouse.pressed && Game.mouse.x >= meat.getX() && Game.mouse.x <= meat.getX() + 100 && Game.mouse.y >= meat.getY() && Game.mouse.y <= meat.getY() + 200) {
            selectedMeat = meat;
            //return;
        }
    }


    // int counter = 0;

//    public void setupTimer() {
//        this.timer = new Timer(1000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                grillingMeatArrayList.add(selectedMeat);
//                meatArrayList.remove(selectedMeat);
//                for (Meat meat : grillingMeatArrayList) {
//                    if (meat != null) {
//                        meat.grilling();
//                    }
//                }
//                counter++;
//                if (counter == 5) {
//
//                } else if (counter == 10) {
//                    System.out.println("10 sec");
//                    parent.cookingState = CookingState.MEAT_READY;
//                } else if (counter == 20) {
//                    System.out.println("20 sec");
//
//                } else if (counter == 30) {
//                    System.out.println("30 sec");
//                    timer.stop();
//                    parent.cookingState = CookingState.MEAT_BURNING;
//                }
//            }
//        });
//    }


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
        for (Meat meat : grillingMeatArrayList) {
            if (meat != null) {
                meat.beReady();
            }
        }
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
        for (Meat m : grillingMeatArrayList) {
            // System.out.println("ми в циклі");
            m.startGrilling();
            flipMeat();
        }

    }

    private void drawNewMeat(Graphics2D g2d) {
        // mince.draw(g2d);
        //activateMinceButton();

        createMeat(g2d);

        //grillBoard.draw(g2d);
        //plate.draw(g2d);
        //trash.draw(g2d);

        for (Meat meat : meatArrayList) {
            meat.draw(g2d);
        }

        if (!grillingMeatArrayList.isEmpty()) {
            for (Meat meat : grillingMeatArrayList) {
                if (meat != null) {
                    meat.draw(g2d);
                }
            }
        }

//        meat.draw(g2d);
    }

//    private void drawImportant(Graphics2D g2d){
//        spatula.draw(g2d);
//    }

    private void drawBase(Graphics2D g2d) {
        g2d.setColor(Color.lightGray);
        g2d.fillRect(0, Game.HEIGHT / 5, Game.WIDTH, Game.HEIGHT - Game.HEIGHT / 5);
    }


}
