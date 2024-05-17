package Menu;

import Enums.PanelState;
import OrderStation.OrderStation;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Enums.PanelState.*;

public class MainPanel extends JPanel{
    private GamePanel gamePanel = new GamePanel(ORDER_STATION);
    public MainPanel(){
        super();
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setBackground(Color.GRAY);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        MenuPanel menuPanel = new MenuPanel();
        add(gamePanel);
        add(menuPanel);
    }

    private class GamePanel extends JPanel implements Runnable{
        private OrderStation orderStation = new OrderStation();
        private boolean isRunning = true;
        private PanelState panelState;
        public GamePanel(PanelState state){
            super();
            setPreferredSize(new Dimension(Game.WIDTH, (int) (Game.HEIGHT*0.9)));
            setBackground(Color.WHITE);
            this.panelState = state;
            Thread gameThread = new Thread(this);
            gameThread.start();
        }

        //оновлення та перемальовування панелі
        @Override
        public void run() {
            double drawInterval = (double) 1000000000 / 60;
            double delta = 0;
            long lastTime = System.nanoTime();
            long currentTime;
            while(isRunning){
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime)/drawInterval;
                lastTime = currentTime;
                if(delta >= 1) {
                    update();
                    repaint();
                    delta--;
                }
            }
        }

        //тут оновлюються дані панелей
        private void update() {

        }

        //відмальовування предметів за зміненими даними
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            switch (panelState){
                case MAIN_MENU:
                    //...
                    break;
                case ORDER_STATION:
                    orderStation.draw(g2d);
                    break;
                case BUILD_STATION:
                    //...
                    break;
                case GRILL_STATION:
                    //...
                    break;
            }
        }
    }

    private class MenuPanel extends JPanel{
        private PanelButton buildStation = new PanelButton("Build Station", BUILD_STATION);
        private PanelButton orderStation = new PanelButton("Order Station", ORDER_STATION);
        private PanelButton grillStation = new PanelButton("Grill Station", GRILL_STATION);
        public MenuPanel(){
            super();
            setPreferredSize(new Dimension(Game.WIDTH, (int) (Game.HEIGHT*0.1)));
            setBackground(Color.darkGray);
            setBorder(BorderFactory.createLineBorder(Color.darkGray, 20));
            setLayout(new BorderLayout(240, 50));
            add(buildStation, BorderLayout.WEST);
            add(orderStation, BorderLayout.CENTER);
            add(grillStation, BorderLayout.EAST);
        }

        private class PanelButton extends JButton{
            private PanelState panelState;
            public PanelButton(String text, PanelState panelState){
                super(text);
                this.panelState = panelState;
                setFocusPainted(false);
                setPreferredSize(new Dimension((int) (Game.WIDTH*0.15), (int) (Game.HEIGHT*0.05)));
                setForeground(Color.white);
                setBackground(Color.black);
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gamePanel.panelState = panelState;
                    }
                });
            }
        }
    }

}
