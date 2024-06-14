package GrillStation;

import java.awt.*;

import Enums.CookingState;
import GrillStation.GrillStationElements.*;
import Level.Level;
import Menu.*;
import Menu.Game;
import Menu.MenuElements.SoundPlayer;

import java.util.ArrayList;

public class GrillStation {

    private final GameMenu.GamePanel parent;

    private final ArrayList<Meat> meatArrayList = new ArrayList<>();

    private final Mince mince = new Mince(Game.WIDTH / 15, Game.HEIGHT - Game.HEIGHT / 3 + Game.HEIGHT / 10, 150, 100);
    private final Trash trash = new Trash(Game.WIDTH / 12, Game.HEIGHT / 2 - Game.HEIGHT / 4, 160, 110);
    private final Plate plate = new Plate(Game.WIDTH / 2 + Game.WIDTH / 3, Game.HEIGHT / 2 + Game.HEIGHT / 6, 160, 110);
    private static Spatula spatula = new Spatula(Game.WIDTH / 2 - 35, Game.HEIGHT / 12, 70, 200);
    private final Sink sink = new Sink(Game.WIDTH / 2 - 125, Game.HEIGHT - 300, 240, 230);

    private final Mouth mouth = new Mouth(Game.WIDTH / 2-150, -110, 300, 100);

    private final GrillBoard grillBoard = new GrillBoard(Game.WIDTH / 2 - Game.WIDTH / 4, Game.HEIGHT / 2 - Game.HEIGHT / 6, Game.WIDTH / 2, Game.HEIGHT / 3);
    private final GrillBackground grillBackground = new GrillBackground(0, 0, Game.WIDTH, Game.HEIGHT);
    private final CookFish cookFish = new CookFish(Game.WIDTH / 2 + Game.WIDTH / 3-150, Game.HEIGHT-320, 200, 350);

    public static Meat selectedMeat = null;

    public static boolean meatSent = false;
    public boolean spatulaTaken = false;
    public static boolean meatStolen = false;

    //для повернення сплатули
    private boolean spatulaReturning = false;
    private int diffX = -1;
    private int diffY = -1;

    public GrillStation(GameMenu.GamePanel parent) {
        this.parent = parent;
    }

    private void moveSpatula() {
        if (!spatulaTaken && Game.mouse.pressed && Game.mouse.x >= spatula.getX() && Game.mouse.x <= spatula.getX() + 100 && Game.mouse.y >= spatula.getY() && Game.mouse.y <= spatula.getY() + 200) {
            spatulaTaken = true;
            SoundPlayer.playPickSound();
            Game.mouse.dragging = true;
        }
        if (spatulaTaken) {
            spatula.setPosition(Game.mouse.x, Game.mouse.y);
        }
        if (!Game.mouse.pressed && spatulaTaken) {
            Game.mouse.dragging = false;
        }
    }

    public void draw(Graphics2D g2d) {
        parent.pin.setDrawTicket(true);
        drawBase(g2d);

        grillStationAndMeatSetUp(g2d);
        switch (parent.cookingState) {
            //case NO_MEAT -> grillStationAndMeatSetUp(g2d);
            case MEAT_NOT_READY, MEAT_BURNING -> drawNewMeat(g2d);
            case MEAT_GRILLING -> grillingMeat(g2d);
            case MEAT_READY, MEAT_SENT_TO_BD, MEAT_SHROWN_AWAY -> readyMeat(g2d);
        }
        update();
        updateSpatulaReturn();
    }

    private void flip() {
        if (spatulaTaken && selectedMeat != null && Game.mouse.pressed && Game.mouse.x >= selectedMeat.getX() && Game.mouse.x <= selectedMeat.getX() + 100 && Game.mouse.y >= selectedMeat.getY() && Game.mouse.y <= selectedMeat.getY() + 200 && selectedMeat.getGrilling()) {
            selectedMeat.getFlipped();
            spatulaTaken = false;
            spatulaReturning = true;
            SoundPlayer.playPickSound();
        }
    }

    private void notFlippedSpatulaBack() {
        if (spatulaTaken && Game.mouse.pressed && Game.mouse.x >= sink.getX() && Game.mouse.x <= sink.getX() + 100 && Game.mouse.y >= sink.getY() && Game.mouse.y <= sink.getY() + 200) {
            spatulaTaken = false;
            spatulaReturning = true;
        }
    }


    private void updateSpatulaReturn() {
        if (!spatulaReturning)
            return;
        if (diffX == -1 && diffY == -1)
            calculateDiffs(Game.WIDTH / 2 - 35, Game.HEIGHT / 12, spatula.getX(), spatula.getY());
        moveToInitial(Game.WIDTH / 2 - 35, Game.HEIGHT / 12, spatula.getX(), spatula.getY());
    }

    private void calculateDiffs(int x1, int y1, int x, int y) {
        int distX = x1 - x;
        int distY = y1 - y;
        double gDist = Math.sqrt(distX * distX + distY * distY);
        int steps = (int) ((gDist - 1) / 30);
        if (steps == 0)
            steps = 1;
        diffY = distY / steps;
        diffX = distX / steps;
    }

