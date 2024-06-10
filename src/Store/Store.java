package Store;

import Elements.Node;
import Enums.StoreState;
import Menu.GameMenu;

import java.awt.*;

import Menu.*;
import Menu.MenuElements.MoneyDisplay;
import Store.StoreElements.*;

import java.util.ArrayList;

public class Store {

    private final GameMenu.GamePanel parent;

    public StoreState storeState = StoreState.CHOOSING;

    private final BusketButton busketButton = new BusketButton(Game.WIDTH / 2 - 50, Game.HEIGHT - 130, 100, 50);
    private final StoreButton storeButton = new StoreButton(Game.WIDTH / 2 - 50, Game.HEIGHT - 130, 100, 50);

    private final StoreBackground storeBackground = new StoreBackground(0, 0, Game.WIDTH, Game.HEIGHT);
    public MoneyDisplay moneyDisplay = new MoneyDisplay(5, 20, 50, 50);
    public Chair chair = new Chair(100, 120, 200, 200, 500);
    public Table table = new Table(400, 120, 200, 200, 1000);
    public GoldenSpatula goldenSpatula = new GoldenSpatula(700, 120, 200, 200, 1500);
    public GoldenSink goldenSink = new GoldenSink(100, 400, 200, 200, 2000);
    public Painting painting = new Painting(400, 400, 200, 200, 2500);
    public IconK iconK = new IconK(700, 400, 200, 200, 3000);

    public ArrayList<Accessories> boughtAccessoriesArrayList = new ArrayList<>();
    private Accessories[] accessories = {chair, table, goldenSpatula, goldenSink, painting, iconK};

    public static boolean chairBought = false;
    public static boolean tableBought = false;
    public static boolean goldenSpatulaBought = false;
    public static boolean goldenSinkBought = false;
    public static boolean paintingBought = false;
    public static boolean iconBought = false;


    public Store(GameMenu.GamePanel parent) {
        this.parent = parent;
    }

    public void draw(Graphics2D g2d) {
        storeBackground.draw(g2d);
        switch (storeState) {
            case BUSKET -> drawBusket(g2d);
            case CHOOSING -> drawStore(g2d);
        }
        moneyDisplay.draw(g2d);
    }

    public ArrayList<Accessories> getBoughtAccessories() {
        return boughtAccessoriesArrayList;
    }

    public void drawStore(Graphics2D g2d) {
        //busketButton.draw(g2d);
        // openBusket();
        drawAllAccessories(g2d);
        buyAccessories();
    }


    private void drawAllAccessories(Graphics2D g2d) {

        drawAccessory(g2d);
        drawPrices(g2d);
    }

    private void drawPrices(Graphics2D g2d) {
        g2d.drawString(" Price: $" + 500, 100, 100);
        g2d.drawString(" Price: $" + 1000, 400, 100);
        g2d.drawString(" Price: $" + 1500, 700, 100);
        g2d.drawString(" Price: $" + 2000, 100, 380);
        g2d.drawString(" Price: $" + 2500, 400, 380);
        g2d.drawString(" Price: $" + 3000, 700, 380);

    }

    private void drawAccessory(Graphics2D g2d) {
        chair.draw(g2d);
        table.draw(g2d);
        goldenSpatula.draw(g2d);
        goldenSink.draw(g2d);
        painting.draw(g2d);
        iconK.draw(g2d);
    }

    private boolean paid = false;

    private void buyAccessories() {
        if (Game.mouse.pressed && !paid) {
            for (Accessories accessory : accessories) {
                if (!accessory.getFileName().equals("/store/lockedproduct.png") && Game.mouse.pressed && Game.mouse.x >= accessory.getX() && Game.mouse.x <= accessory.getX() + 200 && Game.mouse.y <= accessory.getY() + 200 && Game.mouse.y >= accessory.getY() && parent.money >= accessory.getPrice()) {
                    if (accessory.getFileName().equals("/store/goldenspatula.png")) {
                        System.out.println(accessory.getFileName() + " " + accessory.getPrice());
                        goldenSpatulaBought = true;
                    } else if (accessory.getFileName().equals("/store/goldensink.png")) {
                        System.out.println(accessory.getFileName() + " " + accessory.getPrice());
                        goldenSinkBought = true;
                    } else if (accessory.getFileName().equals("/store/picture.png")) {
                        System.out.println(accessory.getFileName() + " " + accessory.getPrice());
                        paintingBought = true;
                    } else if (accessory.getFileName().equals("/store/icon.png")) {
                        System.out.println(accessory.getFileName() + " " + accessory.getPrice());
                        iconBought = true;
                    } else if (accessory.getFileName().equals("/store/chair.png")) {
                        System.out.println(accessory.getFileName() + " " + accessory.getPrice());
                        chairBought = true;
                    } else if (accessory.getFileName().equals("/store/table.png")) {
                        System.out.println(accessory.getFileName() + " " + accessory.getPrice());
                        tableBought = true;
                    }
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

    public void openBusket() {
        if (Game.mouse.pressed && Game.mouse.x >= busketButton.getX() && Game.mouse.x <= busketButton.getX() + 200 && Game.mouse.y <= busketButton.getY() + 200 && Game.mouse.y >= busketButton.getY()) {
            storeState = StoreState.BUSKET;
        }
    }

    public void backToStore() {
        if (Game.mouse.pressed && Game.mouse.x >= storeButton.getX() && Game.mouse.x <= storeButton.getX() + 200 && Game.mouse.y <= storeButton.getY() + 200 && Game.mouse.y >= storeButton.getY()) {
            storeState = StoreState.CHOOSING;
        }
    }

    public void drawBusket(Graphics2D g2d) {
        storeButton.draw(g2d);
        backToStore();
        for (Accessories boughtA : boughtAccessoriesArrayList) {
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


