package OrderStation;

import Enums.OrderState;
import Menu.*;
import Menu.MenuElements.Ticket;
import OrderStation.OrderElements.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class OrderStation {
    private GameMenu.GamePanel parent;
    private OrderFish orderFish = new OrderFish(0, 240, 500, 500);
    private OrderTable orderTable = new OrderTable(0, 500, Game.WIDTH, 250);
    private OrderBackground background = new OrderBackground(0, 0, Game.WIDTH, 500);
    public Customer customer = new Customer(Game.WIDTH, 180, 260, 420);
    private Timer timer;
    public OrderStation(GameMenu.GamePanel parent) {
        this.parent = parent;
    }

    public void draw(Graphics2D g2d) {
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

    private void showBubble(Graphics2D g2d){
        int size = parent.pin.getTicket().getReceipt().size();
        if(size < 7){
            parent.pin.getTicket().fillTicket(7);
            BufferedImage image = parent.pin.getTicket().getReceipt().get(size).getSprite();
            customer.getEmptyBubble().getShownProduct().setSprite(image);
        } else {
            timer.stop();
            parent.orderState = OrderState.MAKING_ORDER;
        }
    }

    private void drawBase(Graphics2D g2d) {
        background.draw(g2d);
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
        if (Game.mouse.pressed && Game.mouse.x >= customer.getOrderBubble().getX() && Game.mouse.x <= customer.getOrderBubble().getX() + 200 && Game.mouse.y <= customer.getOrderBubble().getY() + 200  && Game.mouse.y >= customer.getOrderBubble().getY()) {
            parent.orderState = OrderState.CUSTOMER_ORDERING;
            timer = new Timer(1000, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showBubble(g2d);
                }
            });
            timer.start();
        }
    }

    private void moveCharacter() {
        orderFish.waitForCustomer();
    }

    private void moveCustomer(){
        customer.waitForOrder();
    }

    private void addEmptyTicket() {
        if (!parent.pin.hasTicket())
            parent.pin.setTicket(new Ticket(740, 20, 230, 420));
    }
}
