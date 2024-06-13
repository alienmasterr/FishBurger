package OrderStation;

import Enums.OrderState;
import Level.Level;
import Menu.*;
import Menu.MenuElements.SoundPlayer;
import Menu.MenuElements.Ticket;
import OrderStation.OrderElements.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

import Store.Store;

import Store.StoreElements.Chair;
import Store.StoreElements.IconK;
import Store.StoreElements.Painting;
import Store.StoreElements.Table;

public class OrderStation {
    private GameMenu.GamePanel parent;
    private OrderFish orderFish = new OrderFish(0, 240, 500, 500);
    private OrderTable orderTable = new OrderTable(0, 500, Game.WIDTH, 250);
    private OrderBackground background = new OrderBackground(0, 0, Game.WIDTH, 500);

    private IconK iconK = new IconK(400, 100, 100, 100);
    private Painting painting = new Painting(600, 70, 100, 100);
    private Chair chair = new Chair(100, 100, 400, 400);
    private Table table = new Table(200, 200, 400, 400);

    public Customer customer = new Customer(Game.WIDTH, 180, 260, 420);
    private Timer timer;
    private int seconds = 0;
    private int miliSec = 0;

    public OrderStation(GameMenu.GamePanel parent) {
        this.parent = parent;
    }

    public void draw(Graphics2D g2d) {
        parent.pin.setDrawTicket(true);
        drawBase(g2d);
        switch (parent.orderState) {
            case WAITING_CUSTOMER:
                moveCustomer(g2d);
                moveCharacter();
                break;
            case CUSTOMER_ORDERING:
                addEmptyTicket();
                moveCharacter();
                moveCustomer();
                customer.getEmptyBubble().draw(g2d);
                break;
            case MAKING_ORDER:
                moveCharacter();
                moveCustomer();
                break;
        }
    }

    public void updateTime() {
        miliSec++;
        if (miliSec == 60) {
            seconds++;
            miliSec = 0;
        }
    }

    public void stopTicketErasure() {
        if (timer == null || !timer.isRunning())
            return;
        timer.stop();
        parent.pin.getTicket().showAllProducts();
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    private void showBubble() {
        int size = parent.pin.getTicket().getReceipt().size();
        if (size < Level.getBurgerSize()) {
            parent.pin.getTicket().fillTicket(Level.getBurgerSize());
            BufferedImage image = parent.pin.getTicket().getReceipt().get(size).getSprite();
            customer.getEmptyBubble().getShownProduct().setSprite(image);
        } else {
            timer.stop();
            parent.orderState = OrderState.MAKING_ORDER;
            parent.toggleButtons();
            startTicketErasure();
        }
    }

    private void startTicketErasure() {
        if (Level.levelState != 3)
            return;
        timer = new Timer(12000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.pin.getTicket().hideRandomProduct();
            }
        });
        timer.start();
    }

    private void drawBase(Graphics2D g2d) {
        background.draw(g2d);
        if (Store.iconBought) {
            iconK.draw(g2d);
        }
        if (Store.paintingBought) {
            painting.draw(g2d);
        }
        if (Store.tableBought) {
            table.draw(g2d);
        }
        if (Store.chairBought) {
            chair.draw(g2d);
        }
        customer.draw(g2d);
        orderTable.draw(g2d);
        orderFish.draw(g2d);
    }

    private void moveCustomer(Graphics2D g2d) {
        if (customer.gotToTable()) {
            customer.getOrderBubble().draw(g2d);
            checkOrderBubble(g2d);
        } else
            customer.goToTable();
    }

    private void checkOrderBubble(Graphics2D g2d) {
        if (Game.mouse.pressed && Game.mouse.x >= customer.getOrderBubble().getX() && Game.mouse.x <= customer.getOrderBubble().getX() + 200 && Game.mouse.y <= customer.getOrderBubble().getY() + 200 && Game.mouse.y >= customer.getOrderBubble().getY()) {
            parent.toggleButtons();
            SoundPlayer.playButtonSound();
            parent.orderState = OrderState.CUSTOMER_ORDERING;
            timer = new Timer(1000, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showBubble();
                }
            });
            timer.start();
        }
    }

    private void moveCharacter() {
        orderFish.waitForCustomer();
    }

    private void moveCustomer() {
        customer.waitForOrder();
    }

    private void addEmptyTicket() {
        if (!parent.pin.hasTicket()) {
            parent.pin.setTicket(new Ticket(740, 20, 230, 420));
            parent.pin.getTicket().setAllSecret(false);
            if(Objects.equals(customer.getSrc(), "/customers/customer11.png"))
                parent.pin.getTicket().setAllSecret(true);
            else if(Objects.equals(customer.getSrc(), "/customers/customer13.png"))
                parent.pin.getTicket().hideAllProducts();
        }
    }
}