    private void moveToInitial(int x1, int y1, int x, int y) {
        if (x1 - 30 <= x && x1 + 30 >= x && y1 - 30 < y && y1 + 30 > y) {
            diffX = -1;
            diffY = -1;
            spatulaReturning = false;
            return;
        }
        spatula.setX(spatula.getX() + diffX);
        spatula.setY(spatula.getY() + diffY);
    }

    private void getLevelOfGrill(Graphics2D g2d) {
        if (selectedMeat != null && Game.mouse.x >= selectedMeat.getX() && Game.mouse.x <= selectedMeat.getX() + selectedMeat.getWidth() &&
                Game.mouse.y >= selectedMeat.getY() && Game.mouse.y <= selectedMeat.getY() + selectedMeat.getHeight() && selectedMeat.getGrilling()) {

            LevelOfGrill levelOfGrill = new LevelOfGrill(selectedMeat.getX() - 50, selectedMeat.getY() - 50, 90, 80, selectedMeat.getSideOne(), selectedMeat.getSideTwo());
            levelOfGrill.draw(g2d);
        }
    }

    private void sendMeat(Meat meat) {
        parent.transferMeatToBuild(meat);
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
            sendMeat(selectedMeat);
            SoundPlayer.playPickSound();
            meatArrayList.remove(selectedMeat);
            //parent.cookingState = CookingState.MEAT_SENT_TO_BD;
            meatSent = true;
            selectedMeat = null;
        }
    }

    private void updateMovedToGrillBoard() {
        if (selectedMeat == null)
            return;
        if (selectedMeat.getX() >= grillBoard.getX() && selectedMeat.getX() <= grillBoard.getX() + grillBoard.getWidth() && selectedMeat.getY() >= grillBoard.getY() && selectedMeat.getY() <= grillBoard.getY() + grillBoard.getHeight()) {
            parent.cookingState = CookingState.MEAT_GRILLING;
            selectedMeat.setGrilling(true);
        } else {
            selectedMeat.setGrilling(false);
        }
    }

    private void updateShrowAway() {
        if (selectedMeat == null)
            return;
        if (selectedMeat.getX()+selectedMeat.getWidth() >= trash.getX() && selectedMeat.getX() <= trash.getX() + trash.getWidth() && selectedMeat.getY() >= trash.getY() && selectedMeat.getY()+ selectedMeat.getHeight() <= trash.getY() + trash.getHeight()) {
            meatArrayList.remove(selectedMeat);
            SoundPlayer.playPickSound();
            //parent.cookingState = CookingState.MEAT_SHROWN_AWAY;
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
            if (selectedMeat != meat)
                SoundPlayer.playPickSound();
            selectedMeat = meat;
        }
    }

    private void grillStationAndMeatSetUp(Graphics2D g2d) {
        grillBoard.draw(g2d);
        plate.draw(g2d);
        trash.draw(g2d);
        mince.draw(g2d);
        sink.chooseImage();
        sink.draw(g2d);
        cookFish.draw(g2d);
        cookFish.cookMeat();
        spatula.chooseImage();
        spatula.draw(g2d);
        mouth.draw(g2d);
        activateMinceButton();
        moveSpatula();
        notFlippedSpatulaBack();
        //getLevelOfGrill(g2d);
    }

    private void activateMinceButton() {
        if (selectedMeat == null && Game.mouse.pressed && Game.mouse.x >= mince.getX() && Game.mouse.x <= mince.getX() + 200 && Game.mouse.y <= mince.getY() + 200 && Game.mouse.y >= mince.getY()) {
            selectedMeat = new Meat(Game.mouse.x, Game.mouse.y, 150, 100);
            meatArrayList.add(selectedMeat);
            parent.cookingState = CookingState.MEAT_NOT_READY;

        }
    }

    private void readyMeat(Graphics2D g2d) {
        drawNewMeat(g2d);
        for (Meat meat : meatArrayList) meat.beReady();
    }

    public void grillingMeat(Graphics2D g2d) {
        drawNewMeat(g2d);
        boolean ate=false;
        ArrayList<Meat> meatToRemove = new ArrayList<>();
        for (Meat m : meatArrayList) {
            if (!Game.mouse.pressed && Level.levelState ==3 && m.getX() >= grillBoard.getX() + 50 && m.getX() <= grillBoard.getX() - 50 + grillBoard.getWidth() && m.getY() >= grillBoard.getY() && m.getY() <= grillBoard.getY() + grillBoard.getHeight() - 200) {
                m.isGrilling=false;
                mouth.goDown();
                //meatStolen = true;
                m.beStolen();
                ate=true;

            }
            if(ate){
              meatToRemove.add(m);
              ate=false;
            }
            if (m.getX() >= grillBoard.getX() && m.getX() <= grillBoard.getX() + grillBoard.getWidth() && m.getY() >= grillBoard.getY() && m.getY() <= grillBoard.getY() + grillBoard.getHeight()) {
                m.startGrilling();
                flip();
                getLevelOfGrill(g2d);
            }

        }
        if(meatToRemove.size()>0){
            meatArrayList.removeAll(meatToRemove);
        }
    }

    private void drawNewMeat(Graphics2D g2d) {
        // createMeat(g2d);
        for (Meat meat : meatArrayList) {
            meat.draw(g2d);
        }
    }

    private void drawBase(Graphics2D g2d) {
        grillBackground.draw(g2d);
    }
}
