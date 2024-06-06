package Store;

import Elements.Node;
import Enums.BuildState;
import Enums.CookingState;
import Enums.StoreState;
import GrillStation.GrillStationElements.Meat;
import Menu.GameMenu;

import java.awt.*;

import Menu.*;

import javax.swing.*;
import java.awt.*;

public class Store {

    private final GameMenu.GamePanel parent;

    public StoreState storeState = StoreState.CHOOSING;

    private final SaveButton saveButton = new SaveButton(Game.WIDTH/2-35,Game.HEIGHT-300,70,70);

    public Store(GameMenu.GamePanel parent) {
        this.parent = parent;
    }

    public void draw(Graphics2D g2d) {
        parent.pin.setDrawTicket(false);
        drawBase(g2d);
        saveButton.draw(g2d);
        activateSaveButton();
        switch (storeState) {
            case SAVED -> saveAll();
        }
    }

    private void drawBase(Graphics2D g2d) {
        //g2d.setColor(Color.BLUE);
        //g2d.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
    }

    public void activateSaveButton(){
        if (Game.mouse.pressed && Game.mouse.x >= saveButton.getX() && Game.mouse.x <= saveButton.getX() + 200 && Game.mouse.y <= saveButton.getY() + 200 && Game.mouse.y >= saveButton.getY()) {
            storeState = StoreState.SAVED;
        }
    }
    public void saveAll(){

        //parent.pin.setDrawTicket(true);
    }

}

class SaveButton extends Node {
    public SaveButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        image = getImage("/temp.png");
    }
}
