package Store;

import Elements.Node;
import Enums.StoreState;
import Menu.GameMenu;

import java.awt.*;

import Menu.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Store {

    private final GameMenu.GamePanel parent;

    public StoreState storeState = StoreState.CHOOSING;

    private final HelpButton helpButton = new HelpButton(Game.WIDTH/2-50,Game.HEIGHT-150,100,50);
    public AccessoryOne accessoryOne = new AccessoryOne(100, 120, 200, 200, 1000);
    public AccessoryTwo accessoryTwo = new AccessoryTwo(400, 120, 200, 200, 2000);
    public AccessoryThree accessoryThree = new AccessoryThree(700, 120, 200, 200, 3000);
    public AccessoryFour accessoryFour = new AccessoryFour(100, 400, 200, 200, 4000);
    public AccessoryFive accessoryFive = new AccessoryFive(400, 400, 200, 200, 5000);
    public AccessorySix accessorySix = new AccessorySix(700, 400, 200, 200, 6000);

    public ArrayList<Accessories> boughtAccessoriesArrayList = new ArrayList<>();

    public Store(GameMenu.GamePanel parent) {
        this.parent = parent;
    }

    public void draw(Graphics2D g2d) {
        parent.pin.setDrawTicket(false);
        drawBase(g2d);
        helpButton.draw(g2d);
        activateSaveButton();
        drawAllAccessories(g2d);
        buyAccessories();
        switch (storeState) {
            case SAVED -> saveAll();
        }
    }

    private void drawAllAccessories(Graphics2D g2d) {
        accessoryOne.draw(g2d);
        accessoryTwo.draw(g2d);
        accessoryThree.draw(g2d);
        accessoryFour.draw(g2d);
        accessoryFive.draw(g2d);
        accessorySix.draw(g2d);
    }

    private void buyAccessories() {
        if(Game.mouse.pressed && Game.mouse.x >= accessoryOne.getX() && Game.mouse.x <= accessoryOne.getX() + 200 && Game.mouse.y <= accessoryOne.getY() + 200 && Game.mouse.y >= accessoryOne.getY()){
           accessoryOne.startFalling();
           boughtAccessoriesArrayList.add(accessoryOne);
        }
        if(Game.mouse.pressed && Game.mouse.x >= accessoryTwo.getX() && Game.mouse.x <= accessoryTwo.getX() + 200 && Game.mouse.y <= accessoryTwo.getY() + 200 && Game.mouse.y >= accessoryTwo.getY()){
            accessoryTwo.startFalling();
            boughtAccessoriesArrayList.add(accessoryTwo);
        }
        if(Game.mouse.pressed && Game.mouse.x >= accessoryThree.getX() && Game.mouse.x <= accessoryThree.getX() + 200 && Game.mouse.y <= accessoryThree.getY() + 200 && Game.mouse.y >= accessoryThree.getY()){
            accessoryThree.startFalling();
            boughtAccessoriesArrayList.add(accessoryThree);
        }
        if(Game.mouse.pressed && Game.mouse.x >= accessoryFour.getX() && Game.mouse.x <= accessoryFour.getX() + 200 && Game.mouse.y <= accessoryFour.getY() + 200 && Game.mouse.y >= accessoryFour.getY()){
            accessoryFour.startFalling();
            boughtAccessoriesArrayList.add(accessoryFour);
        }
        if(Game.mouse.pressed && Game.mouse.x >= accessoryFive.getX() && Game.mouse.x <= accessoryFive.getX() + 200 && Game.mouse.y <= accessoryFive.getY() + 200 && Game.mouse.y >= accessoryFive.getY()){
            accessoryFive.startFalling();
            boughtAccessoriesArrayList.add(accessoryFive);
        }
        if(Game.mouse.pressed && Game.mouse.x >= accessorySix.getX() && Game.mouse.x <= accessorySix.getX() + 200 && Game.mouse.y <= accessorySix.getY() + 200 && Game.mouse.y >= accessorySix.getY()){
            accessorySix.startFalling();
            boughtAccessoriesArrayList.add(accessorySix);
        }
    }

    private void drawBase(Graphics2D g2d) {
        //g2d.setColor(Color.BLUE);
        //g2d.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
    }

    public void activateSaveButton(){
        if (Game.mouse.pressed && Game.mouse.x >= helpButton.getX() && Game.mouse.x <= helpButton.getX() + 200 && Game.mouse.y <= helpButton.getY() + 200 && Game.mouse.y >= helpButton.getY()) {
            storeState = StoreState.SAVED;
        }
    }
    public void saveAll(){

    }

}

class HelpButton extends Node {
    public HelpButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/temp.png");
    }
}


