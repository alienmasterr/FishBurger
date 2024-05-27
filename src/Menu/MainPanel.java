package Menu;

import BuildStation.BuildStation;
import Elements.Mouse;
import Enums.OrderState;
import Enums.PanelState;
import GrillStation.GrillStation;
import Menu.MenuElements.TicketPin;
import OrderStation.OrderStation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Enums.PanelState.*;

public class MainPanel extends JPanel{
    private GamePanel gamePanel = new GamePanel(ORDER_STATION);

    public MainPanel(){
        super();
        setStaticSize();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.black);
        MenuPanel menuPanel = new MenuPanel();
        add(gamePanel);
        add(menuPanel);
    }
    private void setStaticSize(){
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setMaximumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setMaximumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
    }

    public class GamePanel extends JPanel implements Runnable{
        public Mouse mouse = new Mouse();
        public OrderState orderState = OrderState.WAITING_CUSTOMER;
        public TicketPin pin = new TicketPin(680, 0, 340, 140);
        private OrderStation orderStation = new OrderStation(this);
        private BuildStation buildStation = new BuildStation();
        private GrillStation grillStation = new GrillStation(this);
        public static boolean isRunning = true;
        private PanelState panelState;
        public GamePanel(PanelState state){
            super();
            this.panelState = state;
            setup();
            Thread gameThread = new Thread(this);
            gameThread.start();
        }

        private void setup(){
            setMaximumSize(new Dimension(Game.WIDTH, (int) (Game.HEIGHT*0.9)));
            setBackground(Color.darkGray);
            addMouseMotionListener(mouse);
            addMouseListener(mouse);
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
                    buildStation.draw(g2d);
                    break;
                case GRILL_STATION:
                    //...
                    //new GrillStation();
                    grillStation.draw(g2d);
                    break;
            }
            pin.draw(g2d);
        }
    }

    private class MenuPanel extends JPanel{
        private PanelButton buildStation = new PanelButton("Build Station", BUILD_STATION);
        private PanelButton orderStation = new PanelButton("Order Station", ORDER_STATION);
        private PanelButton grillStation = new PanelButton("Grill Station", GRILL_STATION);
        private PanelButton menu = new PanelButton("Exit", GAME_MENU);
        public MenuPanel(){
            super();
            setMaximumSize(new Dimension(Game.WIDTH, (int) (Game.HEIGHT*0.1)));
            setBackground(Color.darkGray);
            setBorder(BorderFactory.createLineBorder(Color.darkGray, 15));
            setLayout(new BorderLayout(80, 50));
            setMenuButtons();
        }

        private void setMenuButtons(){
            setSoundButton();
            setStationButtons();
            setMenuButton();
        }

        private void setStationButtons(){
            JPanel stationPanel = new JPanel();
            stationPanel.setBackground(Color.darkGray);
            stationPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            stationPanel.add(buildStation, BorderLayout.WEST);
            stationPanel.add(orderStation, BorderLayout.CENTER);
            stationPanel.add(grillStation, BorderLayout.EAST);
            add(stationPanel, BorderLayout.CENTER);
        }

        private void setSoundButton(){
            JButton pause = new JButton("Vol");
            pause.setPreferredSize(new Dimension((int) (Game.WIDTH*0.07), (int) (Game.WIDTH*0.05)));
            pause.setBackground(Color.black);
            pause.setForeground(Color.white);
            pause.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //зміна гучності
                }
            });
            add(pause, BorderLayout.EAST);
        }

        private void setMenuButton(){
            menu.setPreferredSize(new Dimension((int) (Game.WIDTH*0.07), (int) (Game.WIDTH*0.05)));
            add(menu, BorderLayout.WEST);
        }

        private class PanelButton extends JButton{
            private PanelState panelState;
            public PanelButton(String text, PanelState panelState){
                super(text);
                this.panelState = panelState;
                setup();
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gamePanel.panelState = panelState;
                    }
                });
            }

            private void setup(){
                setFocusPainted(false);
                setPreferredSize(new Dimension((int) (Game.WIDTH*0.15), (int) (Game.HEIGHT*0.05)));
                setForeground(Color.white);
                setBackground(Color.black);
            }
        }
    }
}