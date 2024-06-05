package GrillStation.GrillStationElements;

import Elements.Node;
import Enums.CookingState;
import GrillStation.GrillStation;
import Menu.GameMenu;
import Products.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Meat extends Product {
    private String path;
    private Timer timer;

    public Meat(int x, int y, int width, int height) {
        super(x, y, width, height);

        String path = "/meat/meat.png";

        image = getImage(path);
    }


    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private int yVelocity = -4;

    public void beReady() {
    }

    public void beShrownAway() {
    }

    public void grilling() {
        y += yVelocity;
        yVelocity *= -1;
    }

    public void startGrilling() {
        if (timer == null) {
            setupTimer();
        }
        timer.start();
    }

    int counter = 0;
//todo цей таймер треба стопати
    public void setupTimer() {
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //grillingMeatArrayList.add(selectedMeat);
                // meatArrayList.remove(selectedMeat);
                // for (Meat meat : grillingMeatArrayList) {
                // if (meat != null) {
                //meat.
                System.out.println("counter: " + counter);
                System.out.println("смажимо");
                grilling();
                //}
                //}
                counter++;
                if (counter == 5) {

                } else if (counter == 10) {
                    System.out.println("10 sec");
                    //parent.cookingState = CookingState.MEAT_READY;
                } else if (counter == 20) {
                    System.out.println("20 sec");

                } else if (counter == 30) {
                    System.out.println("30 sec");
                    path = "/meat/burntmeat.png";
                    timer.stop();
                    // parent.cookingState = CookingState.MEAT_BURNING;
                }
            }
        });
    }
}

