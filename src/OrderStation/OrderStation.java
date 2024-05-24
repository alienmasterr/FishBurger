package OrderStation;

import Enums.OrderState;
import Menu.*;
import Menu.MenuElements.Ticket;
import OrderStation.OrderElements.*;

import java.awt.*;

public class OrderStation {
    private MainPanel.GamePanel parent;
    private OrderFish orderFish = new OrderFish(0, 240, 500, 500);
    private OrderTable orderTable = new OrderTable(0, 500, Game.WIDTH, 200);
    private OrderBackground background = new OrderBackground(0, 0, Game.WIDTH, 500);
    private Customer customer = new Customer(Game.WIDTH, 180, 260, 420);

    public OrderStation(MainPanel.GamePanel parent) {
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
                break;
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
            checkOrderBubble();
        } else
            customer.goToTable();
    }
    //я цей крінж зміню згодом
    private void checkOrderBubble() {
        if (parent.mouse.pressed && parent.mouse.x >= customer.getOrderBubble().getX() && parent.mouse.x <= customer.getOrderBubble().getX() + 200 && parent.mouse.y <= customer.getOrderBubble().getY() + 200  && parent.mouse.y >= customer.getOrderBubble().getY())
            parent.orderState = OrderState.CUSTOMER_ORDERING;
    }

    private void moveCharacter() {
        orderFish.waitForCustomer();
    }

    private void addEmptyTicket() {
        if (!parent.pin.hasTicket())
            parent.pin.setTicket(new Ticket(740, 20, 230, 420));
    }
}
