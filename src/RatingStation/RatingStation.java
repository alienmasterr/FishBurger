package RatingStation;

import BuildStation.BuildElements.*;
import Enums.RatingState;
import Menu.*;
import OrderStation.OrderElements.*;
import Products.Product;
import RatingStation.RatingElements.*;
import Elements.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

public class RatingStation {
    private GameMenu.GamePanel parent;
    private Customer customer;
    private RatingState state = RatingState.RATING;
    private RatingFish fish = new RatingFish(0, 340, (int) (250 * 1.1), (int) (383 * 1.1));
    private TicketHolder ticketHolder;
    private ArrayList<Product> receipt;
    private ArrayList<Product> burgerResult;
    private ArrayList<Node> ratingBalloons = new ArrayList<>();
    private ArrayList<String> ratingValues = new ArrayList<>();
    private RatingBackground background = new RatingBackground(0, 0, Game.WIDTH, 500);
    private RatingTable ratingTable = new RatingTable(-200, 500, Game.WIDTH + 500, 270);
    private EmptyBubble emptyBubble = new EmptyBubble(200, 40, 300, 300);
    private BackJar backJar = new BackJar(720, 390, 200, 250);
    private FrontJar frontJar = new FrontJar(720, 390, 200, 250);
    private Coin coin = new Coin(790,-100, 50, 50);
    private int velocityY = 1;
    private Timer timer;

    public RatingStation(GameMenu.GamePanel parent) {
        this.parent = parent;
    }

    //620
    private void fillBalloons() {
        for (int i = 0; i < 3; i++) {
            RatingBalloon balloon = new RatingBalloon(284 + i * (100 + 50), 720, 130, 100);
            ratingBalloons.add(balloon);
        }
    }

    private void fillRatingValues() {
        ratingValues.add(String.valueOf(getBuildingRating()) + "%");
        ratingValues.add(String.valueOf(getOrderRating()) + "%");
        ratingValues.add(String.valueOf(getGrillRating()) + "%");
    }

