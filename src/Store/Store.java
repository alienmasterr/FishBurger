package Store;

import Elements.Node;
import Enums.StoreState;
import Menu.GameMenu;

import java.awt.*;

import Menu.*;
import Menu.MenuElements.MoneyDisplay;
import Store.StoreElements.ProductBack;
import Store.StoreElements.StoreBackground;

import java.util.ArrayList;

public class Store {

    private final GameMenu.GamePanel parent;

    public StoreState storeState = StoreState.CHOOSING;

    private final BusketButton busketButton = new BusketButton(Game.WIDTH / 2 - 50, Game.HEIGHT - 130, 100, 50);
    private final StoreButton storeButton = new StoreButton(Game.WIDTH / 2 - 50, Game.HEIGHT - 130, 100, 50);

    private final StoreBackground storeBackground = new StoreBackground(0, 0, Game.WIDTH, Game.HEIGHT);
    private MoneyDisplay moneyDisplay = new MoneyDisplay(5,20, 70, 70);

    public Chair chair = new Chair(100, 120, 200, 200, 500);
    public Table table = new Table(400, 120, 200, 200, 1000);
    public GoldenSpatula goldenSpatula = new GoldenSpatula(700, 120, 200, 200, 1500);
    public Music music = new Music(100, 400, 200, 200, 2000);
    public Painting painting = new Painting(400, 400, 200, 200, 2500);
    public Icon icon = new Icon(700, 400, 200, 200, 3000);

    public ArrayList<Accessories> boughtAccessoriesArrayList = new ArrayList<>();
    private Accessories[] accessories = {chair, table, goldenSpatula, music, painting, icon};



    public Store(GameMenu.GamePanel parent) {
        this.parent = parent;
    }

    public void draw(Graphics2D g2d) {
        parent.pin.setDrawTicket(false);
        drawBase(g2d);
        moneyDisplay.draw(g2d);
        moneyDisplay.setCurrentMoney(parent.money);
        switch (storeState) {
            case BUSKET -> drawBusket(g2d);
            case CHOOSING -> drawStore(g2d);
        }
    }

    public ArrayList<Accessories> getBoughtAccessories() {
        return boughtAccessoriesArrayList;
    }

    public void drawStore(Graphics2D g2d) {
        busketButton.draw(g2d);
        openBusket();

        drawAllAccessories(g2d);
        buyAccessories();
    }

    private void drawAllAccessories(Graphics2D g2d) {
        int startX = 100;
        int startY = 120;
        int xOffset = 300;
        int yOffset = 280;
        for (int row = 0; row < 2; row++) { // 2 рядки
            for (int col = 0; col < 3; col++) { // 3 стовпці
                int x = startX + col * xOffset;
                int y = startY + row * yOffset;
                ProductBack productBack = new ProductBack(x, y, 200, 260);
                productBack.draw(g2d);
            }
            chair.draw(g2d);
            table.draw(g2d);
            goldenSpatula.draw(g2d);
            music.draw(g2d);
            painting.draw(g2d);
            icon.draw(g2d);
        }
    }
    private boolean paid = false;

    private void buyAccessories() {
        if (Game.mouse.pressed && !paid) {
            for (Accessories accessory : accessories) {
                if (!accessory.getFileName().equals("/store/lockedproduct.png") && Game.mouse.pressed && Game.mouse.x >= accessory.getX() && Game.mouse.x <= accessory.getX() + 200 && Game.mouse.y <= accessory.getY() + 200 && Game.mouse.y >= accessory.getY() && parent.money >= accessory.getPrice()) {
                    accessory.startFalling();
                    boughtAccessoriesArrayList.add(accessory);
                    parent.money -= accessory.getPrice();
                    moneyDisplay.setCurrentMoney(parent.money);
                    break;
                }
            }
            paid = true;
        }

        if (!Game.mouse.pressed) {
            paid = false;
        }
    }

    private void drawBase(Graphics2D g2d) {
        storeBackground.draw(g2d);
    }

    public void openBusket() {
        if (Game.mouse.pressed && Game.mouse.x >= busketButton.getX() && Game.mouse.x <= busketButton.getX() + 200 && Game.mouse.y <= busketButton.getY() + 200 && Game.mouse.y >= busketButton.getY()) {
                storeState = StoreState.BUSKET;
        }
    }

    public void backToStore(){
        if (Game.mouse.pressed && Game.mouse.x >= storeButton.getX() && Game.mouse.x <= storeButton.getX() + 200 && Game.mouse.y <= storeButton.getY() + 200 && Game.mouse.y >= storeButton.getY()) {
            storeState = StoreState.CHOOSING;
        }
    }

    public void drawBusket(Graphics2D g2d) {
        storeButton.draw(g2d);
        backToStore();
        for(Accessories boughtA : boughtAccessoriesArrayList){
            boughtA.draw(g2d);
        }
    }
}

class BusketButton extends Node {
    public BusketButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/temp.png");
    }
}

class StoreButton extends Node {
    public StoreButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/temp.png");
    }
}


