package Menu;

import Elements.Node;
import Enums.FrameState;
import Enums.LevelState;
import Menu.MenuElements.SoundPlayer;
import Menu.TutorialElements.FirstPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TutorialMenu extends JPanel {
    private Game parent;

    public TutorialMenu(Game parent) {
        super();
        this.parent = parent;
        setStaticSize();
        setLayout(null);
        setExitButton();
    }

    private void setStaticSize() {
        setMaximumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setMinimumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
    }

    private void setExitButton() {
        EmptyButton level2 = new EmptyButton(FrameState.MAIN_MENU);
        level2.setBounds(435, 710, 180, 70);
        add(level2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawFirstPage(g2d);

    }

    private void drawFirstPage(Graphics2D g2d) {
        FirstPage firstPage = new FirstPage(70, 10, Game.WIDTH - 140, Game.HEIGHT - 20);
        firstPage.draw(g2d);

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


