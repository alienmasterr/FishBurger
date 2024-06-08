package Store;

import Elements.Node;
import Enums.StoreState;
import Level.Level;
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

    private final HelpButton helpButton = new HelpButton(Game.WIDTH / 2 - 50, Game.HEIGHT - 150, 100, 50);
    private final StoreBackground storeBackground = new StoreBackground(0, 0, Game.WIDTH, Game.HEIGHT);
    private MoneyDisplay moneyDisplay = new MoneyDisplay(5,20, 70, 70);

    public AccessoryOne accessoryOne = new AccessoryOne(100, 120, 200, 200, 500);
    public AccessoryTwo accessoryTwo = new AccessoryTwo(400, 120, 200, 200, 1000);
    public AccessoryThree accessoryThree = new AccessoryThree(700, 120, 200, 200, 1500);
    public AccessoryFour accessoryFour = new AccessoryFour(100, 400, 200, 200, 2000);
    public AccessoryFive accessoryFive = new AccessoryFive(400, 400, 200, 200, 2500);
    public AccessorySix accessorySix = new AccessorySix(700, 400, 200, 200, 3000);

    public ArrayList<Accessories> boughtAccessoriesArrayList = new ArrayList<>();
    private Accessories[] accessories = {accessoryOne, accessoryTwo, accessoryThree, accessoryFour, accessoryFive, accessorySix};

    public Store(GameMenu.GamePanel parent) {
        this.parent = parent;
    }

    public void draw(Graphics2D g2d) {
        parent.pin.setDrawTicket(false);
        drawBase(g2d);
        moneyDisplay.draw(g2d);
        moneyDisplay.setCurrentMoney(parent.money);

        //helpButton.draw(g2d);
        //activateSaveButton();

        drawAllAccessories(g2d);
        buyAccessories();
        switch (storeState) {
            case SAVED -> saveAll();
        }
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
            accessoryOne.draw(g2d);
            accessoryTwo.draw(g2d);
            accessoryThree.draw(g2d);
            accessoryFour.draw(g2d);
            accessoryFive.draw(g2d);
            accessorySix.draw(g2d);
        }
    }
    private boolean paid = false;

    private void buyAccessories() {
        if (Game.mouse.pressed && !paid) {
            for (Accessories accessory : accessories) {
                if (!accessory.getFileName().equals("/store/lockedproduct.png") && Game.mouse.pressed && Game.mouse.x >= accessory.getX() && Game.mouse.x <= accessory.getX() + 200 && Game.mouse.y <= accessory.getY() + 200 && Game.mouse.y >= accessory.getY() && parent.money >= accessory.getPrice()) {
                    accessory.startFalling();
                    boughtAccessoriesArrayList.add(accessory);
                    System.out.println("в гаманці " + parent.money);
                    System.out.println("за продукт " + accessory.getPrice());
                    parent.money -= accessory.getPrice();
                    System.out.println("лишилося " + parent.money);
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

    public void activateSaveButton() {
        if (Game.mouse.pressed && Game.mouse.x >= helpButton.getX() && Game.mouse.x <= helpButton.getX() + 200 && Game.mouse.y <= helpButton.getY() + 200 && Game.mouse.y >= helpButton.getY()) {
            storeState = StoreState.SAVED;
        }
    }

    public void saveAll() {

    }
}

class HelpButton extends Node {
    public HelpButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/temp.png");
    }
}