    public void startRating() {
        fillBalloons();
        fillRatingValues();
        parent.toggleButtons();
        timer = new Timer(1200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customer.setMessage(customer.getMessage() + ".");
                if (customer.getMessage().length() == 4) {
                    state = RatingState.SHOWING_RESULT;
                    timer.stop();
                    timer = null;
                }
            }
        });
        timer.start();
    }

    public void draw(Graphics2D g2d) {
        drawBase(g2d);
        switch (state) {
            case RATING -> drawRating(g2d);
            case SHOWING_RESULT -> drawResults(g2d);
            case GETTING_MONEY -> drawGettingMoney(g2d);
            case WALKING_AWAY -> drawWalkingAway(g2d);
            case RESTARTING -> restartGame(g2d);
        }
    }

    private void drawBase(Graphics2D g2d) {
        background.draw(g2d);
        customer.draw(g2d);
        ratingTable.draw(g2d);
        ticketHolder.draw(g2d);
        fish.draw(g2d);
        for (Product pr : burgerResult)
            pr.draw(g2d);
    }

    private void restartGame(Graphics2D g2d){
        drawLoadingScreen(g2d);
    }

    private void drawLoadingScreen(Graphics2D g2d){
        g2d.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        g2d.setPaint(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 40));
        g2d.drawString(customer.getMessage(), (Game.WIDTH/2)-300, Game.HEIGHT/2);
        g2d.setPaint(Color.black);
    }

    private void drawWalkingAway(Graphics2D g2d){
        fish.waitForRating();
        backJar.draw(g2d);
        coin.draw(g2d);
        frontJar.draw(g2d);
        moveCustomerToExit();
        drawMoney(g2d);
    }

    private void drawMoney(Graphics2D g2d){
        g2d.setPaint(Color.green);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 40));
        g2d.drawString("+" + String.format("%.2f", calculateMoney()) + "$", 800, 560-velocityY);
        velocityY++;
    }

    private void moveCustomerToExit(){
        if(customer.getX() < -300) {
            state = RatingState.RESTARTING;
            parent.pin.setDrawTicket(false);
            parent.money = 5;
            customer.setMessage("Waiting for the next customer");
            timer = new Timer(1200, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    customer.setMessage(customer.getMessage()+".");
                    if(customer.getMessage().length() == 34) {
                        parent.pin.setDrawTicket(true);
                        timer.stop();
                        parent.restartGame();
                    }
                }
            });
            timer.start();
        }
        customer.goToTable();
    }

    private void drawGettingMoney(Graphics2D g2d){
        backJar.draw(g2d);
        coin.draw(g2d);
        frontJar.draw(g2d);
        drawRatingBalloons(g2d);
        moveCoin();
        withdrawRatingBalloons();
        update();
    }

    private void withdrawRatingBalloons(){
        for (Node rb : ratingBalloons)
            rb.setY(rb.getY() + 2);
    }

    private void moveCoin(){
        if(coin.getY()+50 >= 390+200) {
            parent.money+=calculateMoney();
            state = RatingState.WALKING_AWAY;
        }
        coin.setY(coin.getY()+8);
    }

    private double calculateMoney(){
        parent.money+= ((double) getAverageRating() /100)*10;
        return ((double) getAverageRating() /100)*10;
    }

    private void drawRating(Graphics2D g2d) {
        frontJar.draw(g2d);
        backJar.draw(g2d);
        drawThinkingBubble(g2d);
        drawDots(g2d);
        update();
    }

    private void drawResults(Graphics2D g2d) {
        frontJar.draw(g2d);
        backJar.draw(g2d);
        drawEmotion(g2d);
        drawRatingBalloons(g2d);
        moveRatingBalloons();
        update();
    }

    private void drawRatingBalloons(Graphics2D g2d){
        for (Node rb : ratingBalloons)
            rb.draw(g2d);
        for(int i = 0; i < 3; i++){
            g2d.setPaint(Color.WHITE);
            g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 20));
            g2d.drawString(ratingValues.get(i), ratingBalloons.get(i).getX()+40, ratingBalloons.get(i).getY()+50);
        }
    }

    private void drawEmotion(Graphics2D g2d){
        drawThinkingBubble(g2d);
        if (getAverageRating() < 50) {
            NegativeReaction negativeReaction = new NegativeReaction(297, 135, 100, 100);
            negativeReaction.draw(g2d);
        } else {
            PositiveReaction positiveReaction = new PositiveReaction(297, 135, 100, 100);
            positiveReaction.draw(g2d);
        }
    }

    private void moveRatingBalloons() {
        if (ratingBalloons.getFirst().getY() <= 620) {
            startGetMoneyState();
            return;
        }
        for (Node rb : ratingBalloons)
            rb.setY(rb.getY() - 2);
    }

    private void startGetMoneyState() {
        if (timer != null)
            return;
        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = RatingState.GETTING_MONEY;
                timer.stop();
            }
        });
        timer.start();
    }

    private void drawThinkingBubble(Graphics2D g2d) {
        emptyBubble.draw(g2d);
    }

    private void drawDots(Graphics2D g2d) {
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 100));
        g2d.drawString(customer.getMessage(), 310, 190);
    }

    private int getAverageRating() {
        return (getBuildingRating() + getOrderRating() + getGrillRating()) / 3;
    }

    private int getOrderRating() {
        int res = 100;
        int seconds = parent.orderStation.getSeconds();
        while (seconds > 60) {
            seconds -= 10;
            res -= 10;
        }
        if (res < 0)
            res = 0;
        return res;
    }

    private int getBuildingRating() {
        int size = burgerResult.size() - 1;
        if (size == 0)
            size = 1;
        int maxStep = 100 / (size);
        int xDiff = burgerResult.getFirst().getX();
        int res = 100;
        if (receipt.size() != burgerResult.size())
            res -= 20;
        for (int i = 0; i < (Math.min(receipt.size(), burgerResult.size())) - 1; i++) {
            if (!Objects.equals(receipt.get(i).getSrc(), burgerResult.get(i).getSrc()))
                res -= maxStep;
            if (!(receipt.get(i).getX() > xDiff - 5 && receipt.get(i).getX() <= xDiff + 5))
                res -= 3;
        }
        if (res < 0)
            res = 0;
        return res;
    }

    private int getGrillRating() {
        return 100;
    }

    private void update() {
        fish.waitForRating();
        customer.waitForOrder();
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setBurgerResult(ArrayList<Product> burgerResult) {
        this.burgerResult = burgerResult;
        relocateBurgerElements();
    }

    private void relocateBurgerElements() {
        for (Product pr : burgerResult) {
            pr.setY(pr.getY() - 35);
            pr.setX(pr.getX() + 40);
        }
    }

    public void setReceipt(ArrayList<Product> receipt) {
        this.receipt = receipt;
        parent.pin.getTicket().setY(parent.pin.getTicket().getY() - 45);
        parent.pin.getTicket().updateReceiptPosition();
    }

    public void setTicketHolder(TicketHolder ticketHolder) {
        this.ticketHolder = ticketHolder;
        ticketHolder.setY(ticketHolder.getY() - 45);
    }
}
