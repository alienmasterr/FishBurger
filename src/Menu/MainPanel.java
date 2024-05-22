package Menu;

import BuildStation.BuildStation;
import Elements.Mouse;
import Enums.OrderState;
import Enums.PanelState;
import GrillStation.GrillStation;
import OrderStation.OrderStation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

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
        public Mouse mouse = new Mouse();
        private OrderStation orderStation = new OrderStation();
        private BuildStation buildStation = new BuildStation();
        private GrillStation grillStation = new GrillStation();
        public static boolean isRunning = true;
        private PanelState panelState;
        private OrderState orderState = OrderState.WAITING_CUSTOMER;
        public GamePanel(PanelState state){
            super();
            setPreferredSize(new Dimension(Game.WIDTH, (int) (Game.HEIGHT*0.9)));
            setBackground(Color.WHITE);
            addMouseMotionListener(mouse);
            addMouseListener(mouse);
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
                    buildStation.draw(g2d);
                    break;
                case GRILL_STATION:
                    //...
                    //new GrillStation();
                    grillStation.draw(g2d);
                    break;
            }
        }
    }

    private class MenuPanel extends JPanel{
        private PanelButton buildStation = new PanelButton("Build Station", BUILD_STATION);
        private PanelButton orderStation = new PanelButton("Order Station", ORDER_STATION);
        private PanelButton grillStation = new PanelButton("Grill Station", GRILL_STATION);
        private PanelButton menu = new PanelButton("Exit", GAME_MENU);
        public MenuPanel(){
            super();
            setPreferredSize(new Dimension(Game.WIDTH, (int) (Game.HEIGHT*0.1)));
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
