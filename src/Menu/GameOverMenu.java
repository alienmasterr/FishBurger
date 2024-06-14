package Menu;

import Elements.Node;
import Enums.FrameState;
import Menu.MenuElements.SoundPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GameOverMenu extends JPanel {
    private Game parent;
    private Node mainMenu = new Node(0, 0, Game.WIDTH, Game.HEIGHT);
    private boolean isSwapped = false;
    public GameOverMenu(Game parent){
        super();
        this.parent = parent;
        setStaticSize();
        setExitButton();
        setLayout(null);
        parent.removeMoney();
        mainMenu.getImage("/mainmenu/gameover.png");
    }

    public void swapImage(){
        if(!isSwapped) {
            mainMenu.getImage("/mainmenu/gameover2.png");
            isSwapped = true;
        } else {
            mainMenu.getImage("/mainmenu/gameover.png");
            isSwapped = false;
        }
    }

    public boolean isSwapped() {
        return isSwapped;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        mainMenu.draw(g2d);
    }

    private void setStaticSize(){
        setMaximumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setMinimumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
    }

    private void setExitButton(){
        EmptyButton mainMenu = new EmptyButton(FrameState.MAIN_MENU);
        mainMenu.setBounds(22, 15, 75, 75);
        add(mainMenu);
    }

    private class EmptyButton extends JButton{
        private FrameState state;
        public EmptyButton(FrameState state){
            super();
            this.state = state;
            hideVisibility();
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SoundPlayer.playButtonSound();
                    parent.setVisiblePanel(state);
                }
            });
        }

        private void hideVisibility(){
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
        }
    }
}
