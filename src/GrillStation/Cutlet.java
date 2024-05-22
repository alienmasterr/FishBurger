package GrillStation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Timer;
import java.util.TimerTask;

public class Cutlet extends JPanel{

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Cutlet");
//        frame.add(new Cutlet(24,23,24,26));
//
//        frame.setVisible(true);
//    }

    private double timeTillReady;
    //номер замовлення до якого належить котлета
    private double orderNumber;
    private int xCoordinate;
    private int yCoordinate;

    private int prevX;
    private int prevY;
    private boolean dragging = false;

    //розмір котлет
    private final int cutletWidth=150;
    private final int cutletHeight=40;
    private final int cutletThickness=30;

    public Cutlet(){}

    public Cutlet(double timeTillReady, double orderNumber, int xCoordinate, int yCoordinate){
        this.timeTillReady = timeTillReady;
        this.orderNumber = orderNumber;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    private Ellipse2D ellipseUpper;
    private Ellipse2D ellipseDown;
    private Rectangle2D rect;

    //TODO зробити котлету красивою
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.decode("#4d3b39"));

        ellipseUpper = new Ellipse2D.Double(xCoordinate, yCoordinate, cutletWidth, cutletHeight);
        ellipseDown = new Ellipse2D.Double(xCoordinate, yCoordinate + cutletThickness, cutletWidth, cutletHeight);
        rect = new Rectangle2D.Double(xCoordinate, yCoordinate + cutletHeight / 2, cutletWidth, cutletThickness);


        g2d.fill(ellipseDown);
        g2d.fill(rect);
        g2d.fill(ellipseUpper);

        g2d.setColor(Color.decode("#382a2a"));
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(ellipseUpper);
    }

    public void reduceTime(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int secondsPassed = 0;

            @Override
            public void run() {
                secondsPassed++;
                //System.out.println("Seconds passed: " + secondsPassed);
                if (secondsPassed >=60) {
                    timer.cancel();
                }
            }
        }, 0, 1000);

    }


    public double getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(double orderNumber) {
        this.orderNumber = orderNumber;
    }
    public double getTimeTillReady() {
        return timeTillReady;
    }
    public void setTimeTillReady(double timeTillReady) {
        this.timeTillReady = timeTillReady;
    }
    public int getxCoordinate() {
        return xCoordinate;
    }
    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }
    public int getyCoordinate() {
        return yCoordinate;
    }
    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}

