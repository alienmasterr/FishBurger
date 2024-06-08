package Menu;

import Elements.Node;
import Enums.FrameState;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverMenu extends JPanel {
    private Game parent;
    public GameOverMenu(Game parent){
        super();
        this.parent = parent;
        setStaticSize();
        setExitButton();
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        Node mainMenu = new Node(0, 0, Game.WIDTH, Game.HEIGHT);
        mainMenu.getImage("/mainmenu/gameover.png");
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
