package OrderStation;

import Enums.OrderState;
import OrderStation.OrderElements.*;
import Menu.Game;

import javax.swing.*;
import java.awt.*;

public class OrderStation {
    private OrderState orderState;
    private OrderFish orderFish = new OrderFish(0, 200, 500, 500);
    private OrderTable orderTable = new OrderTable(0, 500, Game.WIDTH, 200);
    private OrderBackground background = new OrderBackground(0, 0, Game.WIDTH, 500);
    private Customer customer = new Customer(Game.WIDTH, 180, 260, 420);
    public OrderStation(OrderState orderState){
        this.orderState = orderState;
    }
    public void draw(Graphics2D g2d){
        switch (orderState){
            case WAITING_CUSTOMER:
                moveCustomer();
                break;
            case CUSTOMER_ORDERING:
                break;
        }
        background.draw(g2d);
        customer.draw(g2d);
        orderTable.draw(g2d);
        orderFish.draw(g2d);
    }

    private void moveCustomer(){
        if(customer.gotToTable())
            orderState = OrderState.CUSTOMER_ORDERING;
        customer.goToTable();
    }
}
