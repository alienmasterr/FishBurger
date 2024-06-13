package Menu;

import BuildStation.BuildStation;
import Enums.*;
import GrillStation.GrillStation;
import GrillStation.GrillStationElements.Meat;
import Level.Level;
import Menu.MenuElements.LevelBar;
import Menu.MenuElements.MoneyDisplay;
import Menu.MenuElements.SoundPlayer;
import Menu.MenuElements.TicketPin;
import OrderStation.OrderStation;
import RatingStation.RatingStation;
import Store.Store;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;

import static Enums.PanelState.*;
import static Menu.Game.mouse;

public class GameMenu extends JPanel {
    private GamePanel gamePanel;
    private MenuPanel menuPanel = new MenuPanel();
    private Game parent;
    private Clip clip;
    public Thread gameThread;

    public GameMenu(Game parent) {
        super();
        this.parent = parent;
        gamePanel = new GamePanel(ORDER_STATION);
        setStaticSize();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.black);
        add(gamePanel);
        add(menuPanel);
    }

    public void turnOffTheMusic(){
        clip.stop();
    }

    public void startMusic(){
        if(clip != null){
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            return;
        }
        File file = new File("res/Music/gamemenu.wav");
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ignored) {}
    }

    public void restart() {
        gamePanel.restartGame();
    }

    private void setStaticSize() {
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setMaximumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setMaximumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
    }

    public class GamePanel extends JPanel implements Runnable {
        public double money = 0;
        public OrderState orderState = OrderState.WAITING_CUSTOMER;
        public CookingState cookingState = CookingState.NO_MEAT;
        public TicketPin pin = new TicketPin(680, 0, 340, 140);
        public OrderStation orderStation = new OrderStation(this);
        private BuildStation buildStation = new BuildStation(this);
        private GrillStation grillStation = new GrillStation(this);
        public RatingStation ratingStation = new RatingStation(this);
        private Store store = new Store(this);
        private LevelBar levelBar = new LevelBar(5, 5, 300, 60);
        private MoneyDisplay moneyDisplay = new MoneyDisplay(5, 65, 50, 50);
        public boolean isRunning = true;
        public PanelState panelState;

        public GamePanel(PanelState state) {
            super();
            this.panelState = state;
            setup();
            gameThread = new Thread(this);
            gameThread.start();
        }

        public void gameOver() {
            turnOffTheMusic();
            SoundPlayer.playDeathScream();
            parent.setVisiblePanel(FrameState.GAME_OVER);
        }

        public void restartGame() {
            if(!isToggled())
                toggleButtons();
            levelBar.setLevel(Level.getTimesBeforeNextLevel(), levelBar.getCurrentNumber());
            levelBar.setLevelNum(Level.levelState);
            orderState = OrderState.WAITING_CUSTOMER;
            cookingState = CookingState.NO_MEAT;
            panelState = ORDER_STATION;
            pin = new TicketPin(680, 0, 340, 140);
            orderStation = new OrderStation(this);
            buildStation = new BuildStation(this);
            grillStation = new GrillStation(this);
            ratingStation = new RatingStation(this);
            store = new Store(this);
        }

        public void increaseExp() {
            levelBar.increaseLevel();
        }

        public void updateMoneyDisplay() {
            moneyDisplay.setCurrentMoney(money);
            store.moneyDisplay.setCurrentMoney(money);
        }

        public void toggleButtons() {
            menuPanel.buildStation.toggle();
            menuPanel.grillStation.toggle();
            menuPanel.orderStation.toggle();
            menuPanel.storeButton.toggle();
        }

        public boolean isToggled() {
            return menuPanel.buildStation.active;
        }

        public void transferMeatToBuild(Meat meat) {
            buildStation.getMeatFromGrill(meat);
        }

        private void setup() {
            Level.levelState = parent.levelState.getState();
            levelBar.setLevel(Level.getTimesBeforeNextLevel(), 0);
            levelBar.setLevelNum(Level.levelState);
            moneyDisplay.setCurrentMoney(money);
            store.moneyDisplay.setCurrentMoney(money);
            setMaximumSize(new Dimension(Game.WIDTH, (int) (Game.HEIGHT * 0.9)));
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
            while (isRunning) {
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;
                lastTime = currentTime;
                if (delta >= 1) {
                    update();
                    repaint();
                    delta--;
                }
            }
        }

        //тут оновлюються дані панелей
        private void update() {
            if (orderState == OrderState.MAKING_ORDER) {
                orderStation.updateTime();
                if(Objects.equals(orderStation.customer.getSrc(), "/customers/customer12.png") && orderStation.getSeconds() >= 40) {
                    gameOver();
                    SoundPlayer.playGameOverSound();
                    orderStation.setSeconds(0);
                }
            }

        }

        //відмальовування предметів за зміненими даними
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            switch (panelState) {
                case ORDER_STATION:
                    orderStation.draw(g2d);
                    break;
                case BUILD_STATION:
                    buildStation.draw(g2d);
                    break;
                case GRILL_STATION:
                    grillStation.draw(g2d);
                    break;
                case RATING_STATION:
                    ratingStation.draw(g2d);
                    break;
                case STORE:
                    store.draw(g2d);
                    break;
                case GAME_MENU:
                    parent.setVisiblePanel(FrameState.MAIN_MENU);
            }
            if (panelState == STORE || ratingStation.state == RatingState.RESTARTING)
                return;
            if (pin.isDrawTicket())
                pin.draw(g2d);
            levelBar.draw(g2d);
            moneyDisplay.draw(g2d);
        }
    }

    private class MenuPanel extends JPanel {
        private PanelButton buildStation = new PanelButton("Build Station", BUILD_STATION);
        private PanelButton orderStation = new PanelButton("Order Station", ORDER_STATION);
        private PanelButton grillStation = new PanelButton("Grill Station", GRILL_STATION);
        private PanelButton menu = new PanelButton("Exit", GAME_MENU);
        private PanelButton storeButton = new PanelButton("Store", STORE);

        public MenuPanel() {
            super();
            setMaximumSize(new Dimension(Game.WIDTH, (int) (Game.HEIGHT * 0.1)));
            setBackground(Color.darkGray);
            setBorder(BorderFactory.createLineBorder(Color.darkGray, 15));
            setLayout(new BorderLayout(80, 50));
            setMenuButtons();
        }

        private void setMenuButtons() {
            setStoreButton();
            setStationButtons();
            setMenuButton();
        }

        private void setStationButtons() {
            JPanel stationPanel = new JPanel();
            stationPanel.setBackground(Color.darkGray);
            stationPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            stationPanel.add(buildStation, BorderLayout.WEST);
            stationPanel.add(orderStation, BorderLayout.CENTER);
            stationPanel.add(grillStation, BorderLayout.EAST);
            add(stationPanel, BorderLayout.CENTER);
        }

        private void setStoreButton() {
            storeButton.setPreferredSize(new Dimension((int) (Game.WIDTH * 0.07), (int) (Game.WIDTH * 0.05)));
            add(storeButton, BorderLayout.EAST);
        }

        private void setMenuButton() {
            menu.setPreferredSize(new Dimension((int) (Game.WIDTH * 0.07), (int) (Game.WIDTH * 0.05)));
            add(menu, BorderLayout.WEST);
        }

        private class PanelButton extends JButton {
            private boolean active = true;
            private PanelState panelState;

            public PanelButton(String text, PanelState panelState) {
                super(text);
                this.panelState = panelState;
                setup();
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!active)
                            return;
                        SoundPlayer.playButtonSound();
                        if(Objects.equals(text, "Exit"))
                            turnOffTheMusic();
                        gamePanel.panelState = panelState;
                    }
                });
            }

            public void toggle() {
                this.active = !active;
            }

            private void setup() {
                setFocusPainted(false);
                setPreferredSize(new Dimension((int) (Game.WIDTH * 0.15), (int) (Game.HEIGHT * 0.05)));
                setForeground(Color.white);
                setBackground(Color.black);
            }
        }
    }
}